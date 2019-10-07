package hhx.dao;

import hhx.entity.GoodSku;

import java.util.List;

public interface GoodSkuDao {

    public int insertSku(GoodSku goodSku);

    public List<GoodSku> querySkuBySpuId(int spuId);

    public GoodSku querySkuBySkuId(long skuId);
}
