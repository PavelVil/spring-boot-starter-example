package com.github.pavelvil.springbootstarterconcurrency.aspect;

import com.github.pavelvil.springbootstarterconcurrency.annotation.UseCyclicBarrier;
import com.github.pavelvil.springbootstarterconcurrency.bean.BarrierAction;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

@Aspect
public class UseCyclicBarrierAspect {

    private static final Logger LOG = LoggerFactory.getLogger(UseCyclicBarrierAspect.class);

    private final Map<String, CyclicBarrier> barriers = new ConcurrentHashMap<>();

    private final BarrierAction barrierAction;

    public UseCyclicBarrierAspect(BarrierAction barrierAction) {
        this.barrierAction = barrierAction;
    }

    @Pointcut("@annotation(useCyclicBarrier)")
    public void useCyclicBarrierPointcut(UseCyclicBarrier useCyclicBarrier) {
    }

    @Before("useCyclicBarrierPointcut(useCyclicBarrier)")
    public void beforeUseCyclicBarrier(UseCyclicBarrier useCyclicBarrier) {
        String barrierName = useCyclicBarrier.barrier();
        CyclicBarrier barrier = barriers.computeIfAbsent(barrierName,
                k -> new CyclicBarrier(useCyclicBarrier.parties(), barrierAction::action));

        try {
            LOG.info("Поток " + Thread.currentThread().threadId() + " ждет у барьера.");

            barrier.await();

            LOG.info("Поток " + Thread.currentThread().threadId() + " пересек барьер.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
