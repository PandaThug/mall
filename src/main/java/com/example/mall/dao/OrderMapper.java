package com.example.mall.dao;

import com.example.mall.entity.Good;
import com.example.mall.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    // order_id,user_id,shop_id,good_id,purchase_quantity,total_price,good_name,good_option,order_status,address,tel_number,buyer_name
    int insertOrder(Order order);

    // 用买家id查询其所有的订单
    List<Order> selectOrdersByUserId(@Param("userId") int userId);

    // 用卖家id查询其所有的订单
    List<Order> selectOrdersByShopId(@Param("shopId") int shopId);

    int updateOrderStatus(@Param("orderId") int orderId);

    int selectOrderStatusByGoodId(@Param("goodId") int goodId);

    // 根据订单编号查找对应的商品编号
    int selectGoodIdByOrderId(@Param("orderId") int orderId);

    // 根据订单编号查找对应的店铺编号
    int selectStoreIdByOrderId(@Param("orderId") int orderId);

    // 根据订单编号查找对应的订单
    Order selectOrderById(@Param("orderId") int orderId);

    // 根据订单编号查找对应的用户编号
    int selectUserIdByOrderId(@Param("orderId") int orderId);


}
