package com.sjh.shardingjdbc;

import com.sjh.shardingjdbc.pojo.po.TTestPO;
import com.sjh.shardingjdbc.service.TTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingjdbcApplicationTests {

	@Autowired
	private TTestService tTestService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void insert() {
		TTestPO tTestPO = new TTestPO();
		tTestPO.setName("jack");
		tTestService.insert(tTestPO);

	}

}
