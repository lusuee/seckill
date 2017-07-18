package org.seckill.exception;

/**
 * 重复秒杀异常（运行期异常）
 * spring 事务，只接收运行期异常
 * Created by lusuee on 2017/7/16.
 */
public class RepeatKillException extends SeckillException {

	public RepeatKillException(String message) {
		super(message);
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}
}
