package hhx.service;

import hhx.entity.GoodType;

import java.util.List;

public interface GoodTypeService {
    public List<GoodType> queryAllType();
    public GoodType queryTypeById(Integer typeId);
    public GoodType queryTypeByName(String typeName);
}
