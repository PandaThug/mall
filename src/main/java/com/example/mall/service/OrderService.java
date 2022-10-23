package com.example.mall.service;

import com.example.mall.dao.GoodsMapper;
import com.example.mall.dao.OrderMapper;
import com.example.mall.entity.Order;
import com.example.mall.entity.User;
import com.example.mall.utils.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private GoodsMapper goodsMapper;

    // 买家下单
    public Map<String, Object> addOrder(Order order) {

        Map<String, Object> map = new HashMap<>();

        User user = hostHolder.getUser();

        // 空值处理
        if (order == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        if (StringUtils.isBlank(order.getBuyerName())) {
            map.put("buyerNameMsg", "未填写收货人姓名!");
            return map;
        }
        if (order.getTelNumber() == 0) {
            map.put("buyerPhoneMsg", "未填写收货人电话号码!");
            return map;
        }
        if (StringUtils.isBlank(order.getAddress())) {
            map.put("buyerAddressMsg", "未填写收货地址!");
        }
        // 查询商品所在店铺
        int goodId = order.getGoodId();
        int storeId = goodsMapper.selectStoreIdByGoodId(goodId);

        order.setOrderId(0);
        order.setUserId(user.getId());
        order.setShopId(storeId);
        order.setTotalPrice(order.getPurchaseQuantity() * goodsMapper.selectPriceByGoodId(goodId));
        order.setOrderStatus(0);

        orderMapper.insertOrder(order);

        return map;

    }

    // 根据用户id查找其所有订单
    public List<Order> findOrdersByUserId(int userId) {
        return orderMapper.selectOrdersByUserId(userId);
    }

    // 根据店铺id查找其所有订单
    public List<Order> findOrdersByStoreId(int storeId) {
        return orderMapper.selectOrdersByShopId(storeId);
    }

    // 根据订单id更新订单状态
    public Map<String, Object> updateOrderStatusByOrderId(int orderId) {

        Map<String, Object> map = new HashMap<>();

        int i = orderMapper.updateOrderStatus(orderId);

        if (i != 1) {
            map.put("msg", "订单状态更新失败!");
        }

        return map;

    }

}
