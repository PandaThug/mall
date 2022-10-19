package com.example.mall.controller;

import com.alibaba.fastjson.JSON;
import com.example.mall.entity.Store;
import com.example.mall.service.StoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ManageController {

    @Autowired
    private StoreService storeService;

    @RequestMapping(path = "/shopManage", method = RequestMethod.GET)
    public String shopManage() {
        return "/site/shopManage";
    }

    @RequestMapping(path = "/shopManage/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateStore(Model model, Integer storeId, String storeName, String instruction) {

        Map<String, Object> map = storeService.changeStoreNameAndInstruction(storeId, storeName, instruction);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "店铺信息修改成功!");
        } else {
            model.addAttribute("msg", map.get("msg"));
        }
        return model.toString();

    }

    @RequestMapping(path = "/shopManage/information", method = RequestMethod.POST)
    @ResponseBody
    public String getStoreInformation(Model model, @RequestBody String str) {

        Integer userId = Integer.parseInt(JSON.parseObject(str).get("storeId").toString());

        Map<String, Object> map = storeService.findStoreByUserId(userId);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "未查询到店铺信息!");
        }
        model.addAttribute("storeId", map.get("storeId"));
        model.addAttribute("storeName", map.get("storeName"));
        model.addAttribute("storeInstruction", map.get("storeInstruction"));

        return model.toString();

    }

}
