package hhx.service.impl;

import com.alibaba.fastjson.JSONObject;
import hhx.dao.GoodSPUDao;
import hhx.dao.GoodTypeDao;
import hhx.entity.GoodSPU;
import hhx.entity.GoodType;
import hhx.service.GoodSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodSpuServiceImpl implements GoodSpuService {

    @Autowired
    private GoodSPUDao goodSPUDao;
    @Autowired
    private GoodTypeDao goodTypeDao;

    @Override
    public int insertGoodSPU(GoodSPU goodSPU) {
        try {
            int effectNum = goodSPUDao.insertGoodSPU(goodSPU);
            if (effectNum != 1)
                throw new RuntimeException("插入有误");
            else
                return effectNum;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<GoodSPU> querySpuByTypeId(int typeId) {
        try{
            List<GoodSPU> goodSPUList = goodSPUDao.querySpuByTypeId(typeId);
            return goodSPUList;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<GoodSPU> querySpuByTypeName(String typeName) {
        try {
            GoodType goodType = new GoodType();
            goodType.setTypeName(typeName);
            GoodType wholeType = goodTypeDao.queryType(goodType);

            return querySpuByTypeId(wholeType.getTypeId());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获得每种商品类型的topN个spu
     * @return 格式为{type_name1:spuList, type_name2:spuList,....}
     */
    @Override
    @Transactional
    public Map<Object, Object> queryTopNSpu(int n) {
        try{
            Map<Object, Object> data = new HashMap<>();
            List<GoodType> alltypes = goodTypeDao.queryAllType();  // 获得所有商品类型

            // 每种类型的前3个spu
            for (GoodType type : alltypes){
                List<GoodSPU> goodSPUList = goodSPUDao.queryTopNSpuByTypeId(type.getTypeId(), 4);
//                data.put(type.getTypeName(), goodSPUList);
                data.put(JSONObject.toJSON(type), goodSPUList);
            }

            return data;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public GoodSPU querySpuBySpuId(int spuId) {
        try{
            GoodSPU goodSPUList = goodSPUDao.querySpuBySpuId(spuId);
            return goodSPUList;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 支持多种查询条件的查询
     * @param goodSPU
     * @return
     */
    @Override
    public List<GoodSPU> querySpu(GoodSPU goodSPU) {
        try {
            return goodSPUDao.querySpu(goodSPU);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获得最新修改的TopN
     * @param n
     * @return
     */
    public List<GoodSPU> queryNewNSpu(int n){
        try{
            return goodSPUDao.queryNewNSpu(n);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
