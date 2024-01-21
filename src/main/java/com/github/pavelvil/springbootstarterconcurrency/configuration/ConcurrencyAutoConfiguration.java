package com.github.pavelvil.springbootstarterconcurrency.configuration;

import com.github.pavelvil.springbootstarterconcurrency.aspect.LockAspect;
import com.github.pavelvil.springbootstarterconcurrency.aspect.UseCyclicBarrierAspect;
import com.github.pavelvil.springbootstarterconcurrency.aspect.UseSemaphoreAspect;
import com.github.pavelvil.springbootstarterconcurrency.bean.BarrierAction;
import com.github.pavelvil.springbootstarterconcurrency.bean.BarrierActionImpl;
import com.github.pavelvil.springbootstarterconcurrency.configuration.properties.ConcurrencyProperties;
import com.github.pavelvil.springbootstarterconcurrency.filter.ConditionalOnCyclicBarrierCondition;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(ConcurrencyProperties.class)
@ConditionalOnProperty(prefix = "concurrency", value = "enabled", havingValue = "true", matchIfMissing = false)
public class ConcurrencyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public BarrierAction barrierAction() {
        return new BarrierActionImpl();
    }

    @Bean
    @ConditionalOnExpression("${concurrency.lock-enabled:false}")
    public LockAspect lockAspect() {
        return new LockAspect();
    }

    @Bean
    @ConditionalOnCyclicBarrierCondition
    public UseCyclicBarrierAspect useCyclicBarrierAspect(BarrierAction barrierAction) {
        return new UseCyclicBarrierAspect(barrierAction);
    }

    @Bean
    @ConditionalOnProperty(prefix = "concurrency", value = "semaphore-enabled", havingValue = "true",
            matchIfMissing = true)
    public UseSemaphoreAspect useSemaphoreAspect() {
        return new UseSemaphoreAspect();
    }
}
