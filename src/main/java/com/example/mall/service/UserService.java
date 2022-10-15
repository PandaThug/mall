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
import org.springframework.security.core.GrantedAuthority;
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
    @Autowired
    private Producer kaptchaProducer;
    @Value("${mall.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User findUserById(int id) {
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
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg", "邮箱不能为空!");
            return map;
        }
        // 验证账号
        User u = userMapper.selectByName(user.getUsername());
        if (u != null) {
            map.put("usernameMgs", "该账号已存在!");
            return map;
        }
        if(!user.getPassword().equals(confirmPassword)){
            map.put("confirmPasswordMsg","两次输入的密码不一致!");
            return map;
        }
        // 验证邮箱
        u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            map.put("emailMgs", "该邮箱已被注册!");
            return map;
        }
        // 注册用户
        user.setSalt(MallUtil.generateUUID().substring(0, 5));
        user.setPassword(MallUtil.md5(user.getPassword() + user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(MallUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        userMapper.insertUser(user);
        // 发送激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // http://localhost:8080/community/activation/101/code
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", content);

        return map;
    }

    public int activation(int userId, String code) {
        User user = userMapper.selectById(userId);
        if (user.getStatus() == 1) {
            return ACTIVATION_REPEAT;
        } else if (user.getActivationCode().equals(code)) {
            userMapper.updateStatus(userId, 1);
            clearCache(userId);
            return ACTIVATION_SUCCESS;
        } else {
            return ACTIVATION_FAILURE;
        }
    }

    public Map<String, Object> login(String username, String password, int expiredSeconds) {
        Map<String, Object> map = new HashMap<>();
        // 空值处理
        if (StringUtils.isBlank(username)) {
            map.put("usernameMsg", "账号不能为空！");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("passwordMsg", "密码不能为空！");
            return map;
        }

        // 验证账号
        User user = userMapper.selectByName(username);
        if (user == null) {
            map.put("usernameMsg", "该账号不存在！");
            return map;
        }

        // 验证状态
        if (user.getStatus() == 0) {
            map.put("usernameMsg", "该账号未激活！");
            return map;
        }

        // 验证密码
        password = MallUtil.md5(password + user.getSalt());
        if (user.getPassword().equals(password)) {
            map.put("passwordMsg", "密码不正确！");
            return map;
        }

        // 生成登录凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(MallUtil.generateUUID());
        loginTicket.setStatus(0);

        loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000L));
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

    public int updateHeader(int userId, String headerUrl) {
//        return userMapper.updateHeader(userId, headerUrl);
        int rows = userMapper.updateHeader(userId, headerUrl);
        clearCache(userId);
        return rows;
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
        int id=user.getId();
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
    private User getCache(int userId) {
        String redisKey = RedisKeyUtil.getUserKey(userId);
        return (User) redisTemplate.opsForValue().get(redisKey);
    }

    // 2.取不到时初始化缓存数据
    private User initCache(int userId) {
        User user = userMapper.selectById(userId);
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.opsForValue().set(redisKey, user, 3600, TimeUnit.SECONDS);
        return user;
    }

    // 3.数据变更时清除缓存数据
    private void clearCache(int userId) {
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.delete(redisKey);
    }

    // 查询某个用户的权限
    public Collection<? extends GrantedAuthority> getAuthorities(int userId) {
        User user = this.findUserById(userId);

        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                switch (user.getType()) {
                    case 0:
                        return AUTHORITY_ADMIN;
                    case 1:
                        return AUTHORITY_SELLER;
                    default:
                        return AUTHORITY_BUYER;
                }
            }
        });
        return list;
    }

    public User findUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    public void sendCode(String email, HttpServletResponse response) {
        String text = MallUtil.generateUUID().substring(0,6);
        // 验证码的归属
        String codeOwner = MallUtil.generateUUID();
        Cookie cookie = new Cookie("codeOwner", codeOwner);
        //失效的时间是10min
        cookie.setMaxAge(60*10);
        cookie.setPath(contextPath);
        response.addCookie(cookie);
        // 将验证码存入Redis,失效的时间是10min
        String redisKey = RedisKeyUtil.getCodeKey(codeOwner);
        redisTemplate.opsForValue().set(redisKey, text, 60*10, TimeUnit.SECONDS);

        // 激活邮件,使用thymeleaf创建的对象携带变量
        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("text", text);
        //使用模板引擎，利用thymeleaf，将context放到/mail/activation.html文件中，然后再利用mail包发送给邮箱
        String content = templateEngine.process("/mail/forget", context);
        mailClient.sendMail(email, "忘记密码", content);

    }

    public Map<String, Object> changePasswordByCode(String email, String password) {
        Map<String, Object> map = new HashMap<>();
        // 验证密码
        if (StringUtils.isBlank(password)) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }
        User user=userMapper.selectByEmail(email);
        password=MallUtil.md5(password + user.getSalt());
        userMapper.updatePassword(user.getId(),password);
        map.put("success","success");
        clearCache(user.getId());
        return map;
    }

}
