package hhx.dao;

import hhx.BaseTest;
import hhx.entity.OrderGood;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderGoodDaoTest extends BaseTest {

    @Autowired
    private OrderGoodDao orderGoodDao;

    @Test
    public void testInsertOrderGood(){
        OrderGood orderGood = OrderGood.createGood("20191006143124263", 4, 2, 4188, "");
        orderGoodDao.insertOrderGood(orderGood);
    }


}
