package com.example.mall.controller;

import com.example.mall.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/commentManage/add", method = RequestMethod.POST)
    @ResponseBody
    public String addCommentForGoods(Model model, String orderId, String commentScore, String commentText) {

        Map<String, Object> map = commentService.addComment(Integer.parseInt(orderId), Integer.parseInt(commentScore), commentText);

        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "评论成功!");

        } else {
            model.addAttribute("msg", map.get("msg"));
        }

        return model.toString();

    }

}
