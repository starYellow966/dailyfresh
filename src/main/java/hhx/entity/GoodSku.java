package hhx.entity;


import java.io.Serializable;
import java.util.Date;

public class GoodSku implements Serializable {
    private Long skuId;
    private String skuName;
    private Integer sock;
    private Double price;
    private Integer priority;
    private String spec;  // 以json格式存储的属性列表
    private String skuImgAddr;
    private String skuBriefInfo;
    private GoodSPU goodSPU;
    private Date createTime;
    private Date lastEditTime;

    @Override
    public String toString() {
        return "GoodSku{" +
                "skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", sock=" + sock +
                ", price=" + price +
                ", priority=" + priority +
                ", spec='" + spec + '\'' +
                ", skuImgAddr='" + skuImgAddr + '\'' +
                ", skuBriefInfo='" + skuBriefInfo + '\'' +
                ", goodSPU=" + goodSPU +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                '}';
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getSock() {
        return sock;
    }

    public void setSock(Integer sock) {
        this.sock = sock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSkuImgAddr() {
        return skuImgAddr;
    }

    public void setSkuImgAddr(String skuImgAddr) {
        this.skuImgAddr = skuImgAddr;
    }

    public String getSkuBriefInfo() {
        return skuBriefInfo;
    }

    public void setSkuBriefInfo(String skuBriefInfo) {
        this.skuBriefInfo = skuBriefInfo;
    }

    public GoodSPU getGoodSPU() {
        return goodSPU;
    }

    public void setGoodSPU(GoodSPU goodSPU) {
        this.goodSPU = goodSPU;
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
