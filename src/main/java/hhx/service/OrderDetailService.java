package hhx.service;

import hhx.dto.OrderDetailDto;
import hhx.dto.OrderGoodDto;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> getAllOrderDetailByUserId(int userId);

    /**
     * 当点击结算时，将选中的购物车信息临时存放到redis中
     * @param userId
     * @param data
     * @return key
     */
    String addTempCart(int userId, String data);
    List<OrderGoodDto> getTempCart(String key);
    void commitOrder(int addrId, int userId, int payMethodCode, String[] skus, String[] counts, String[] prices);

    OrderDetailDto getOrderDetailByOrderId(String orderId);
}