package hhx.dao;

import com.alibaba.fastjson.JSONObject;
import hhx.BaseTest;
import hhx.entity.GoodSPU;
import hhx.entity.GoodSku;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GoodSkuDaoTest  extends BaseTest {
    @Autowired
    private GoodSkuDao goodSkuDao;

    @Test
    public void testInsertSku(){
        GoodSPU spu = new GoodSPU();
        spu.setSpuId(1);

        GoodSku sku = new GoodSku();
        sku.setGoodSPU(spu);
        sku.setCreateTime(new Date());
        sku.setLastEditTime(new Date());
        sku.setPriority(0);
        JSONObject jsonObject = null;

        spu.setSpuId(42);
        sku.setSkuName("小颗粒草莓，一盒28颗");
        sku.setSock(100);
        sku.setPrice(20.00);
        jsonObject = new JSONObject();
        jsonObject.put("unit", "盒装草莓");
        sku.setSpec(jsonObject.toJSONString());
        assert 1 == goodSkuDao.insertSku(sku);

        sku.setSkuName("牛奶草莓水果新鲜");
        sku.setSock(100);
        sku.setPrice(10.00);
        jsonObject = new JSONObject();
        jsonObject.put("unit", "草莓500g");
        sku.setSpec(jsonObject.toJSONString());
        assert 1 == goodSkuDao.insertSku(sku);

//        sku.setSkuName("华为 HUAWEI P30 超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机天空之境全网通双4G手机");
//        sku.setSock(100);
//        sku.setPrice(3988.00);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("mem", "8GB+128GB");
//        jsonObject.put("color", "天空之境");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);

//        sku.setSkuName("华为 HUAWEI P30 超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机天空之境全网通双4G手机");
//        sku.setSock(100);
//        sku.setPrice(3988.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "6GB+128GB");
//        jsonObject.put("color", "天空之境");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("华为 HUAWEI P30 超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机亮黑色全网通双4G手机");
//        sku.setSock(100);
//        sku.setPrice(3988.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "6GB+128GB");
//        jsonObject.put("color", "亮黑色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("华为 HUAWEI P30 超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机亮黑色全网通双4G手机");
//        sku.setSock(100);
//        sku.setPrice(3988.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "8GB+128GB");
//        jsonObject.put("color", "亮黑色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);

//        sku.setSkuName("华为 HUAWEI P30 超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机极光色全网通双4G手机");
//        sku.setSock(100);
//        sku.setPrice(3988.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "6GB+128GB");
//        jsonObject.put("color", "极光色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("华为 HUAWEI P30 超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机极光色全网通双4G手机");
//        sku.setSock(100);
//        sku.setPrice(3988.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "8GB+128GB");
//        jsonObject.put("color", "极光色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);

//        spu.setSpuId(2);
//        sku.setSkuName("华为 HUAWEI P30 Pro 超感光徕卡四摄10倍混合变焦麒麟980芯片屏内指纹天空之境全网通版双4G手机");
//        sku.setSock(100);
//        sku.setPrice(5488.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "8GB+256GB");
//        jsonObject.put("color", "天空之境");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("华为 HUAWEI P30 超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机天空之境全网通双4G手机");
//        sku.setSock(100);
//        sku.setPrice(4988.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "8GB+128GB");
//        jsonObject.put("color", "天空之境");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("华为 HUAWEI P30 Pro 超感光徕卡四摄10倍混合变焦麒麟980芯片屏内指纹亮黑色全网通版双4G手机");
//        sku.setSock(100);
//        sku.setPrice(5488.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "8GB+256GB");
//        jsonObject.put("color", "亮黑色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("华为 HUAWEI P30 超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机亮黑色全网通双4G手机");
//        sku.setSock(100);
//        sku.setPrice(4988.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "8GB+128GB");
//        jsonObject.put("color", "亮黑色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("华为 HUAWEI P30 Pro 超感光徕卡四摄10倍混合变焦麒麟980芯片屏内指纹赤茶橘全网通版双4G手机");
//        sku.setSock(100);
//        sku.setPrice(5488.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "8GB+256GB");
//        jsonObject.put("color", "亮黑色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("华为 HUAWEI P30 超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机赤茶橘全网通双4G手机");
//        sku.setSock(100);
//        sku.setPrice(6288.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "8GB+512GB");
//        jsonObject.put("color", "亮黑色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);

//        spu.setSpuId(3);
//        sku.setSkuName("Apple iPhone XR (A2108) 蓝色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(6299.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "256GB");
//        jsonObject.put("color", "蓝色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("Apple iPhone XR (A2108) 蓝色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(5199.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "128GB");
//        jsonObject.put("color", "蓝色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("Apple iPhone XR (A2108) 黑色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(6299.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "256GB");
//        jsonObject.put("color", "黑色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("Apple iPhone XR (A2108) 黑色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(5199.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "128GB");
//        jsonObject.put("color", "黑色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("Apple iPhone XR (A2108) 白色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(6299.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "256GB");
//        jsonObject.put("color", "白色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("Apple iPhone XR (A2108) 白色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(5199.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "128GB");
//        jsonObject.put("color", "白色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);

//        spu.setSpuId(3);
//        sku.setSkuName("Apple iPhone XS Max (A2104) 金色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(8899.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "256GB");
//        jsonObject.put("color", "金色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("Apple iPhone XR (A2108) 金色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(7799.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "64GB");
//        jsonObject.put("color", "金色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("Apple iPhone XS Max (A2104) 银色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(8899.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "256GB");
//        jsonObject.put("color", "银色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("Apple iPhone XR (A2108) 银色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(7799.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "64GB");
//        jsonObject.put("color", "银色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("Apple iPhone XS Max (A2104) 深空灰色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(8899.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "256GB");
//        jsonObject.put("color", "深空灰色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("Apple iPhone XR (A2108) 深空灰色 移动联通电信4G手机 双卡双待");
//        sku.setSock(100);
//        sku.setPrice(7799.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("mem", "64GB");
//        jsonObject.put("color", "深空灰色");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);

        // 电脑
//        spu.setSpuId(5);
//        sku.setSkuName("联想(Lenovo)拯救者Y7000P 2019英特尔酷睿i715.6英寸游戏笔记本电脑(i7-9750H 16G 1T SSD GTX1660Ti 144Hz)");
//        sku.setSock(100);
//        sku.setPrice(9299.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("color", "Y7000P2019|i7 16G 1660Ti电竞屏");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("联想(Lenovo)拯救者Y7000 2019英特尔酷睿i5 15.6英寸游戏笔记本电脑(i5-9300H 8G 512G SSD GTX1050 3G IPS)");
//        sku.setSock(100);
//        sku.setPrice(5699.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("color", "Y7000 2019|i5 512 GTX1050 3G");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        spu.setSpuId(6);
//        sku.setSkuName("联想(Lenovo)小新Air14英寸英特尔酷睿i5 超轻薄笔记本电脑(I5-10210U 12G 512G MX250 72％NTSC)轻奢灰");
//        sku.setSock(100);
//        sku.setPrice(5299.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("color", "【十代i5 12G 512G MX250】灰");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("联想(Lenovo)小新Air15.6英寸英特尔酷睿i5超轻薄笔记本电脑(I5-10210U 12G 1TSSD MX250 72%NTSC )轻奢灰");
//        sku.setSock(100);
//        sku.setPrice(5499.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("color", "新品【十代i5 12G 1TSSD MX250】");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        spu.setSpuId(7);
//        sku.setSkuName("戴尔DELL游匣G3 15.6英寸英特尔酷睿i5游戏笔记本电脑(i5-9300H 8G 512G GTX1650 4G 72色域 2年整机上门)");
//        sku.setSock(100);
//        sku.setPrice(6099.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("color", "爆款i5|GTX1650 4G|512G|72色域");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
//
//        sku.setSkuName("戴尔DELL游匣G3 15.6英寸电竞游戏笔记本电脑(九代i7-9750H 8G 1TSSD GTX1660TiMQ 6G 72色域 2年整机上门)");
//        sku.setSock(100);
//        sku.setPrice(8399.00);
//        jsonObject = new JSONObject();
//        jsonObject.put("color", "新品i7|1660TiMQ|1TSSD|72色域");
//        sku.setSpec(jsonObject.toJSONString());
//        assert 1 == goodSkuDao.insertSku(sku);
    }

    @Test
    public void testQuerySkuBySpuId(){
        List<GoodSku> goodSkus = goodSkuDao.querySkuBySpuId(1);
        System.out.println(goodSkus);

        for (GoodSku sku:goodSkus){
            String spec = sku.getSpec();
            JSONObject jsonObject = JSONObject.parseObject(spec);
            System.out.println(jsonObject.values().toString());
            sku.setSpec(jsonObject.values().toString());
        }

        assert goodSkus.size() == 6;
    }

    @Test
    public void testQuerySkuBySkuId(){
        GoodSku goodSku = goodSkuDao.querySkuBySkuId(2l);
        System.out.println(goodSku);
    }
}
