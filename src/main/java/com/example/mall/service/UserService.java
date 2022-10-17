package com.example.mall.service;

import com.example.mall.dao.UserMapper;
import com.example.mall.entity.LoginTicket;
import com.example.mall.entity.User;
import com.example.mall.utils.MailClient;
import com.example.mall.utils.MallUtil;
import com.example.mall.utils.RedisKeyUtil;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.example.mall.utils.MallConstant.*;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailClient mailClient;
    @Resource
    private TemplateEngine templateEngine;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${mall.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User findUserById(String id) {
//        return userMapper.selectById(id);
        User user = getCache(id);
        if (user == null) {
            user = initCache(id);
        }
        return user;
    }

    // 用户注册
    public Map<String, Object> register(User user, String confirmPassword) {
        Map<String, Object> map = new HashMap<>();
        // 空值处理
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "账户不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }
        // 验证账号
        User u = userMapper.selectByName(user.getUsername());
        if (u != null) {
            map.put("usernameMsg", "该账号已存在!");
            return map;
        }
        if(!user.getPassword().equals(confirmPassword)){
            map.put("confirmPasswordMsg", "两次输入的密码不一致!");
            return map;
        }
        // 注册用户
        user.setSalt(MallUtil.generateUUID().substring(0, 5));
        user.setPassword(MallUtil.md5(user.getPassword() + user.getSalt()));
        user.setType(user.getType());
        userMapper.insertUser(user);

        return map;
    }

    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        // 空值处理
        if (StringUtils.isBlank(username)) {
            map.put("usernameMsg", "账号不能为空!");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }
        // 验证账号
        User user = userMapper.selectByName(username);
        if (user == null) {
            map.put("usernameMsg", "该账号不存在!");
            return map;
        }
        // 验证密码
        password = MallUtil.md5(password + user.getSalt());
        if (user.getPassword().equals(password)) {
            map.put("passwordMsg", "密码不正确!");
            return map;
        }
        // 生成登录凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(MallUtil.generateUUID());
        loginTicket.setStatus(0);
//        loginTicketMapper.insertLoginTicket(loginTicket);

        String redisKey = RedisKeyUtil.getTicketKey(loginTicket.getTicket());
        redisTemplate.opsForValue().set(redisKey, loginTicket);

        map.put("ticket", loginTicket.getTicket());
        return map;
    }

    public void logout(String ticket) {
//        loginTicketMapper.updateStatus(ticket, 1);
        String redisKey = RedisKeyUtil.getTicketKey(ticket);
        LoginTicket loginTicket  = (LoginTicket) redisTemplate.opsForValue().get(redisKey);
        loginTicket.setStatus(1);
        redisTemplate.opsForValue().set(redisKey, loginTicket);
    }

    public LoginTicket findLoginTicket(String ticket) {
//        return loginTicketMapper.selectByTicket(ticket);
        String redisKey = RedisKeyUtil.getTicketKey(ticket);
        return (LoginTicket) redisTemplate.opsForValue().get(redisKey);
    }

    public Map<String, Object> changePassword(User user, String oldPassword, String newPassword, String confirmPassword) {
        Map<String, Object> map = new HashMap<>();
        // 验证密码
        oldPassword = MallUtil.md5(oldPassword + user.getSalt());
        if (!user.getPassword().equals(oldPassword)) {
            map.put("oldPasswordMsg", "密码不正确!");
            return map;
        }
        if (StringUtils.isBlank(newPassword)) {
            map.put("newPasswordMsg", "密码不能为空!");
            return map;
        }
        if(!newPassword.equals(confirmPassword)){
            map.put("confirmPasswordMsg", "两次输入的密码不一致!");
            return map;
        }
        String id = user.getId();
        newPassword=MallUtil.md5(newPassword + user.getSalt());
        if(oldPassword.equals(newPassword)){
            map.put("newPasswordMsg", "旧密码与新密码一致!");
            return map;
        }
        userMapper.updatePassword(id,newPassword);
        clearCache(user.getId());
        return map;
    }

    public User findUserByName(String userName) {
        return userMapper.selectByName(userName);
    }

    // 1.优先从缓存中取值
    private User getCache(String userId) {
        String redisKey = RedisKeyUtil.getUserKey(userId);
        return (User) redisTemplate.opsForValue().get(redisKey);
    }

    // 2.取不到时初始化缓存数据
    private User initCache(String userId) {
        User user = userMapper.selectById(userId);
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.opsForValue().set(redisKey, user, 3600, TimeUnit.SECONDS);
        return user;
    }

    // 3.数据变更时清除缓存数据
    private void clearCache(String userId) {
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.delete(redisKey);
    }

    // 查询某个用户的权限
//    public Collection<? extends GrantedAuthority> getAuthorities(int userId) {
//        User user = this.findUserById(userId);
//
//        List<GrantedAuthority> list = new ArrayList<>();
//        list.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                switch (user.getType()) {
//                    case 0:
//                        return AUTHORITY_ADMIN;
//                    case 1:
//                        return AUTHORITY_SELLER;
//                    default:
//                        return AUTHORITY_BUYER;
//                }
//            }
//        });
//        return list;
//    }

}