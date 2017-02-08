package com.example.interactor.base;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by huii on 17/1/5.
 */
public abstract class CaseController<T> extends Subscriber<T>{
    Subscription subscription;

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public void terminate() {
        if (this.subscription != null) {
            if (!this.subscription.isUnsubscribed()) {
                this.subscription.unsubscribe();
            }
        }
    }
}
