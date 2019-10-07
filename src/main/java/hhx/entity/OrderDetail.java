package hhx.entity;

import hhx.enums.PayMethodEnum;
import hhx.enums.PayStatusEnum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderDetail {
    private String orderId;  // 自行生成的订单ID，不是mysql中主键
    private Integer addrId;  // 收件人地址的ID
    private Integer userId;  // 收件人ID
    private PayMethodEnum payMethod;  // 支付方式
    private PayStatusEnum payStatus;  // 订单状态
    private List<OrderGood> orderGoodList; // 该订单包含的商品(并不在数据表中)
    private Double totalAmount;  // 订单总金额
    private Integer totalCount;  // 订单总数目
    private Double freight;  // 运费
    private Date createTime;  // 订单创建时间

    private void createOrderId(){
        Date now = new Date();
        String temp = new SimpleDateFormat("yyyyMMddHHmmss").format(now);  // 日期
        temp += String.valueOf(100 + (int)(Math.random()*(999-100+1))); // 加上三位随机数
        this.orderId = temp;
    }

    /**
     * 创建一个订单
     * @param addrId
     * @param userId
     * @param payMethodCode
     * @param freight
     * @return
     */
    public static OrderDetail createOrder(int addrId, int userId, int payMethodCode, double freight,
                                          double totalAmount, int totalCount, int payStatusCode){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.createOrderId();
        orderDetail.setAddrId(addrId);
        orderDetail.setUserId(userId);
        orderDetail.setPayMethod(PayMethodEnum.indexOf(payMethodCode));
        orderDetail.setFreight(freight);
        orderDetail.setTotalAmount(totalAmount);
        orderDetail.setTotalCount(totalCount);
        orderDetail.setCreateTime(new Date());
        orderDetail.setPayStatus(PayStatusEnum.indexOf(payStatusCode));
        return orderDetail;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId='" + orderId + '\'' +
                ", addrId=" + addrId +
                ", userId=" + userId +
                ", payMethod=" + payMethod +
                ", orderGoodList=" + orderGoodList +
                ", totalAmount=" + totalAmount +
                ", totalCount=" + totalCount +
                ", feight=" + freight +
                ", createTime=" + createTime +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public PayMethodEnum getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethodEnum payMethod) {
        this.payMethod = payMethod;
    }

    public List<OrderGood> getOrderGoodList() {
        return orderGoodList;
    }

    public void setOrderGoodList(List<OrderGood> orderGoodList) {
        this.orderGoodList = orderGoodList;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PayStatusEnum getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatusEnum payStatus) {
        this.payStatus = payStatus;
    }
}
