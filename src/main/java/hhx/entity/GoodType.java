package hhx.entity;

public class GoodType {
    private Integer typeId;
    private String typeName;
    private String typeEnName;  // 英文名
    private Integer priority;
    private Integer isDelete;
    private String imgAddr;

    @Override
    public String toString() {
        return "GoodType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeEnName='" + typeEnName + '\'' +
                ", priority=" + priority +
                ", isDelete=" + isDelete +
                ", imgAddr='" + imgAddr + '\'' +
                '}';
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeEnName() {
        return typeEnName;
    }

    public void setTypeEnName(String typeEnName) {
        this.typeEnName = typeEnName;
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

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }
}
