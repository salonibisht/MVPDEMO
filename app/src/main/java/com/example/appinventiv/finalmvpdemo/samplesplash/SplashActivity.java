package com.example.appinventiv.finalmvpdemo.samplesplash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appinventiv.finalmvpdemo.R;
import com.example.appinventiv.finalmvpdemo.base.BaseActivity;
import com.example.appinventiv.finalmvpdemo.base.BaseView;
import com.example.appinventiv.finalmvpdemo.samplehome.HomeActivity;
import com.example.appinventiv.finalmvpdemo.samplehome.HomePresentor;

public class SplashActivity extends BaseActivity implements SplashView {
private SplashPresenter mSplashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashPresenter = new SplashPresenter(this);
        mSplashPresenter.setDelay();
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    public void showToastLong(String message) {

    }

    @Override
    public void fireIntentToHome() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        }, 3000);
    }
}
