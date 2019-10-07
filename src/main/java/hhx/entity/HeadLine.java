package hhx.entity;

/**
 * 首页滚动信息
 */
public class HeadLine {
    private Integer lineId;
    private String imgAddr;
    private Integer priority;
    private Integer isDelete;
    private GoodSPU spu;

    @Override
    public String toString() {
        return "HeadLine{" +
                "lineId=" + lineId +
                ", imgAddr='" + imgAddr + '\'' +
                ", priority=" + priority +
                ", isDelete=" + isDelete +
                ", spu=" + spu +
                '}';
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public GoodSPU getSpu() {
        return spu;
    }

    public void setSpu(GoodSPU spu) {
        this.spu = spu;
    }
}
