package org.seckill.exception;

/**
 * 秒杀业务相关异常
 * Created by lusuee on 2017/7/16.
 */
public class SeckillException extends RuntimeException {

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}
}
