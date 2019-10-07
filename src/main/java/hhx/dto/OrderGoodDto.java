package hhx.dto;

import hhx.entity.OrderGood;

public class OrderGoodDto {
    private int skuId;
    private String skuName;
    private double price;
    private int count;
    private String comment;
    private String orderId;
    private String imgAddr;
    private String spec;

    public OrderGoodDto(){}
    public OrderGoodDto(OrderGood orderGood){
        this.skuId = orderGood.getSkuId();
        this.count = orderGood.getNum();
        this.price = orderGood.getPrice();
        this.comment = orderGood.getComment();
        this.orderId = orderGood.getOrderId();
    }

    @Override
    public String toString() {
        return "OrderGoodDto{" +
                "skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", comment='" + comment + '\'' +
                ", orderId='" + orderId + '\'' +
                ", imgAddr='" + imgAddr + '\'' +
                ", spec='" + spec + '\'' +
                '}';
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
