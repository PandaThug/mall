package com.example.mall.dao;

import com.example.mall.entity.Good;
import com.example.mall.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {

    // id, good_name, good_price, good_category, good_introduction, good_sales, good_options, good_picture, good_store, good_comment_count, real_inventory, virtual_inventory
    Good selectGoodById(@Param("id") int id);

    // id, good_name, good_price, good_category, good_introduction, good_sales, good_options, good_picture, good_store, good_comment_count, real_inventory, virtual_inventory
    Good selectGoodByName(@Param("goodName") String goodName);

    // id, good_name, good_price, good_category, good_introduction, good_sales, good_options, good_picture, good_store, good_comment_count, real_inventory, virtual_inventory
    List<Good> selectGoodsByStoreId(@Param("storeId") int storeId);

    // id, good_name, good_price, good_category, good_introduction, good_sales
    List<Good> selectGoodByCategory(@Param("goodCategory") String goodCategory);

    // 查询商品所在店铺的编号
    int selectStoreIdByGoodId(@Param("goodId") int goodId);

    // 根据商品id查询商品单价
    int selectPriceByGoodId(@Param("goodId") int goodId);

    // 按照商品销量排序
    // id, good_name, good_price, good_category, good_introduction, good_sales
    List<Good> selectGoodBySales();

    // name包含该字符串的所有商品, 按销量排序
    List<Good> selectGoodHasStringBySales(@Param("searchString") String searchString);

    // good_name, good_price, good_category, good_introduction, good_sales, good_options
    int insertGood(Good good);

    int updateGoodName(@Param("id") int id, @Param("goodName") String goodName);

    int updateGoodPrice(@Param("id") int id, @Param("goodPrice") int goodPrice);

    // 根据商品id删除对应商品
    int deleteGoodById(@Param("id") int id);

    // 根据多个商品id删除多个商品
    int deleteGoodsByIds(@Param("ids") int[] ids);

    // 根据商品id更新商品的销量
    int updateGoodSalesById(@Param("id") int id, @Param("sales") int sales);

    // 根据商品id更新商品评价数量
    int updateGoodCommentsCountByGoodId(@Param("id") int id);

    // 用户下单时根据对应商品id和购买数量更新商品虚拟库存
    int updateGoodVirtualInventoryByGoodIdAndPurchaseQuantity(@Param("id") int id, @Param("quantity") int quantity);

    // 用户支付时根据对应商品id和购买数量更新商品实际库存
    int updateGoodRealInventoryByGoodIdAndPurchaseQuantity(@Param("id") int id, @Param("quantity") int quantity);

    // 根据商品id查询商品实际库存
    int selectRealInventoryByGoodId(@Param("id") int id);

    // 根据商品id查询商品虚拟库存
    int selectVirtualInventoryByGoodId(@Param("id") int id);


}
