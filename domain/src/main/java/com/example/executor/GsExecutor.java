package com.example.executor;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Created by huii on 17/1/10.
 */
public class GsExecutor implements ThreadExecutor {
    private static final int INITIAL_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;

    private static final int KEEP_ALIVE_TIME = 10;

    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private final BlockingDeque<Runnable> workQueue;

    private final ThreadPoolExecutor threadPoolExecutor;

    private final ThreadFactory threadFactory;

    @Inject
    public GsExecutor() {
        this.workQueue = new LinkedBlockingDeque<>();
        this.threadFactory = new JobThreadFactory();
        this.threadPoolExecutor = new ThreadPoolExecutor(INITIAL_POOL_SIZE,MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,KEEP_ALIVE_TIME_UNIT,this.workQueue,this.threadFactory);
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable ==null) {
            throw new IllegalArgumentException("Runnable to execute cannot be null");
        }
        this.threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private static final String THREAD_NAME = "android_";
        private int counter = 0;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable,THREAD_NAME + counter++);
        }
    }
}
