package com.example.mall.service;

import com.example.mall.dao.GoodsMapper;
import com.example.mall.dao.OrderMapper;
import com.example.mall.dao.UserMapper;
import com.example.mall.entity.Good;
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
    @Autowired
    private UserMapper userMapper;

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
        }
        if (StringUtils.isBlank(order.getTelNumber())) {
            map.put("buyerPhoneMsg", "未填写收货人电话号码!");
        }
        if (StringUtils.isBlank(order.getAddress())) {
            map.put("buyerAddressMsg", "未填写收货地址!");
        }
        // 查询商品所在店铺
        int goodId = order.getGoodId();
        int storeId = goodsMapper.selectStoreIdByGoodId(goodId);

        // 查询商品虚拟库存
        int purchaseQuantity = order.getPurchaseQuantity();
        int virtualInventory = goodsMapper.selectVirtualInventoryByGoodId(goodId);
        if (virtualInventory < purchaseQuantity) {
            map.put("inventoryMsg", "商品库存不足!");
        } else {
            order.setOrderId(0);
            order.setShopId(storeId);
            order.setTotalPrice(order.getPurchaseQuantity() * goodsMapper.selectPriceByGoodId(goodId));
            order.setOrderStatus(0);
            order.setOrderStatus(-1);
            // 更新商品虚拟库存
            goodsMapper.updateGoodVirtualInventoryByGoodIdAndPurchaseQuantity(goodId, virtualInventory - purchaseQuantity);
            orderMapper.insertOrder(order);
        }
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

        int goodId = orderMapper.selectGoodIdByOrderId(orderId);
        Good good = goodsMapper.selectGoodById(goodId);
        int goodPrice = good.getGoodPrice();
        Order order = orderMapper.selectOrderById(orderId);
        int status = order.getOrderStatus();
        int storeId = goodsMapper.selectStoreIdByGoodId(goodId);

        // 状态从-1到0：支付
        if (status == -1) {
            int realInventory = good.getRealInventory();
            int purchaseQuantity = order.getPurchaseQuantity();
            if (realInventory < purchaseQuantity) {
                map.put("msg", "支付失败,商品库存不足!");
            } else {
                int userId = orderMapper.selectUserIdByOrderId(orderId);
                Integer buyerAccount = userMapper.selectAccountById(userId);
                Integer sellerAccount = userMapper.selectAccountById(storeId);

                int price = goodPrice * purchaseQuantity;
                if (buyerAccount < price) {
                    map.put("msg", "支付失败,用户余额不足!");
                } else {
                    goodsMapper.updateGoodRealInventoryByGoodIdAndPurchaseQuantity(goodId, realInventory - purchaseQuantity);
                    userMapper.updateUserAccountByUserId(userId, buyerAccount - price);
                    userMapper.updateUserAccountByUserId(storeId, sellerAccount + price);
                    orderMapper.updateOrderStatus(orderId);
                }
            }
        } else {
            orderMapper.updateOrderStatus(orderId);
        }

        return map;

    }

}
