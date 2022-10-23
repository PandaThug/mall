package com.example.mall.dao;

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

}
