package hhx.dao;


import hhx.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailDao {
    int insertOrderDetail(OrderDetail orderDetail);

    List<OrderDetail> queryAllOrderDetailByUserId(@Param("userId")int userId);

    int updatePayStatus(@Param("orderId") String orderId, @Param("payStatusCode") int payStatusCode);

    OrderDetail queryOrderDetailByOrderId(String orderId);
}
