package cn.itbeien.common.core.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Component
public class RedisCacheUtils<T> {

	@Autowired
	private  RedisTemplate<String, T> redisTemplate;

	/**
	 * 
	 * 方法用途:写入缓存 <br>
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, T value) {
		boolean result = false;
		try {
			ValueOperations<String, T> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 
	 * 方法用途:写入单个对象到缓存(可以设置有效时间) <br>
	 * 
	 * @param key
	 * @param value
	 * @param expireTime 有效时间 单位秒
	 * @return
	 */
	public boolean set(final String key, T value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<String, T> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * 
	 * 方法用途:写入List到缓存缓存 <br>
	 * 
	 * @param key
	 * @param
	 * @return
	 */
	public boolean setList(final String key, List<T> dataList) {
		boolean result = false;
		try {
			ListOperations <String, T> listOperation  = redisTemplate.opsForList();
			if (null != dataList) {
	            int size = dataList.size();
	            for (int i = 0; i < size; i++) {
	                listOperation.leftPush(key, dataList.get(i));
	            }
	        }
			result = true;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * 
	 * 方法用途:写入单个对象到缓存(可以设置有效时间) <br>
	 * 
	 * @param key
	 * @param value
	 * @param expireTime 有效时间 单位秒
	 * @return
	 */
	public boolean setList(final String key, List<T> dataList, Long expireTime) {
		boolean result = false;
		try {
			ListOperations<String, T> listOperation = redisTemplate.opsForList();
			if (null != dataList) {
	            int size = dataList.size();
	            for (int i = 0; i < size; i++) {
	                listOperation.leftPush(key, dataList.get(i));
	            }
	        }
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 
	 * 方法用途:读取缓存 <br>
	 * 
	 * @param key
	 * @return
	 */
	public T get(final String key) {
		T result = null;
		ValueOperations<String, T> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}
	
	/**
	 * 
	 * 方法用途:返回一个字符串，也就是键的旧值。 如果键不存在，则返回nil <br>
	 * 
	 * @param key
	 * @return
	 */
	public T getSet( final String key, T value) {
		T result = null;
		ValueOperations<String, T> operations = redisTemplate.opsForValue();
		result = operations.getAndSet(key, value);
		return result;
	}
	
	
	/**
	 * 
	 * 方法用途:读取List缓存 【 0 ，-1 代表所有值】 <br>
	 * @param key
	 * @return
	 */
	public List<T> getList(final String key) {
		List<T> dataList = new ArrayList<T>();
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);

        for (int i = 0; i < size; i++) {
            dataList.add(listOperation.index(key,i));
        }
        return dataList;
	}

	/**
	 * 方法用途:批量删除对应的value  <br>
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 方法用途:批量删除key  <br>
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 方法用途:删除对应的value  <br>
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 方法用途: 判断缓存中是否有对应的value <br>
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

}
