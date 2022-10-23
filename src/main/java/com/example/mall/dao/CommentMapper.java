package com.example.mall.dao;

import com.example.mall.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 获取评论数
    int selectCommentCountOfGood(@Param("storeId") int storeId, @Param("goodId") int goodId);

    // 添加商品评价
    int insertComment(Comment comment);

    // 根据商品id获取商品评价列表
    List<Comment> selectCommentsByGoodsId(@Param("goodsId") int goodsId);

}
