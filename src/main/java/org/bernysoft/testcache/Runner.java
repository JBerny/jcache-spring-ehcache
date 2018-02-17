package org.bernysoft.testcache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

@Slf4j
@Component
public class Runner implements Runnable {
	@Autowired
	private CacheManager jCacheManager;

	public void run() {
		MutableConfiguration<Long, String> configuration =
				new MutableConfiguration<Long, String>()
						.setTypes(Long.class, String.class)
						.setStoreByValue(false)
						.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));
		Cache<Long, String> cache = jCacheManager.createCache("jCache", configuration);
		cache.put(1L, "one");
		String value = cache.get(1L);
		log.info("value is {}",value);
	}
}
