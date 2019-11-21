package com.framework.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.framework.bean.common.SystemConstant;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.SortingParams;

public class RedisUtil {
	
	private static Integer expire = 60 * 30; // sessionId缓存半小时
	
	private static String host = "127.0.0.1";

	public static JedisPool jedisPool;// 切片连接池
	
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			// 设置最大连接数
			config.setMaxTotal(300);
			// 设置最大空闲数
			config.setMaxIdle(600);
			// 设置超时时间
			config.setMaxWaitMillis(3000);
			// 在应用初始化的时候生成连接池
			jedisPool = new JedisPool(config, host, 6379);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向缓存中设置字符串内容 失败返回0 不覆盖 成功 返回1
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @return
	 * @throws Exception
	 */
	public static void set(String key, String value) {
		Jedis client = null;
		try {
			// 从切片池中获取实例
			client = jedisPool.getResource();
			client.set(SystemConstant.prefix + key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
	}

	public static void setex(String key, String value) {
		Jedis client = null;
		try {
			// 从切片池中获取实例
			client = jedisPool.getResource();
			client.setex(SystemConstant.prefix + key, expire, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
	}

	public static void setex(String key, Integer seconds, String value) {
		Jedis client = null;
		try {
			// 从切片池中获取实例
			client = jedisPool.getResource();
			client.setex(SystemConstant.prefix + key, seconds, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
	}

	public static void expire(String key, Integer seconds) {
		Jedis client = null;
		try {
			// 从切片池中获取实例
			client = jedisPool.getResource();
			client.expire(SystemConstant.prefix + key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
	}

	public static void expire(String key) {
		Jedis client = null;
		try {
			// 从切片池中获取实例
			client = jedisPool.getResource();
			client.expire(SystemConstant.prefix + key, expire);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
	}

	/**
	 * 删除缓存中得对象，根据key
	 * 
	 * @param key
	 * @return
	 */
	public static boolean del(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(SystemConstant.prefix + key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedis.close();
		}

	}

	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String result = jedis.get(SystemConstant.prefix + key);
			if (result == null || result.equals("null")) {
				result = null;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedis.close();
		}

	}

	public static Integer getInteger(String key) {
		String result = get(key);
		if (StringUtils.isBlank(result)) {
			return 0;
		} else {
			try {
				return Integer.parseInt(result);
			} catch (Exception ex) {
				return 0;
			}
		}
	}

	/**
	 * 根据key 获取对象
	 * 
	 * @param key
	 * @return
	 */
	public static <T> T get(String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get(SystemConstant.prefix + key);
			return JSON.parseObject(value, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedis.close();
		}
	}

	/***
	 * 检查key是否存在
	 * 
	 * @param key
	 * @return true 存在 false 不存在
	 */
	public static boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(SystemConstant.prefix + key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedis.close();
		}

	}

	/***
	 * 往指定的key追加内容，key不在则添加key
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean appendStr(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.append(SystemConstant.prefix + key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedis.close();
		}
	}

	/***************************************
	 * hashes(哈希)类型
	 *********************************************************/

	/**
	 * 设置hash field 如果存在不会设置返回0
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return 成功返回1,失败 0
	 */
	public static long hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hset(SystemConstant.prefix + key, field, value);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			jedis.close();
		}
		return 0;

	}

	/**
	 * hget取值(value)
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public static Object hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hget(SystemConstant.prefix + key, field);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedis.close();
		}
	}

	/**
	 * hmset 批量设置值
	 * 
	 * @param key
	 * @param hashmap
	 * @return 成功返回OK
	 */
	public static String hmset(String key, Map<String, String> hashmap) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hmset(SystemConstant.prefix + key, hashmap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return null;
	}

	/**
	 * hmget 批量取值(value)
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public static Object hmget(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hmget(SystemConstant.prefix + key, fields);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedis.close();
		}
	}

	/**
	 * @param key
	 * @return 返回所有的key和value
	 */
	public static Map<String, String> hgetall(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hgetAll(SystemConstant.prefix + key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedis.close();
		}
	}

	/**
	 * 指定自增，负数自减
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public static long hincrby(String key, String field, long value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hincrBy(SystemConstant.prefix + key, field, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return 0;
	}

	/***
	 * 删除一个或多个哈希表
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */

	public static long hdel(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hdel(SystemConstant.prefix + key, fields);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return 0;
	}

	/***************************************
	 * list(列表)
	 *********************************************************/

	/**
	 * lpush 设置值 从头部压入一个元素
	 * 
	 * @param key
	 * @param strings
	 * @return 成功返回成员的数量 失败返回0
	 */
	public static long lpush(String key, String... strings) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lpush(SystemConstant.prefix + key, strings);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return 0;
	}

	/**
	 * rpush 从尾部压入一个元素
	 * 
	 * @param key
	 * @param strings
	 * @return 成功返回成员的数量 失败返回0
	 */
	public static long rpush(String key, String... strings) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.rpush(SystemConstant.prefix + key, strings);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return 0;
	}

	/**
	 * list列表取值(lrange)
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return start=0 end=-1(代表从开始到结束)
	 */
	public static Object lrange(String key, long start, long end) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lrange(SystemConstant.prefix + key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return 0;
	}

	/**
	 * 从头部删除元素，并返回删除元素
	 * 
	 * @param key
	 * @return
	 */
	public static String lpop(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			return jedis.lpop(SystemConstant.prefix + key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return null;

	}

	/**
	 * 根据下表获取元素
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public static String lindex(String key, int index) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lindex(SystemConstant.prefix + key, index);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return null;
	}

	/**
	 * 获取列表对应的长度
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public static long llen(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.llen(SystemConstant.prefix + key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return 0;
	}

	/***************************************
	 * set集合
	 *********************************************************/

	/**
	 * 向集合成添加一个或多个成员
	 * 
	 * @param key
	 * @param members
	 * @return
	 */
	public static long sadd(String key, String... members) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.sadd(SystemConstant.prefix + key, members);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return 0;
	}

	/**
	 * 获取集合的成员个数
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public static long scard(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.scard(SystemConstant.prefix + key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return 0;
	}

	/**
	 * 返回集合中的所有成员
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public static Set<String> smembers(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.smembers(SystemConstant.prefix + key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return null;
	}

	/***************************************
	 * sorted set
	 ******************************************************/

	/**
	 * 向有序集合中添加一个或多个成员，或者更新已存在成员的分数
	 * 
	 * @param key
	 * @param scoreMembers
	 * @return
	 */
	public static long zadd(String key, Map<String, Double> scoreMembers) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zadd(SystemConstant.prefix + key, scoreMembers);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return 0;
	}

	/***
	 * 排序
	 * 
	 * @param key
	 * @param sortingParameters
	 * @return
	 */
	public static List<String> sort(String key, SortingParams sortingParameters) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.sort(SystemConstant.prefix + key, sortingParameters);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return null;
	}

	/***
	 * 排序 从大到小
	 * 
	 * @param key
	 * @param sortingParameters
	 * @return
	 */
	public static List<String> sort(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			SortingParams sort = new SortingParams();
			sort.desc();
			return jedis.sort(SystemConstant.prefix + key, sort);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return null;
	}
}