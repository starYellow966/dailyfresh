package hhx.dao;

import hhx.BaseTest;
import hhx.entity.OrderDetail;
import hhx.enums.PayStatusEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderDetailDaoTest extends BaseTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void testInsertOrderDetail(){
        OrderDetail orderDetail = OrderDetail.createOrder(1,2,1,10, 10, 10, 0);
        orderDetailDao.insertOrderDetail(orderDetail);
    }

    @Test
    public void testQueryOrderDetail(){
        List<OrderDetail> orderDetail = orderDetailDao.queryAllOrderDetailByUserId(1);
        int a = 1;
        a =2 ;
    }

    @Test
    public void testUpdatePayStatus(){
        orderDetailDao.updatePayStatus("20191006143124263", PayStatusEnum.PAID.getStatusCode());
    }
}
