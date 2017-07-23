package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.SecKill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * created by lusuee 2017/7/22
 */
public class RedisDao {

	private JedisPool pool;

	public RedisDao(String ip, int port) {
		pool = new JedisPool(ip, port);
	}

	private RuntimeSchema<SecKill> schema = RuntimeSchema.createFrom(SecKill.class);

	public SecKill getSecKill(long seckillId) {
		//redis操作逻辑
		try {
			Jedis jedis = pool.getResource();
			try {
				String key = "seckill:" + seckillId;
				//并没有实现内部序列化操作
				//get -> byte[] -> 反序列化 -> Object(SecKill)
				//高并发中的序列化效率问题
				//采用自定义序列化
				//protostuff: pojo
				byte[] bytes = jedis.get(key.getBytes());
				//缓存重获取
				if (bytes != null) {
					SecKill secKill = schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, secKill, schema);
					//secKill被反序列化
					return secKill;
				}
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String putSecKill(SecKill secKill) {
		//set Object(SecKill) -> 序列化 -> bytes[]
		String result = null;
		try {
			Jedis jedis = pool.getResource();
			try {
				String key = "seckill:" + secKill.getSeckillId();
				byte[] bytes = ProtostuffIOUtil.toByteArray(secKill, schema,
						LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				//超时缓存
				int timeout = 60 * 60;
				result = jedis.setex(key.getBytes(), timeout, bytes);
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
