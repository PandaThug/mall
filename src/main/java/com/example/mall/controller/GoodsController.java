package com.example.mall.controller;

import com.example.mall.entity.Good;
import com.example.mall.entity.User;
import com.example.mall.service.GoodsService;
import com.example.mall.service.StoreService;
import com.example.mall.utils.HostHolder;
import com.example.mall.utils.MallConstant;
import com.example.mall.utils.MallUtil;
import com.example.mall.utils.UploadPictureUtil;
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

    @RequestMapping(path = "/goodManage/add", method = RequestMethod.POST)
    @ResponseBody
    public String addGood(Model model, String goodName, String goodCategory, String goodPrice, String goodImg,
                          String goodOptions, String goodDetails){

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

        Good good = new Good(0, goodName, price, goodCategory, goodDetails, 0, goodOptions, goodImg, storeId, 0);

        Map<String, Object> map = goodsService.addGoods(good);

        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "商品添加成功!");
        } else {
            model.addAttribute("goodNameMsg", map.get("goodNameMsg"));
            model.addAttribute("goodPriceMsg", map.get("goodPriceMsg"));
            model.addAttribute("goodPictureMsg", map.get("goodPictureMsg"));
            model.addAttribute("goodOptionsMsg", map.get("goodOptionsMsg"));
            model.addAttribute("goodInstructionMsg", map.get("goodInstructionMsg"));
        }

        return model.toString();

    }

    @RequestMapping(path = "/goodManage/get", method = RequestMethod.GET)
    @ResponseBody
    public String listGoods(Model model, int storeId) {

        List<Good> goods = storeService.findGoodsByStoreId(storeId);

        for (int i = 0; i < goods.size(); i++) {
            model.addAttribute("good" + i, goods.get(i));
        }


        return model.toString();

    }


}
