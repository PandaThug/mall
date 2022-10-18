package com.example.mall;

import com.example.mall.dao.UserMapper;
import com.example.mall.entity.User;
import com.example.mall.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = MallApplication.class)
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testRegister() {
        User user = new User("10", "Tom", "123456",  "1");
        System.out.println(user);
        Map<String, Object> map = userService.register(user);
        System.out.println(map.size());
        System.out.println(map);
        User user1 = userMapper.selectById("6");
        System.out.println(user1 + "---");
    }

    @Test
    public void testInsertUser() {
        User jerry = new User("1", "Jerry", "123", "1");
        userMapper.insertUser(jerry);
        System.out.println(jerry);
    }

}
