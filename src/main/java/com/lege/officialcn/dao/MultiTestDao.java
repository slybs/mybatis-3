package com.lege.officialcn.dao;

import com.lege.officialcn.domain.MultiTest;
import org.apache.ibatis.annotations.Param;

/**
 * @Author 了个
 * @date 2020/1/10 14:39
 */
public interface MultiTestDao {
    public void insert(MultiTest multiTest);
    public void insert2(MultiTest multiTest);
    public void update(MultiTest multiTest);
    public MultiTest select(@Param("id") int id);
}
