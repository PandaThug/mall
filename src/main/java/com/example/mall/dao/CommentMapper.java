package com.example.mall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {

    // 获取评论数
    int selectCommentCountOfGood(@Param("storeId") int storeId, @Param("goodId") int goodId);

}
