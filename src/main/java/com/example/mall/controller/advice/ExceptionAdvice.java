package com.example.mall.controller.advice;

import com.example.mall.utils.MallUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({Exception.class})
    public void handleException(Exception e, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.error("服务器发生异常:" + e.getMessage());
        for (StackTraceElement element : e.getStackTrace()) {
            logger.error(element.toString());
        }
        String xRequestedWith = req.getHeader("x-requested-with");
        if ("XMLHttpRequest".equals(xRequestedWith)) {
            resp.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.write(MallUtil.getJSONString(1, "服务器异常!"));
        } else {
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}
