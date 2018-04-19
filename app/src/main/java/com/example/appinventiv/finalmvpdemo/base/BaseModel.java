package com.example.appinventiv.finalmvpdemo.base;

import com.example.appinventiv.finalmvpdemo.data.DataManager;

import java.lang.ref.SoftReference;

/**
 * Created by appinventiv on 16/4/18.
 */

public abstract class BaseModel<T extends BaseModelListener> {

    public DataManager getDataManager() {
        return DataManager.getInstance();
    }
    private SoftReference<T> listener;

    public BaseModel(T listener) {
        this.listener = new SoftReference<>(listener);
    }

    public void attachListener(T listener) {
        this.listener = new SoftReference<>(listener);
    }

    public void detachListener() {
        this.listener = null;
    }

    public T getListener() {
        return (listener != null) ? listener.get() : null;
    }
}
