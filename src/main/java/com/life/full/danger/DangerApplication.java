package com.life.full.danger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LiHongHui
 * @date 2018/1/12 14:58
 * @description
 */
@SpringBootApplication
@MapperScan(basePackages= {"com.life.full.danger.*.dao"})
public class DangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DangerApplication.class, args);
	}
}
