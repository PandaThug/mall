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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private StoreService storeService;

    @RequestMapping(path = "/goodManage/add", method = RequestMethod.POST)
    @ResponseBody
    public String addGood(Model model, @RequestPart Good good, @RequestPart MultipartFile goodImg) throws IOException {

        User user = hostHolder.getUser();
        System.out.println(user);

        if (user == null) {
            return MallUtil.getJSONString(403, "你还没有登录!");
        }
        if (!user.getType().equals("1")) {
            return MallUtil.getJSONString(403, "你没有商品管理权限!");
        }
        if (goodImg == null) {
            model.addAttribute("goodPictureMsg", "图片不能为空!");
            return model.toString();
        }
        String fileName = goodImg.getOriginalFilename();
        String suffix = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));
        if (StringUtils.isBlank(suffix)) {
            model.addAttribute("error", "文件的格式不正确!");
            return model.toString();
        }

        File tmp = File.createTempFile(UUID.randomUUID().toString().replace("-", ""), "tmp");
        goodImg.transferTo(tmp);
        good.setGoodPicture(UploadPictureUtil.enPic(tmp));

        Map<String, Object> storeByUserId = storeService.findStoreByUserId(user.getId());
        String storeName = (String) storeByUserId.get("storeName");
        int storeId = storeService.findStoreIdByName(storeName);
        good.setGoodStore(storeName);

        good.setGoodSales(0);

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


}
