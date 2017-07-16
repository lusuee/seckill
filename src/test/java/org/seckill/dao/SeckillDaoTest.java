package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SecKill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * SeckillDao ceshilei
 * Created by lusuee on 17-7-16.
 */

//peizhi spring he junit zhenghe, junit qidongshi jiazai springIOC rongqi
@RunWith(SpringJUnit4ClassRunner.class)
// gaosu junit spring de peizhi wenjian
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

	// zhu ru dao shi xian lei yilai
	@Resource
	private SeckillDao seckillDao;

	@Test
	public void queryById() throws Exception {
		long id = 1000;
		SecKill secKill = seckillDao.queryById(id);
		System.out.println(secKill.getName());
		System.out.println(secKill);
	}

	@Test
	public void queryAll() throws Exception {
		List<SecKill> list = seckillDao.queryAll(0, 4);
		System.out.println(list.toString());
	}

	@Test
	public void reduceNumber() throws Exception {
		Date date = new Date();
		int updateCount = seckillDao.reduceNumber(1000L, date);
		System.out.println(updateCount);

	}

}