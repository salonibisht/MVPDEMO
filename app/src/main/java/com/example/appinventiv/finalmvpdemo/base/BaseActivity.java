package com.example.appinventiv.finalmvpdemo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.appinventiv.finalmvpdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {



    RelativeLayout baseContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        baseContainer = findViewById(R.id.base_container);
        setLayout();
        ButterKnife.bind(this);
    }

    private void setLayout() {
        if (getResourceId() != -1) {
            removeLayout();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                View view = inflater.inflate(getResourceId(), null);
                baseContainer.addView(view, layoutParams);
            }
        }
    }

    private void removeLayout() {
        if (baseContainer.getChildCount() >= 1)
            baseContainer.removeAllViews();
    }


    protected abstract int getResourceId();



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}
