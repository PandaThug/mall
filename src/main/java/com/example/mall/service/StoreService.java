package com.example.mall.service;

import com.example.mall.dao.CommentMapper;
import com.example.mall.dao.GoodsMapper;
import com.example.mall.dao.StoreMapper;
import com.example.mall.dao.UserMapper;
import com.example.mall.entity.Good;
import com.example.mall.entity.Store;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreService {

    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    // 修改店铺名称
    public Map<String, Object> changeStoreName(Integer storeId, String storeName) {
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
    public Map<String, Object> changeStoreInstruction(Integer storeId, String instruction) {
        HashMap<String , Object> map = new HashMap<>();
        // 空值处理
        if (StringUtils.isBlank(instruction)) {
            storeMapper.updateStoreInstruction(storeId, "这个店铺暂时没有描述~");
        }
        storeMapper.updateStoreInstruction(storeId, instruction);

        return map;
    }

    // 修改店铺名称和简介
    public Map<String, Object> changeStoreNameAndInstruction(Integer storeId, String storeName, String instruction) {
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
    public Map<String, Object> findStoreByUserId(Integer userId) {

        Map<String, Object> map = new HashMap<>();
        Store store = storeMapper.selectStoreByUserId(userId);
        map.put("storeId", store.getStoreId());
        map.put("storeName", store.getStoreName());
        map.put("storeInstruction", store.getInstruction());
        return map;
//        return storeMapper.selectStoreByUserId(userId);

    }

    // 根据店铺名称查询店铺编号
    public int findStoreIdByName(String storeName) {
        return storeMapper.selectStoreIdByName(storeName);
    }

    // 根据店铺编号查询所有商品
    public List<Good> findGoodsByStoreId(int storeId) {
        return goodsMapper.selectGoodsByStoreId(storeId);
    }

    // 根据销量查询前24位的商品
    public List<Good> findGoodsByGoodSales() {
        return goodsMapper.selectGoodBySales();
    }

    // 查询所有name包含该字符串的商品, 按销量排序
    public List<Good> findGoodsHaveString(String searchString) {
        return goodsMapper.selectGoodHasStringBySales(searchString);
    }

}
