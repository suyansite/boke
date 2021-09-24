package com.databaker.hyzx;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		long t = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			//System.out.println(IdsGen.BASIC.getIdGenStr());
		}
		long t1 = System.currentTimeMillis();
		System.out.println("耗时-->" + (t1 - t));

	}

	@Test
	public void contextLoads1() {
		/*SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 1);
		for (int i = 0; i < 1000; i++) {
			long id = idWorker.nextId();
			System.out.println(Long.toBinaryString(id));
			System.out.println(id);
		}*/

	}

	@Test
	public void contextLoads2() {
		/*SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 1);
		for (int i = 0; i < 1000; i++) {
			long id = idWorker.nextId();
			System.out.println(Long.toBinaryString(id));
			System.out.println(id);
		}*/

	}

}
