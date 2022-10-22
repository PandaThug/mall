package com.example.mall.service;

import com.example.mall.dao.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public int getCommentCountOfGood(int storeId, int goodId) {
        return commentMapper.selectCommentCountOfGood(storeId, goodId);
    }

}
