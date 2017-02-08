package com.example.interactor.base;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by huii on 17/1/5.
 */
public class CaseCore {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();


    public CaseCore(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

//    protected abstract Observable buildCaseObservable();

    @SuppressWarnings("unchecked")
    public void execute(Observable observable, CaseController caseController) {
        caseController.setSubscription(
                observable.subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(caseController));
    }

    public void terminate() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
