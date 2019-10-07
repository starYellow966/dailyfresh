package hhx.service;

import hhx.BaseTest;
import hhx.entity.GoodSPU;
import org.junit.Test;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class GoodSpuServiceTest extends BaseTest {
    @Autowired
    private GoodSpuService goodSpuService;

    @Test
    public void testQuerySpu(){
        GoodSPU goodSPU = new GoodSPU();
        goodSPU.setSpuName("手机");
//        goodSPU.setSpuDetail("手机");  // 这种使得也会去detail字段查询是否包含该关键字
        List<GoodSPU> result = goodSpuService.querySpu(goodSPU);
        System.out.println(result);
        assert result != null;
    }

    @Test
    public void testQueryTopNSpu(){
        Map<Object, Object> result = goodSpuService.queryTopNSpu(4);
        int i = 10;
        i = 1-1;
    }
}
