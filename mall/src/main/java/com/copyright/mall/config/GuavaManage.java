package com.copyright.mall.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * GuavaManage
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/17 3:30 下午
 */
@Component
public class GuavaManage {

    private Cache<String, Optional<Object>> localMemoryCache;

    @PostConstruct
    public void init() {
      localMemoryCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build();
    }

    public Cache<String, Optional<Object>> getLocalMemoryCache() {
      return localMemoryCache;
    }


    public Optional<Object> getCache(String key, Callable<? extends Optional<Object>> method){
      // 缓存读取
      Optional<Object>  infoOptional= Optional.empty();
      if (null == localMemoryCache) {
        return infoOptional;
      }
      try {
        infoOptional = localMemoryCache.get(
          key,
          method);
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
      return infoOptional;
    }
}
