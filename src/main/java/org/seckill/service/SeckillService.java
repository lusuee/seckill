package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口：站在“使用者”角度设计接口
 * 三个方面：
 * 方法定义粒度，参数，返回类型（return 类型/exception）
 * Created by lusuee on 2017/7/16.
 */
public interface SeckillService {

	/**
	 * 查询所有秒杀记录
	 *
	 * @return secKill对象集合
	 */
	List<SecKill> getSeckillList();

	/**
	 * 查询单个秒杀记录
	 *
	 * @param seckillId
	 * @return
	 */
	SecKill getById(long seckillId);

	/**
	 * 秒杀开启时输出秒杀接口地址
	 * 否则输出系统时间和秒杀时间
	 *
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);

	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;

	SeckillExecution executeSeckillByProcedure(long seckillId, long userPhone, String md5);
}
