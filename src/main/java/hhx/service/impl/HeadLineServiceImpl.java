package hhx.service.impl;

import hhx.dao.HeadLineDao;
import hhx.entity.HeadLine;
import hhx.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> queryAll() {
        try {
            return headLineDao.queryAll();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
