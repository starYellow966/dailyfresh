package hhx.service.impl;

import hhx.dao.GoodSPUDao;
import hhx.dao.GoodSkuDao;
import hhx.entity.GoodSPU;
import hhx.entity.GoodSku;
import hhx.service.GoodSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodSkuServiceImpl implements GoodSkuService {
    @Autowired
    private GoodSkuDao goodSkuDao;
    @Autowired
    private GoodSPUDao goodSPUDao;

    @Override
    @Transactional
    public List<GoodSku> querySkuBySpuId(int spuId) {
        try {
            GoodSPU goodSPU = goodSPUDao.querySpuBySpuId(spuId);
            List<GoodSku> goodSkus = goodSkuDao.querySkuBySpuId(spuId);
            for (GoodSku sku : goodSkus)
                sku.setGoodSPU(goodSPU);
            return goodSkus;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public GoodSku querySkuBySkuId(int skuId) {
        try {
            return goodSkuDao.querySkuBySkuId(skuId);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
