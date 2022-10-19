package com.example.mall.dao;

import com.example.mall.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreMapper {

    // store_id, store_name, total_sales, instruction
    Store selectStoreById(@Param("storeId") Integer storeId);

    // store_id, store_name, total_sales, instruction
    Store selectStoreByName(@Param("storeName") String storeName);

    // store_id, store_name, total_sales, instruction
    Store selectStoreByUserId(@Param("userId") Integer userId);

    // store_id, store_name, total_sales, instruction
    int insertStore(Store store);

    int updateStoreName(@Param("storeId") Integer storeId, @Param("storeName") String storeName);

    int updateStoreInstruction(@Param("storeId") Integer storeId, @Param("instruction") String instruction);

}
