package com.example.mall.service;

import com.example.mall.dao.CommentMapper;
import com.example.mall.dao.OrderMapper;
import com.example.mall.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private OrderMapper orderMapper;

    public int getCommentCountOfGood(int storeId, int goodId) {
        return commentMapper.selectCommentCountOfGood(storeId, goodId);
    }

    // 为商品添加评价
    public Map<String, Object> addComment(int orderId, int commentScore, String commentText) {

        Map<String, Object> map = new HashMap<>();

        int goodId = orderMapper.selectGoodIdByOrderId(orderId);
        int storeId = orderMapper.selectStoreIdByOrderId(orderId);

        Comment comment = new Comment(0, commentScore, commentText, storeId, goodId);

        int i = commentMapper.insertComment(comment);
        if (i != 1) {
            map.put("msg", "评价失败!");
        }

        return map;

    }

}
