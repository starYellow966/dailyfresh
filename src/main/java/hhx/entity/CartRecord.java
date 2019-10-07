package hhx.entity;

public class CartRecord {
    private GoodSku goodSku;
    private Integer num;

    public CartRecord(GoodSku goodSku, Integer num) {
        this.goodSku = goodSku;
        this.num = num;
    }

    public CartRecord() {
    }

    @Override
    public String toString() {
        return "CartRecord{" +
                "goodSku=" + goodSku +
                ", num=" + num +
                '}';
    }

    public GoodSku getGoodSku() {
        return goodSku;
    }

    public void setGoodSku(GoodSku goodSku) {
        this.goodSku = goodSku;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
