package com.example.mall;

import com.example.mall.dao.GoodsMapper;
import com.example.mall.dao.OrderMapper;
import com.example.mall.entity.Good;
import com.example.mall.entity.Order;
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
public class TestMapper {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testMapper() {
        List<Good> goods = goodsMapper.selectGoodsByStoreId(5);
        for (Good good : goods) {
            System.out.println(good);
        }
    }

    @Test
    public void testMapper2() {
        Good good = goodsMapper.selectGoodByName("221");
        System.out.println(good);
    }

    @Test
    public void testMapper3() {
        int i = goodsMapper.deleteGoodById(10);
        System.out.println(i);
    }

    @Test
    public void testMapper4() {
        int i = goodsMapper.deleteGoodsByIds(new int[]{7, 8, 9});
        System.out.println(3);
    }

    @Test
    public void testMapper5() {
        String s = "123";
        int i = Integer.parseInt(s);
        System.out.println(i);
    }

    @Test
    public void testMapper6() {
        List<Good> goods = goodsMapper.selectGoodBySales();
        for (Good good : goods) {
            System.out.println(good);
        }
    }

    @Test
    public void testMapper7() {
        List<Good> goods = goodsMapper.selectGoodHasStringBySales("bao");
        for (Good good : goods) {
            System.out.println(good);
        }
    }

    @Test
    public void testMapper8() {
        Good good = goodsMapper.selectGoodById(17);
        System.out.println(good);
    }

    @Test
    public void testMapper9() {
        int i = orderMapper.insertOrder(new Order(1, 0, 0, 0, 1, 100,
                "test", "option", 0, "address", "110", "tom"));
        System.out.println(i);
    }

    @Test
    public void testMapper10() {
        List<Order> orders = orderMapper.selectOrdersByUserId(0);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

}
