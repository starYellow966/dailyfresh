package hhx.service.impl;

import hhx.dao.GoodTypeDao;
import hhx.entity.GoodType;
import hhx.service.GoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodTypeServiceImpl implements GoodTypeService {

    @Autowired
    private GoodTypeDao goodTypeDao;

    @Override
    public List<GoodType> queryAllType() {
        try {
            return goodTypeDao.queryAllType();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public GoodType queryTypeById(Integer typeId) {
        try {
            GoodType goodType = new GoodType();
            goodType.setTypeId(typeId);
            return goodTypeDao.queryType(goodType);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public GoodType queryTypeByName(String typeName) {
        try {
            GoodType goodType = new GoodType();
            goodType.setTypeName(typeName);
            return goodTypeDao.queryType(goodType);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
