package com.example.mall.controller;

import com.example.mall.entity.User;
import com.example.mall.service.UserService;
import com.example.mall.utils.MallUtil;
import com.example.mall.utils.RedisKeyUtil;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.example.mall.utils.MallConstant.*;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {

        return "/site/register";

    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage() {

        return "/site/login";

    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(Model model, User user, String confirmPassword) {

        Map<String, Object> map = userService.register(user, confirmPassword);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "注册成功！");
            return "/index";
        } else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("confirmPasswordMsg", map.get("confirmPasswordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "/site/register";
        }

    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, String password,
                        Model model, /*HttpSession session,*/HttpServletResponse response) {

        // 检查账号、密码
        Map<String, Object> map = userService.login(username, password);
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath(contextPath);
            cookie.setMaxAge(DEFAULT_EXPIRED_SECONDS);
            response.addCookie(cookie);
            return "redirect:/index";
        } else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            return "/site/login";
        }

    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(@CookieValue("ticket")String ticket) {

        userService.logout(ticket);
//        SecurityContextHolder.clearContext();
        return "redirect:/login";   // 默认GET请求的/login

    }

}
