package hhx.dao;

import hhx.BaseTest;
import hhx.entity.GoodSPU;
import hhx.entity.GoodType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class GoodSPUDaoTest extends BaseTest {
    @Autowired
    private GoodSPUDao goodSPUDao;

    @Test
    public void testInsertGoodSPU(){
        GoodType goodType = new GoodType();
        goodType.setTypeId(4);

        GoodSPU goodSPU = new GoodSPU();
        goodSPU.setSpuDetail("");
        goodSPU.setGoodType(goodType);
        goodSPU.setCreateTime(new Date());
        goodSPU.setLastEditTime(new Date());

//        goodSPU.setSpuName("索尼（SONY）DSC-RX100M7");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodSPU.setSpuName("索尼（SONY）DSC-RX100M6");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodSPU.setSpuName("徕卡（Leica）相机 D-LUX7");
//        goodSPU.setSpuDetail("徕卡（Leica）相机 D-LUX7 便携式全自动对焦数码照相机 黑色19115");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodSPU.setSpuName("富士（FUJIFILM）X100F");
//        goodSPU.setSpuDetail("富士（FUJIFILM）X100F 数码旁轴相机 银色 人文扫街 2430万像素 混合取景器 复古 WIFI USB充电");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodSPU.setSpuName("松下（Panasonic）LX10");
//        goodSPU.setSpuDetail("松下（Panasonic）LX10 1英寸大底数码相机/卡片机 F1.4-2.8大光圈 触摸屏 Vlog相机 WIFI 4K全家桶");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodSPU.setSpuName("富士instax立拍立得 一次成像相机 mini7c");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodSPU.setSpuName("富士instax立拍立得 一次成像相机 mini9");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodSPU.setSpuName("索尼（SONY）Alpha 7");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodSPU.setSpuName("佳能（Canon）EOS RP 微单相机");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;

//        goodType.setTypeId(6);
//        goodSPU.setSpuName("草莓");
//        goodSPU.setSpuDetail("<p><strong>很不错的草莓</strong></p>");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("葡萄");
//        goodSPU.setSpuDetail("");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("柠檬");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("奇异果");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("苹果");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodType.setTypeId(7);
//        goodSPU.setSpuName("大青虾");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("秋刀鱼");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("扇贝");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("基围虾");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodType.setTypeId(8);
//        goodSPU.setSpuName("猪肉");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("牛肉");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("羊肉");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("牛排");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodType.setTypeId(9);
//        goodSPU.setSpuName("白菜");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("芹菜");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("香菜");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("冬瓜");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//
//        goodType.setTypeId(10);
//        goodSPU.setSpuName("鸡腿");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("鸡肉");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("鸭蛋");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
//        goodSPU.setSpuName("鸡蛋");
//        assert goodSPUDao.insertGoodSPU(goodSPU) == 1;
    }


    @Test
    public void testQuerySpuByTypeId(){
        List<GoodSPU> goodSPUList = goodSPUDao.querySpuByTypeId(1);
        assert goodSPUList.size() == 12;
    }

    @Test
    public void testQuerySpu(){
        GoodSPU goodSPU = new GoodSPU();
        goodSPU.setSpuName("手机");
        List<GoodSPU> list = goodSPUDao.querySpu(goodSPU);
        assert list != null;
    }

    @Test
    public void testQueryThreeSpuByTypeId(){
        System.out.println(goodSPUDao.queryTopNSpuByTypeId(6, 4));
    }

    @Test
    public void testQueryNewNSpu(){
        System.out.println(goodSPUDao.queryNewNSpu(3));
    }
}
