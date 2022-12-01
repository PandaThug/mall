package com.example.mall.controller;

import com.alibaba.fastjson.JSON;
import com.example.mall.entity.Good;
import com.example.mall.entity.User;
import com.example.mall.service.GoodsService;
import com.example.mall.service.StoreService;
import com.example.mall.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Controller
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Value("${mall.path.upload}")
    private String uploadPath;
    @Value("${mall.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private StoreService storeService;

    // 添加商品
    @RequestMapping(path = "/goodManage/add", method = RequestMethod.POST)
    @ResponseBody
    public String addGood(Model model, String goodName, String goodType, String goodPrice, String goodImg,
                          String goodOptions, String goodDetails, String inventory){

        User user = hostHolder.getUser();

        // 空值处理
        if (user == null) {
            return MallUtil.getJSONString(403, "你还没有登录!");
        }
        if (!user.getType().equals("2")) {
            return MallUtil.getJSONString(403, "你没有商品管理权限!");
        }

        Map<String, Object> storeByUserId = storeService.findStoreByUserId(user.getId());
        String storeName = (String) storeByUserId.get("storeName");
        int storeId = storeService.findStoreIdByName(storeName);
        int price = Integer.parseInt(goodPrice);

        Good good = new Good(0, goodName, price, goodType, goodDetails, 0,
                goodOptions, goodImg, storeId, 0, Integer.parseInt(inventory), Integer.parseInt(inventory));

        Map<String, Object> map = goodsService.addGoods(good);

        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "商品添加成功!");
        } else {
            model.addAttribute("goodNameMsg", map.get("goodNameMsg"));
            model.addAttribute("goodPriceMsg", map.get("goodPriceMsg"));
            model.addAttribute("goodPictureMsg", map.get("goodPictureMsg"));
            model.addAttribute("goodOptionsMsg", map.get("goodOptionsMsg"));
            model.addAttribute("goodInstructionMsg", map.get("goodInstructionMsg"));
            model.addAttribute("goodInventoryMsg", map.get("goodInventoryMsg"));
        }

        return model.toString();

    }

    // 获取商品列表
    @RequestMapping(path = "/goodManage/getAll", method = RequestMethod.GET)
    @ResponseBody
    public String listGoods(Model model, int storeId) {

        List<Good> goods = storeService.findGoodsByStoreId(storeId);

        for (int i = 0; i < goods.size(); i++) {
            model.addAttribute("good" + i, goods.get(i));
        }

        return model.toString();

    }

    // 根据商品id获取商品
    @RequestMapping(path = "/goodManage/get", method = RequestMethod.GET)
    @ResponseBody
    public String findGood(Model model, int goodsId) {

        Good good = goodsService.findGoodById(goodsId);

        model.addAttribute("goodId", good.getId());
        model.addAttribute("goodName", good.getGoodName());
        model.addAttribute("goodPrice", good.getGoodPrice());
        model.addAttribute("goodCategory", good.getGoodCategory());
        model.addAttribute("goodIntroduction", good.getGoodIntroduction());
        model.addAttribute("goodSales", good.getGoodSales());
        model.addAttribute("goodOptions", good.getGoodOptions());
        model.addAttribute("goodPicture", good.getGoodPicture());
        model.addAttribute("goodStore", good.getGoodStore());
        model.addAttribute("goodCommentCount", good.getGoodCommentCount());
        model.addAttribute("goodInventory", good.getVirtualInventory());

        return model.toString();

    }

    // 根据商品id删除商品
    @RequestMapping(path = "/goodManage/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteGoods(Model model, @RequestBody String goodId) {

        String id = JSON.parseObject(goodId).getString("goodId");

        if (NumUtil.isNumeric(id)) {
            int i = Integer.parseInt(id);
            int num = goodsService.deleteGoodByGoodId(i);
            if (num != 0) {
                model.addAttribute("msg", "删除成功!");
            } else {
                model.addAttribute("msg", "商品不存在!");
            }
        } else {
            model.addAttribute("msg", "商品编号不是数字!");
        }

        return model.toString();

    }


}
