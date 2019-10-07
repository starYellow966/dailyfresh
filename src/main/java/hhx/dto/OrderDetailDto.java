package hhx.dto;

import hhx.entity.OrderDetail;

import java.util.Date;
import java.util.List;

/**
 * 用于用户中心的全部订单页面
 */
public class OrderDetailDto {
    private String orderId;
    private Date createTime;
    private String payStatus;
    private double freight;
    private double totalAmount;
    private List<OrderGoodDto> orderGoodDtos;

    OrderDetailDto(){}

    public OrderDetailDto(OrderDetail orderDetail){
        this.setCreateTime(orderDetail.getCreateTime());
        this.setFreight(orderDetail.getFreight());
        this.setOrderId(orderDetail.getOrderId());
        this.setTotalAmount(orderDetail.getTotalAmount());
        this.setPayStatus(orderDetail.getPayStatus().getStatusName());
    }

    @Override
    public String toString() {
        return "OrderDetailDto{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", payStatus='" + payStatus + '\'' +
                ", freight=" + freight +
                ", totalAmount=" + totalAmount +
                ", orderDetailDtos=" + orderGoodDtos +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderGoodDto> getOrderGoodDtos() {
        return orderGoodDtos;
    }

    public void setOrderGoodDtos(List<OrderGoodDto> orderGoodDtos) {
        this.orderGoodDtos = orderGoodDtos;
    }
}
