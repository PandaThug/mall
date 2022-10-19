package com.example.mall.service;

import com.example.mall.dao.StoreMapper;
import com.example.mall.entity.Store;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StoreService {

    @Autowired
    private StoreMapper storeMapper;

    // 修改店铺名称
    public Map<String, Object> changeStoreName(String storeId, String storeName) {
        HashMap<String , Object> map = new HashMap<>();
        // 空值处理
        if (StringUtils.isBlank(storeName)) {
            map.put("msg", "店铺名称不能为空!");
            return map;
        }
        storeMapper.updateStoreName(storeId, storeName);

        return map;
    }

    // 修改店铺简介
    public Map<String, Object> changeStoreInstruction(String storeId, String instruction) {
        HashMap<String , Object> map = new HashMap<>();
        // 空值处理
        if (StringUtils.isBlank(instruction)) {
            storeMapper.updateStoreInstruction(storeId, "这个店铺暂时没有描述~");
        }
        storeMapper.updateStoreInstruction(storeId, instruction);

        return map;
    }

    // 修改店铺名称和简介
    public Map<String, Object> changeStoreNameAndInstruction(String storeId, String storeName, String instruction) {
        HashMap<String , Object> map = new HashMap<>();
        if (StringUtils.isBlank(storeName)) {
            map.put("msg", "店铺名称不能为空!");
            return map;
        }
        storeMapper.updateStoreName(storeId, storeName);
        if (StringUtils.isBlank(instruction)) {
            storeMapper.updateStoreInstruction(storeId, "这个店铺暂时没有描述~");
        }
        storeMapper.updateStoreInstruction(storeId, instruction);

        return map;
    }

    // 根据用户的id获取店铺信息(店铺编号、店铺名称、店铺简介)
    public Store findStoreByUserId(String userId) {
        return storeMapper.selectStoreByUserId(userId);
    }

}
