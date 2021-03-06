package com.example.appinventiv.finalmvpdemo.base;

import java.lang.ref.SoftReference;

/**
 * Created by appinventiv on 16/4/18.
 */

public abstract class BasePresenter<T extends BaseView> implements BaseModelListener {

    private SoftReference<T> view;

    public BasePresenter(T view) {
        attachView(view);
        setModel();
    }

    public void attachView(T view) {
        this.view = new SoftReference<T>(view);
    }

    public T getView() {
        return (view == null) ? null : view.get();
    }

    public void detachView() {
        view = null;
        destroy();
    }

    protected abstract void setModel();

    protected abstract void destroy();

    protected abstract void initView();
}
