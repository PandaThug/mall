package com.example.mall.controller;

import com.example.mall.entity.Good;
import com.example.mall.entity.Order;
import com.example.mall.service.OrderService;
import org.apache.ibatis.ognl.ObjectElementsAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orderManage/add", method = RequestMethod.POST)
    @ResponseBody
    public String addOrder(Model model, String orderGoodsName, String orderGoodsId, String orderOption, String orderNum,
                           String orderBuyerName, String orderBuyerAddress, String orderBuyerPhoneNumber, String buyerId) {

        Order order = new Order(0, Integer.parseInt(buyerId), 0, Integer.parseInt(orderGoodsId),
                Integer.parseInt(orderNum), 0, orderGoodsName, orderOption, 0, orderBuyerAddress,
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

    // 根据用户id查询其所有的订单
    @RequestMapping(path = "/orderManage/buyer/getAll", method = RequestMethod.GET)
    @ResponseBody
    public String getAllOrdersWithBuyerId(Model model, String userId) {

        int id = Integer.parseInt(userId);

        List<Order> orders = orderService.findOrdersByUserId(id);

        Map<String, Object> map = new HashMap<>();

        for (Order order : orders) {
            map.put("orderId", order.getOrderId());
            map.put("buyerId", order.getUserId());
            map.put("shopId", order.getShopId());
            map.put("goodsId", order.getGoodId());
            map.put("orderNum", order.getPurchaseQuantity());
            map.put("totalPrice", order.getTotalPrice());
            map.put("goodsName", order.getGoodName());
            map.put("goodsOption", order.getGoodOption());
            map.put("orderStatus", order.getOrderStatus());
            map.put("buyerAddress", order.getAddress());
            map.put("buyerPhoneNumber", order.getTelNumber());
            map.put("buyerName", order.getBuyerName());
            model.addAttribute("orderId" + order.getOrderId(), map);
        }

        return model.toString();

    }

    // 根据店铺id查询其所有的订单
    @RequestMapping(path = "/orderManage/seller/getAll", method = RequestMethod.GET)
    @ResponseBody
    public String getAllOrdersWithStoreId(Model model, String storeId) {

        int id = Integer.parseInt(storeId);

        List<Order> orders = orderService.findOrdersByStoreId(id);

        Map<String, Object> map = new HashMap<>();

        for (Order order : orders) {
            map.put("orderId", order.getOrderId());
            map.put("buyerId", order.getUserId());
            map.put("shopId", order.getShopId());
            map.put("goodsId", order.getGoodId());
            map.put("orderNum", order.getPurchaseQuantity());
            map.put("totalPrice", order.getTotalPrice());
            map.put("goodsName", order.getGoodName());
            map.put("goodsOption", order.getGoodOption());
            map.put("orderStatus", order.getOrderStatus());
            map.put("buyerAddress", order.getAddress());
            map.put("buyerPhoneNumber", order.getTelNumber());
            map.put("buyerName", order.getBuyerName());
            model.addAttribute("orderId" + order.getOrderId(), map);
        }

        return model.toString();

    }

    // 根据店铺id更新订单状态
    @RequestMapping(path = "/orderManage/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateOrderStatusUsingOrderId(Model model, String orderId) {

        int id = Integer.parseInt(orderId);

        Map<String, Object> map = orderService.updateOrderStatusByOrderId(id);

        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "订单状态更新成功!");
        } else {
            model.addAttribute("msg", map.get("msg"));
        }

        return model.toString();

    }

}
