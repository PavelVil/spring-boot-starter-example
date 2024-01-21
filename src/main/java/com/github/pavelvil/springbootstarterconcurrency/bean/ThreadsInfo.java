package com.github.pavelvil.springbootstarterconcurrency.bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThreadsInfo {

    private final Logger LOG = LoggerFactory.getLogger(ThreadsInfo.class);

    private final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    public void printThreadsInfo() {
        ThreadInfo[] allThreads = threadMXBean.dumpAllThreads(true, true);
        for (ThreadInfo threadInfo : allThreads) {
            LOG.info("ID Потока: " + threadInfo.getThreadId());
            LOG.info("Имя потока: " + threadInfo.getThreadName());
            LOG.info("Статус Потока: " + threadInfo.getThreadState());
            LOG.info("---------------------");
        }
    }

}
