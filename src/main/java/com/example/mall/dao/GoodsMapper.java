package com.example.mall.dao;

import com.example.mall.entity.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {

    Good selectById(@Param("id") int id);

    Good selectByName(@Param("goodName") String goodName);

    List<Good> selectByCategory(@Param("goodCategory") int goodCategory);

    // 按照商品销量排序
    List<Good> selectBySales();

    int insertGood(Good good);

    int updateGoodName(@Param("id") int id, @Param("goodName") String goodName);

    int updateGoodPrice(@Param("id") int id, @Param("goodPrice") int goodPrice);

}
