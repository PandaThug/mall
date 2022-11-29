package com.example.mall;

import com.example.mall.entity.Good;
import com.example.mall.entity.Order;
import com.example.mall.service.GoodsService;
import com.example.mall.service.OrderService;
import com.example.mall.service.StoreService;
import com.example.mall.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = MallApplication.class)
public class TestService {

    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @Test
    public void testStoreService() {
        List<Good> goods = storeService.findGoodsByStoreId(5);
        for (Good good : goods) {
            System.out.println(good);
        }
    }

    @Test
    public void testService() {
        int i = goodsService.deleteGoodByGoodId(13);
        System.out.println(i);
    }

    @Test
    public void testService2() {
        System.out.println(goodsService.deleteGoodsByGoodsIds(new int[]{14}));
    }

    @Test
    public void testService3() {
        List<Good> goods = storeService.findGoodsByGoodSales();
        for (Good good : goods) {
            System.out.println(good);
        }
    }

    @Test
    public void testService4() {
        List<Good> goods = storeService.findGoodsHaveString("ba");
        for (Good good : goods) {
            System.out.println(good);
        }
    }

    @Test
    public void testService5() {
        Good good = goodsService.findGoodById(17);
        System.out.println(good);
    }

    @Test
    public void testService6() {
        List<Order> orders = orderService.findOrdersByUserId(0);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void testService7() {
        System.out.println(userService.findAccountById(5));
    }

}
