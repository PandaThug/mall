package com.example.mall.dao;

import com.example.mall.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreMapper {

    // store_id, store_name, create_time, total_sales, instruction
    Store selectStoreById(@Param("storeId") String storeId);

    // store_id, store_name, create_time, total_sales, instruction
    Store selectStoreByName(@Param("name") String name);

    Store selectStoreByUserId(@Param("userId") String userId);

    // store_id, store_name, create_time, total_sales, instruction
    int insertStore(Store store);

    int updateStoreName(@Param("storeId") String storeId, @Param("storeName") String storeName);

    int updateStoreInstruction(@Param("storeId") String storeId, @Param("instruction") String instruction);

}
