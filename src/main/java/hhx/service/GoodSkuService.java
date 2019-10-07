package hhx.service;

import hhx.entity.GoodSku;

import java.util.List;

public interface GoodSkuService {
    public List<GoodSku> querySkuBySpuId(int spuId);
    public GoodSku querySkuBySkuId(int skuId);
}
