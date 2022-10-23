package com.example.mall.dao;

import com.example.mall.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    // order_id,user_id,shop_id,good_id,purchase_quantity,total_price,good_name,good_option,order_status,address,tel_number,buyer_name
    int insertOrder(Order order);

}
