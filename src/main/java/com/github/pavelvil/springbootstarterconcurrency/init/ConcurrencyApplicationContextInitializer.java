package com.github.pavelvil.springbootstarterconcurrency.init;

import com.github.pavelvil.springbootstarterconcurrency.bean.ThreadsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ConcurrencyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger LOG = LoggerFactory.getLogger(ConcurrencyApplicationContextInitializer.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        LOG.info("Вызов ConcurrencyApplicationContextInitializer");
        applicationContext.getBeanFactory().registerSingleton(ThreadsInfo.class.getSimpleName(), new ThreadsInfo());
    }
}
