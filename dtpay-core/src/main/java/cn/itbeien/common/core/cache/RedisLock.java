package cn.itbeien.common.core.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Slf4j
public class RedisLock {
	
	private RedisTemplate redisTemplate;

	private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 50;

	/**
	 * Lock key path.
	 */
	private String lockKey;

	/**
	 * 锁超时时间，防止线程在入锁以后，无限的执行等待
	 */
	private int expireMsecs = 60 * 1000;

	/**
	 * 锁等待时间
	 */
	private int timeoutMsecs = 10 * 1000;

	private volatile boolean locked = false;

	
	public RedisLock(RedisTemplate redisTemplate, String lockKey) {
		this.redisTemplate = redisTemplate;
		this.lockKey = lockKey + "_lock";
	}

	
	public RedisLock(RedisTemplate redisTemplate, String lockKey, int timeoutMsecs) {
		this(redisTemplate, lockKey);
		this.timeoutMsecs = timeoutMsecs;
	}

	
	public RedisLock(RedisTemplate redisTemplate, String lockKey, int timeoutMsecs, int expireMsecs) {
		this(redisTemplate, lockKey, timeoutMsecs);
		this.expireMsecs = expireMsecs;
	}

	/**
	 * @return lock key
	 */
	public String getLockKey() {
		return lockKey;
	}

	private String get(final String key) {
		Object obj = null;
		try {
			obj = redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					StringRedisSerializer serializer = new StringRedisSerializer();
					byte[] data = connection.get(serializer.serialize(key));
					connection.close();
					if (data == null) {
						return null;
					}
					return serializer.deserialize(data);
				}
			});
		} catch (Exception e) {
			log.error("get redis error, key : {}", key);
		}
		return obj != null ? obj.toString() : null;
	}

	private boolean setNX(final String key, final String value) {
		Object obj = null;
		try {
			obj = redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					StringRedisSerializer serializer = new StringRedisSerializer();
					Boolean success = connection.setNX(serializer.serialize(key), serializer.serialize(value));
					connection.close();
					return success;
				}
			});
		} catch (Exception e) {
			log.error("setNX redis error, key : {}", key);
		}
		return obj != null ? (Boolean) obj : false;
	}

	private String getSet(final String key, final String value) {
		Object obj = null;
		try {
			obj = redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					StringRedisSerializer serializer = new StringRedisSerializer();
					byte[] ret = connection.getSet(serializer.serialize(key), serializer.serialize(value));
					connection.close();
					return serializer.deserialize(ret);
				}
			});
		} catch (Exception e) {
			log.error("setNX redis error, key : {}", key);
		}
		return obj != null ? (String) obj : null;
	}

	/**
	 * 获得 lock. 实现思路: 主要是使用了redis 的setnx命令,缓存了锁. reids缓存的key是锁的key,所有的共享,
	 * value是锁的到期时间(注意:这里把过期时间放在value了,没有时间上设置其超时时间) 执行过程:
	 * 1.通过setnx尝试设置某个key的值,成功(当前没有这个锁)则返回,成功获得锁
	 * 2.锁已经存在则获取锁的到期时间,和当前时间比较,超时的话,则设置新的值
	 *
	 * @return true 代表已经获得锁, false 代表未获得锁
	 * @throws InterruptedException
	 */
	public synchronized boolean lock() throws InterruptedException {
		log.debug("当前线程值为："+Thread.currentThread().getName());
		
		int timeout = timeoutMsecs;
		while (timeout >= 0) {
			long expires = System.currentTimeMillis() + expireMsecs + 1;
			log.debug("当前线程值为："+Thread.currentThread().getName()+"超时时间："+expires);
			String expiresStr = String.valueOf(expires); // 锁到期时间
			if (this.setNX(lockKey, expiresStr)) {
				// lock acquired
				log.debug(Thread.currentThread().getName() + "初次获得锁");
				locked = true;
				return true;
			}

			String currentValueStr = this.get(lockKey); // redis里的时间
			if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
				// 判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
				// lock is expired
				String oldValueStr = this.getSet(lockKey, expiresStr);
				// 获取上一个锁到期时间，并设置现在的锁到期时间，
				// 只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
				if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
					// [分布式的情况下]:如果这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
					// lock acquired
					log.debug(Thread.currentThread().getName() + "获得锁");
					locked = true;
					return true;
				}
			}
			timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;
			log.debug("获取锁失败：剩余时间"+timeout);

			/*
			 * 延迟100 毫秒
			 */
			Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
			log.debug("获取锁失败：剩余时间"+(double)(timeout/1000));

		}
		return false;
	}

	/**
	 * Acqurired lock release.
	 */
	public synchronized void unlock() {
		if (locked) {
			log.info("释放锁");
			redisTemplate.delete(lockKey);
			locked = false;
		}
	}
	
	/**
	 * 分布式锁使用流程
	 * 方法用途: <br>
	 */
	public void test(){
		 RedisLock lock = new RedisLock(redisTemplate, "key", 10000, 20000);
		 try {
		      if(lock.lock()) {
		         //需要加锁的代码
		    	  
		      }
		 } catch (InterruptedException e) {
			 log.error("分布式锁异常:"+e.getMessage(),e);
		 }finally {
		   //为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做del操作，因为可能客户端因为某个耗时的操作而挂起，
		   lock.unlock();
		}
	}
}
