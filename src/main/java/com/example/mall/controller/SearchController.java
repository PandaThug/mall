package com.example.mall.controller;

import com.example.mall.entity.Good;
import com.example.mall.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private StoreService storeService;

    @RequestMapping(path = "/search/searchString", method = RequestMethod.GET)
    @ResponseBody
    public String searchGoodsByString(String searchString, Model model) {

        List<Good> goods = storeService.findGoodsHaveString(searchString);
        for (int i = 0; i < goods.size(); i++) {
            model.addAttribute("good" + i, goods.get(i));
        }
        return model.toString();

    }

}
