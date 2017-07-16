package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * 
 * SuccessKilled DAO jiekou
 * Created by lusuee on 17-7-15.
 */
public interface SuccessKilledDao {

	/**
	 * charu goumai mingxi, ke guolv chongfu
	 *
	 * @param seckillId
	 * @param userPhone
	 * @return charu de hangshu
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

	/**
	 * genju id chaxun SuccessKilled bing xiedai miaosha chanpin duixiang shiti
	 *
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(long seckillId);

}
