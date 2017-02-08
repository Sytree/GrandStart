package com.example.executor;

import rx.Scheduler;

/**
 * Created by huii on 17/1/5.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
