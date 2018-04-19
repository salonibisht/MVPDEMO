package com.example.appinventiv.finalmvpdemo.samplesplash;

import com.example.appinventiv.finalmvpdemo.base.BasePresenter;
import com.example.appinventiv.finalmvpdemo.samplehome.HomeView;

/**
 * Created by appinventiv on 18/4/18.
 */

public class SplashPresenter extends BasePresenter<SplashView> {

    public SplashPresenter(SplashView view) {
        super(view);
    }



    @Override
    protected void setModel() {

    }

    @Override
    protected void destroy() {

    }

    @Override
    protected void initView() {

    }

    public void setDelay() {
        getView().fireIntentToHome();
    }
}
