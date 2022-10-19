package com.example.mall.controller;

import com.example.mall.entity.Store;
import com.example.mall.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller()
public class ManageController {

    @Autowired
    private StoreService storeService;

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateStore(Model model, String storeId, String storeName, String instruction) {
        Map<String, Object> map = storeService.changeStoreNameAndInstruction(storeId, storeName, instruction);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "店铺信息修改成功!");
        } else {
            model.addAttribute("msg", map.get("msg"));
        }
        return model.toString();
    }

    @RequestMapping(path = "/information", method = RequestMethod.POST)
    @ResponseBody
    public String getStoreInformation(Model model, String storeId) {
        Store store = storeService.findStoreByUserId(storeId);
        model.addAttribute("store", store);
        return model.toString();
    }

}
