package hhx.entity;

import java.util.Date;

public class Comment {
    private String orderId;
    private Integer userId;
    private Integer skuId;
    private String comment;
    private Date createTime;

    public Comment() {
        this.setCreateTime(new Date());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", skuId=" + skuId +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
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
}
