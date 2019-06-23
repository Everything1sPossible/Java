package com.sjh.shardingjdbc.service.impl;

import com.sjh.shardingjdbc.mapper.TTestMapper;
import com.sjh.shardingjdbc.pojo.po.TTestPO;
import com.sjh.shardingjdbc.service.TTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sjh
 * @Description: TODO
 * @date 2019/6/23 18:49
 */
@Service
@RequiredArgsConstructor
public class TTestServiceImpl implements TTestService {

    private final TTestMapper tTestMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void insert(TTestPO tTestPO) {
        int i = tTestMapper.insert(tTestPO);
        System.out.println("i::" + i);
        List<TTestPO> list = tTestMapper.query();
        System.out.println(list);
    }
}
