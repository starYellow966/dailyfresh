package hhx.dao;

import hhx.entity.GoodType;

import java.util.List;

public interface GoodTypeDao {
    public List<GoodType> queryAllType();
    public GoodType queryType(GoodType goodType);
    public int insertType(GoodType goodType);
}
