package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized;
import org.seckill.entity.SecKill;

import java.util.Date;
import java.util.List;

/**
 * SecKill DAO jiekou
 * Created by lusuee on 17-7-15.
 */
public interface SeckillDao {

	/**
	 * jiankucun
	 *
	 * @param seckillId
	 * @param killTime
	 * @return ruguo yingxiang hangshu > 1, biaoshi gengxin chenggong
	 */
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

	/**
	 * genjuIdchaxunmiaoshaduixiang
	 *
	 * @param seckillId
	 * @return
	 */
	SecKill queryById(long seckillId);

	/**
	 * genjupianyiliangchaxunshangpinliebiao
	 *
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<SecKill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}
