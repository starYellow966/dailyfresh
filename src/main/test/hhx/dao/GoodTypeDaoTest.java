package hhx.dao;

import hhx.BaseTest;
import hhx.entity.GoodType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GoodTypeDaoTest extends BaseTest {
    @Autowired
    private GoodTypeDao goodTypeDao;

    @Test
    public void testQueryAllType(){
        List<GoodType> result = goodTypeDao.queryAllType();
        assert result.size() == 5;
    }

    @Test
    public void testQueryType(){
        GoodType goodType = new GoodType();
//        goodType.setTypeId(1);
        goodType.setTypeName("电脑");
        System.out.println(goodTypeDao.queryType(goodType));
    }

    @Test
    public void testInsertType(){
        GoodType goodType = new GoodType();

        goodType.setTypeName("新鲜水果");
        goodType.setTypeEnName("fruit");
        assert goodTypeDao.insertType(goodType) == 1;

        goodType.setTypeName("海鲜水产");
        goodType.setTypeEnName("seafood");
        assert goodTypeDao.insertType(goodType) == 1;

        goodType.setTypeName("新鲜蔬菜");
        goodType.setTypeEnName("vegetables");
        assert goodTypeDao.insertType(goodType) == 1;

        goodType.setTypeName("猪牛羊肉");
        goodType.setTypeEnName("meet");
        assert goodTypeDao.insertType(goodType) == 1;

        goodType.setTypeName("禽类蛋品");
        goodType.setTypeEnName("egg");
        assert goodTypeDao.insertType(goodType) == 1;
    }

}
