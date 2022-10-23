package com.example.mall.service;

import com.example.mall.dao.GoodsMapper;
import com.example.mall.entity.Good;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsService.class);

    @Autowired
    private GoodsMapper goodsMapper;

    // 添加商品
    public Map<String, Object> addGoods(Good good) {

        Map<String, Object> map = new HashMap<>();
        // 空值处理
        if (good == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        if (StringUtils.isBlank(good.getGoodName())) {
            map.put("goodNameMsg", "商品名称不能为空!");
            return map;
        }
        if (good.getGoodPrice() == 0) {
            map.put("goodPriceMsg", "商品价格不能为零!");
            return map;
        }
        if (StringUtils.isBlank(good.getGoodPicture())) {
            map.put("goodPictureMsg", "商品图片未上传!");
            return map;
        }
        if (StringUtils.isBlank(good.getGoodOptions())) {
            map.put("goodOptionsMsg", "商品选项未填写!");
            return map;
        }
        if (StringUtils.isBlank(good.getGoodIntroduction())) {
            map.put("goodInstructionMsg", "商品说明未填写!");
            return map;
        }
        Good g = goodsMapper.selectGoodByName(good.getGoodName());
        if (g != null) {
            map.put("goodNameMsg", "该商品名称已被占用!");
            return map;
        }

        goodsMapper.insertGood(good);

        return map;

    }

    // 根据商品id删除对应商品
    public int deleteGoodByGoodId(int goodId) {
        return goodsMapper.deleteGoodById(goodId);
    }

    // 根据多个商品id删除多个商品
    public int deleteGoodsByGoodsIds(int[] goodsIds) {
        return goodsMapper.deleteGoodsByIds(goodsIds);
    }

    // 根据商品id查询商品
    public Good findGoodById(int goodsId) {
        return goodsMapper.selectGoodById(goodsId);
    }

}
