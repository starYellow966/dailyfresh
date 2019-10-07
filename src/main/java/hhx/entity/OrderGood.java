package hhx.entity;

import java.util.Date;

/**
 * 记录订单中商品信息。一个订单（OrderDetail）包含多个订单商品（OrderGood）
 */
public class OrderGood {
    private String orderId;  // 自行生成的订单ID，不是mysql中主键
    private Integer skuId;
    private Integer num;
    private Double price;  // 每件sku的单价
    private String comment;  // 评论
    private Date createTime;
    private Date lastEditTime;

    public static OrderGood createGood(String orderId, int skuId, int num, double price, String comment){
        OrderGood orderGood = new OrderGood();
        orderGood.setOrderId(orderId);
        orderGood.setSkuId(skuId);
        orderGood.setNum(num);
        orderGood.setPrice(price);
        orderGood.setComment(comment);
        orderGood.setCreateTime(new Date());
        orderGood.setLastEditTime(new Date());
        return orderGood;
    }

    @Override
    public String toString() {
        return "OrderGood{" +
                "orderId='" + orderId + '\'' +
                ", skuId=" + skuId +
                ", num=" + num +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
