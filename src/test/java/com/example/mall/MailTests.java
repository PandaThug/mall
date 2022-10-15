package com.example.mall;

import com.example.mall.utils.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = MallApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Test
    public void testMail() {
        mailClient.sendMail("1746904635@qq.com", "test", "hi");
    }

}
