package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * SuccessKilledDao ceshilei
 * Created by lusuee on 17-7-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

	@Resource
	private SuccessKilledDao successKilledDao;

	@Test
	public void insertSuccessKilled() throws Exception {
		long id = 1000L;
		long userPhone = 15898850250L;
		int insertCount = successKilledDao.insertSuccessKilled(id, userPhone);
		System.out.println(insertCount);
	}

	@Test
	public void queryByIdWithSeckill() throws Exception {
		long id = 1000L;
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id);
		System.out.println(successKilled);
	}

}