package org.bernysoft.testcache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

@Slf4j
public class Main{


	public static void main(String... args){
		ApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
		Runnable runnable = context.getBean(Runner.class);
		assert runnable != null;
		Thread t = new Thread(runnable);
		t.setDaemon(true);
		log.info("Starting app...");
		t.start();
		try {
			t.join();
			synchronized (Thread.currentThread()) {
				Thread.currentThread().wait();
			}
		} catch (InterruptedException e) {
			log.error("Exception in main thread ",e);
		}
	}


}
