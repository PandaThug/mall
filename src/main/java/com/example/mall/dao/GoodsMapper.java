package com.example.mall.dao;

import com.example.mall.entity.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {

    // id, good_name, good_price, good_category, good_introduction, good_sales, good_options, good_picture, good_store, good_comment_count
    Good selectGoodById(@Param("id") int id);

    // id, good_name, good_price, good_category, good_introduction, good_sales, good_options, good_picture, good_store, good_comment_count
    Good selectGoodByName(@Param("goodName") String goodName);

    List<Good> selectGoodsByStoreId(@Param("storeId") int storeId);

    // id, good_name, good_price, good_category, good_introduction, good_sales
    List<Good> selectGoodByCategory(@Param("goodCategory") String goodCategory);

    // 按照商品销量排序
    // id, good_name, good_price, good_category, good_introduction, good_sales
    List<Good> SelectGoodBySales();

    // good_name, good_price, good_category, good_introduction, good_sales, good_options
    int insertGood(Good good);

    int updateGoodName(@Param("id") int id, @Param("goodName") String goodName);

    int updateGoodPrice(@Param("id") int id, @Param("goodPrice") int goodPrice);

}
