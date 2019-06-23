package com.sjh.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.sjh.shardingjdbc.mapper")
public class ShardingjdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingjdbcApplication.class, args);
	}

}
