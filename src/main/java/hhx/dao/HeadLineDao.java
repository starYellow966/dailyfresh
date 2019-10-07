package hhx.dao;

import hhx.entity.HeadLine;

import java.util.List;

public interface HeadLineDao {
    public int addHeadLine(HeadLine headLine);
    public List<HeadLine> queryAll();
}
