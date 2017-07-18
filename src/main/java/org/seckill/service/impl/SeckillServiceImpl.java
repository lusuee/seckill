package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SecKill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 秒杀业务service实现类
 * Created by lusuee on 2017/7/16.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillDao seckillDao;

	@Autowired
	private SuccessKilledDao successKilledDao;

	//md5盐值字符串，混淆作用
	private final String salt = "fdjsfu1kk1rir!#$!@#$~JlkjsfousofqefoiSDFE";

	public List<SecKill> getSeckillList() {
		return seckillDao.queryAll(0, 4);
	}

	public SecKill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		SecKill secKill = seckillDao.queryById(seckillId);
		if (secKill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = secKill.getStartTime();
		Date endTime = secKill.getEndTime();
		Date now = new Date();
		if (startTime.getTime() > now.getTime()
				|| endTime.getTime() < now.getTime()) {
			return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), endTime.getTime());
		}
		//转化特定字符串的过程，不可逆
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	private String getMD5(long seckillId) {
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Transactional
	/**
	 * 使用注解控制事务方法的优点：
	 * 1、开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2、保证事务方法的执行时间尽可能短，不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部
	 * 3、不是所有的方法都需要事务，如只有一条修改操作、只读操作不需要事务
	 */
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}
		try {
			//执行秒杀逻辑：减库存 + 记录购买行为
			//减库存
			Date nowTime = new Date();
			int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
			if (updateCount <= 0) {
				//没有更新到记录，秒杀结束
				throw new SeckillCloseException("seckill is closed");
			}
			//记录购买行为
			int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
			if (insertCount <= 0) {
				throw new RepeatKillException("seckill repeated");
			}
			SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
			return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
		} catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//所有编译器异常，转化为运行期异常
			throw new SeckillException("seckill inner error:" + e.getMessage());
		}
	}
}
