package com.example.appinventiv.finalmvpdemo.samplehome;

import com.example.appinventiv.finalmvpdemo.base.BaseView;
import com.example.appinventiv.finalmvpdemo.pojo.NotificationInfoBean;

import java.util.ArrayList;

/**
 * Created by appinventiv on 16/4/18.
 */

public interface HomeView extends BaseView {
    void showNotification(ArrayList<NotificationInfoBean> notificationList);
    void intentFireAddTododetails();
    void intentEditTodoDetails(int id);

}
