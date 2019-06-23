package com.sjh.shardingjdbc.mapper;

import com.sjh.shardingjdbc.pojo.po.TTestPO;

import java.util.List;

/**
 * @author sjh
 * @Description: TODO
 * @date 2019/6/23 18:21
 */
public interface TTestMapper {

    int insert(TTestPO tTest);

    List<TTestPO> query();

    TTestPO queryOne(int id);
}
