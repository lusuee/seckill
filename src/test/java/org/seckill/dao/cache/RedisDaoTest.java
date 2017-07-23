package org.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.SecKill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * created by lusuee 2017/7/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RedisDaoTest {

	private long id = 1001L;

	@Autowired
	private RedisDao redisDao;

	@Autowired
	private SeckillDao seckillDao;

	@Test
	public void testSecKill() throws Exception {
		SecKill secKill = redisDao.getSecKill(id);
		if (secKill == null) {
			secKill = seckillDao.queryById(id);
			if (secKill != null) {
				String result = redisDao.putSecKill(secKill);
				System.out.println("result:" + result);
				secKill = redisDao.getSecKill(id);
				System.out.println(secKill);
			}
		}
	}

}