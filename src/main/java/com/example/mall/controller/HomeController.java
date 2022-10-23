package com.example.mall.controller;

import com.example.mall.entity.Good;
import com.example.mall.entity.Page;
import com.example.mall.entity.Store;
import com.example.mall.entity.User;
import com.example.mall.service.GoodsService;
import com.example.mall.service.StoreService;
import com.example.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private StoreService storeService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage() {
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

    @RequestMapping(path = "/addToIndex", method = RequestMethod.GET)
    @ResponseBody
    public String addGoodsToIndex(Model model) {
        List<Good> goods = storeService.findGoodsByGoodSales();
        for (int i = 0; i < goods.size(); i++) {
            model.addAttribute("good" + i, goods.get(i));
        }
        return model.toString();
    }

    @RequestMapping(path = "/goods", method = RequestMethod.GET)
    public String getGoodsPage() {
        return "/site/goods";
    }

    @RequestMapping(path = "/shop", method = RequestMethod.GET)
    public String getShopPage() {
        return "/site/shop";
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String getSearchPage() {
        return "/site/search";
    }

    @RequestMapping(path = "/myOrder", method = RequestMethod.GET)
    public String getMyOrderPage() {
        return "/site/myOrder";
    }

}
