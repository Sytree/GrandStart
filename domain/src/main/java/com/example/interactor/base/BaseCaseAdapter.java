package com.example.interactor.base;

import rx.Observable;

/**
 * Created by huii on 17/1/8.
 */
public abstract class BaseCaseAdapter<T> {
    CaseCore caseCore;

    public BaseCaseAdapter(CaseCore caseCore) {
        this.caseCore = caseCore;
    }

    public void executeCase(T param,CaseController caseController) {
        caseCore.execute(buildCaseObservable(param),caseController);
    }

    public abstract Observable buildCaseObservable(T param);
}
