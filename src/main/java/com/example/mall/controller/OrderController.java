package com.example.mall.controller;

import com.example.mall.entity.Order;
import com.example.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orderManage/add", method = RequestMethod.POST)
    @ResponseBody
    public String addOrder(Model model, String orderGoodsName, String orderGoodsId, String orderOption, String orderNum,
                           String orderBuyerName, String orderBuyerAddress, String orderBuyerPhoneNumber) {

        Order order = new Order(0, 0, 0, Integer.parseInt(orderGoodsId), Integer.parseInt(orderNum),
                0, orderGoodsName, orderOption, 0, orderBuyerAddress,
                Integer.parseInt(orderBuyerPhoneNumber), orderBuyerName);

        Map<String, Object> map = orderService.addOrder(order);

        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "下单成功!");
        } else {
            model.addAttribute("buyerNameMsg", map.get("buyerNameMsg"));
            model.addAttribute("buyerPhoneMsg", map.get("buyerPhoneMsg"));
            model.addAttribute("buyerAddressMsg", map.get("buyerAddressMsg"));
        }

        return model.toString();

    }
}
