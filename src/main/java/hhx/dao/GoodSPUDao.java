package hhx.dao;

import hhx.entity.GoodSPU;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodSPUDao {
    public int insertGoodSPU(GoodSPU goodSPU);
    public List<GoodSPU> querySpuByTypeId(int typeId);
    public List<GoodSPU> queryTopNSpuByTypeId(@Param("typeId") int typeId, @Param("n") int n);
    public GoodSPU querySpuBySpuId(int spuId);

    /**
     * 支持多种查询条件的查询
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
