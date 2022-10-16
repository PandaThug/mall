package com.example.mall.controller;

import com.example.mall.entity.Page;
import com.example.mall.entity.User;
import com.example.mall.service.GoodsService;
import com.example.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page, @RequestParam(name = "orderMode", defaultValue = "0") int orderMode) {
        // 方法调用前，SpringMVC会自动实例化Model和Page，并将Page注入Model。
        // 所以，在Thymeleaf中可以直接访问Page对象中的数据。
//        page.setRows(discussPostService.findDiscussPostRows(0));
//        page.setPath("/index?orderMode=" + orderMode);
//
//        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit(), orderMode);
//        List<Map<String, Object>> discussPosts = new ArrayList<>();
//        if (list != null) {
//            for (DiscussPost post : list) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("post", post);
//                User user = userService.findUserById(post.getUserId());
//                map.put("user", user);
//
//                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId());
//                map.put("likeCount", likeCount);
//
//                discussPosts.add(map);
//            }
//        }
//        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("orderMode", orderMode);
        return "/index";
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String getErrorPage() {
        return "/error/500";
    }

    @RequestMapping(path = "/denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        return "/error/404";
    }

}
