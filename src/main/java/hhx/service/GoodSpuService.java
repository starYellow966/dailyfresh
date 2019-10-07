package hhx.service;

import hhx.entity.GoodSPU;

import java.util.List;
import java.util.Map;

public interface GoodSpuService {
    public int insertGoodSPU(GoodSPU goodSPU);
    public List<GoodSPU> querySpuByTypeId(int typeId);
    public List<GoodSPU> querySpuByTypeName(String typeName);

    /**
     * 获得每种商品类型的topN个spu
     * @return
     */
    public Map<Object, Object> queryTopNSpu(int n);
    public GoodSPU querySpuBySpuId(int spuId);
    /**
     * 支持多种查询条件的查询,对于文本数据采用like模糊查询
     * @param goodSPU
     * @return
     */
    public List<GoodSPU> querySpu(GoodSPU goodSPU);

    /**
     * 获得最新修改的TopN
     * @param n
     * @return
     */
    public List<GoodSPU> queryNewNSpu(int n);
}
