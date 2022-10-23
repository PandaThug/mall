package com.example.mall.dao;

import com.example.mall.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {

    // 获取评论数
    int selectCommentCountOfGood(@Param("storeId") int storeId, @Param("goodId") int goodId);

    // 添加商品评价
    int insertComment(Comment comment);

}
