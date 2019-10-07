package hhx.entity;

import java.util.Date;
import java.util.Objects;

public class GoodSPU {
    private Integer spuId;
    private String spuName;
    private String spuDetail;
    private Date createTime;
    private Date lastEditTime;
    private GoodType goodType;
    private Integer isDelete;
    private String imgAddr;

    @Override
    public String toString() {
        return "GoodSPU{" +
                "spuId=" + spuId +
                ", spuName='" + spuName + '\'' +
                ", spuDetail='" + spuDetail + '\'' +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", goodType=" + goodType +
                ", isDelete=" + isDelete +
                ", imgAddr='" + imgAddr + '\'' +
                '}';
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpuDetail() {
        return spuDetail;
    }

    public void setSpuDetail(String spuDetail) {
        this.spuDetail = spuDetail;
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

    public GoodType getGoodType() {
        return goodType;
    }

    public void setGoodType(GoodType goodType) {
        this.goodType = goodType;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    /**
     * 当id相同，则认为两个对象相同
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodSPU goodSPU = (GoodSPU) o;
        return Objects.equals(spuId, goodSPU.spuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spuId);
    }
}
