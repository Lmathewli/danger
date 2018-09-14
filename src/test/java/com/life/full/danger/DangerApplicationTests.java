package com.life.full.danger;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DangerApplicationTests {
	@Test
	public void test() {
		log.debug("debug...");
		log.info("info....");
        String name = "mathew";
        String password = "123456";
        log.info("name:{}, password:{}", name, password);
		log.error("error...");
	}

}
