package hhx.dao;

import hhx.BaseTest;
import hhx.entity.GoodSPU;
import hhx.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;
    @Autowired
    private GoodSPUDao goodSPUDao;

    @Test
    public void testInsertLine(){
        GoodSPU goodSPU = goodSPUDao.querySpuBySpuId(3);
        HeadLine headLine = new HeadLine();
        headLine.setSpu(goodSPU);
        headLine.setImgAddr(goodSPU.getImgAddr());
        headLine.setPriority(2);

        assert 1 == headLineDao.addHeadLine(headLine);
    }

    @Test
    public void testQueryAll(){
        List<HeadLine> lineList = headLineDao.queryAll();
        assert 3 == lineList.size();
    }
}
