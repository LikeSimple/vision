package org.vision.wechat.common;


import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisClient {
  
  @Resource
  private RedisTemplate<Serializable, Object> redisTemplate;
  
  private static Logger log = LoggerFactory.getLogger(RedisClient.class);

  /**
   * 删除对应的value
   *
   * @param key
   */
  public void remove(final String key) {
    if (this.exists(key)) {
      this.redisTemplate.delete(key);
    }
  }

  /**
   * 判断缓存中是否有对应的value
   *
   * @param key
   * @return
   */
  public boolean exists(final String key) {
    try {
      return this.redisTemplate.hasKey(key);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return false;
  }

  /**
   * 读取缓存
   *
   * @param key
   * @return
   */
  public Object get(final String key) {
    log.info(redisTemplate.getConnectionFactory().getConnection().getClientList().toString());
    Object result = null;
    ValueOperations<Serializable, Object> operations = this.redisTemplate.opsForValue();
    result = operations.get(key);
    return result;
  }

  /**
   * 写入缓存
   *
   * @param key
   * @param value
   * @return
   */
  public boolean set(final String key, Object value) {
    boolean result = false;
    try {
      ValueOperations<Serializable, Object> operations = this.redisTemplate.opsForValue();
      operations.set(key, value);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 写入缓存
   *
   * @param key
   * @param value
   * @return
   */
  public boolean set(final String key, Object value, Long expireTime) {
    boolean result = false;
    try {
      ValueOperations<Serializable, Object> operations = this.redisTemplate.opsForValue();
      operations.set(key, value);
      this.redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

}
