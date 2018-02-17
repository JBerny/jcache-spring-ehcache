package org.bernysoft.testcache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.cache.CacheManager;
import javax.cache.Caching;

@org.springframework.context.annotation.Configuration
@EnableCaching
@ComponentScan
@Slf4j
public class Configuration {

	public Configuration() {
		log.info("Loading configuration");
	}

	@Bean
	@Qualifier("cacheManager")
	public org.springframework.cache.CacheManager cacheManager() {
		CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
		return new JCacheCacheManager(cacheManager);
	}

	@Bean
	@Qualifier("jCacheManager")
	public CacheManager jCacheManager(){
		return Caching.getCachingProvider().getCacheManager();
	}
}
