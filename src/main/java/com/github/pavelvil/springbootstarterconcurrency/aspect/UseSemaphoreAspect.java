package com.github.pavelvil.springbootstarterconcurrency.aspect;

import com.github.pavelvil.springbootstarterconcurrency.annotation.UseSemaphore;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Aspect
public class UseSemaphoreAspect {

    private static final Logger LOG = LoggerFactory.getLogger(UseSemaphoreAspect.class);

    private final Map<String, Semaphore> semaphores = new ConcurrentHashMap<>();

    @Pointcut("@annotation(useSemaphore)")
    public void useSemaphoreAspect(UseSemaphore useSemaphore) {}

    @Around("useSemaphoreAspect(useSemaphore)")
    public Object useSemaphoreAround(ProceedingJoinPoint joinPoint, UseSemaphore useSemaphore) throws Throwable {
        Semaphore semaphore = semaphores.computeIfAbsent(useSemaphore.target(), k -> new Semaphore(useSemaphore.permits()));
        try {
            semaphore.acquire();
            LOG.info("Семафор захвачен потоком: " + Thread.currentThread().threadId());
            return joinPoint.proceed();
        } finally {
            semaphore.release();
            LOG.info("Семафор отпущен потоком: " + Thread.currentThread().threadId());
        }
    }

}
