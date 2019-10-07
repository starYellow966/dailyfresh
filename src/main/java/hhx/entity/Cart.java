package hhx.entity;

public class Cart {
    private Integer userId;
    private Integer skuId;
    private Integer num;

    public Cart(){}

    public Cart(Integer userId, Integer skuId, Integer num) {
        this.userId = userId;
        this.skuId = skuId;
        this.num = num;
    }

    @Override
    public String toString() {
        return "CartDao{" +
                "userId='" + userId + '\'' +
                ", skuId='" + skuId + '\'' +
                ", num=" + num +
                '}';
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
