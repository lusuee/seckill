package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SecKill;
import org.seckill.entity.SuccessKilled;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * SeckillService实现的测试类
 * Created by lusuee on 2017/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

	private final Logger logger = LoggerFactory.getLogger("test");

	@Autowired
	private SeckillService seckillServiceImpl;

	@Test
	public void getSeckillList() throws Exception {
		List<SecKill> list = seckillServiceImpl.getSeckillList();
		//System.out.println("list--->" + list);
		logger.info("list={}", list);
	}

	@Test
	public void getById() throws Exception {
		long seckillId = 1000L;
		SecKill secKill = seckillServiceImpl.getById(seckillId);
		System.out.println(secKill);
	}

	/**
	 * 集成测试完整代码，可重复执行
	 */
	@Test
	public void seckillLogic() throws Exception {
		long id = 1000L;
		Exposer exposer = seckillServiceImpl.exportSeckillUrl(id);
		if (exposer.isExposed()) {
			logger.info("exposer={}", exposer);
			long userPhone = 15898850251L;
			String md5 = exposer.getMd5();
			try {
				SeckillExecution seckillExecution = seckillServiceImpl.executeSeckill(id, userPhone, md5);
				logger.info("seckillExecution: {}", seckillExecution);
			} catch (SeckillCloseException e) {
				logger.error(e.getMessage());
			} catch (RepeatKillException e) {
				logger.error(e.getMessage());
			}
		} else {
			//警告：秒杀未开启
			logger.warn("expoer={}", exposer);
		}
	}
}