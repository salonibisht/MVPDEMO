package com.example.appinventiv.finalmvpdemo.data;

import com.example.appinventiv.finalmvpdemo.pojo.NotificationInfoBean;

import java.util.ArrayList;

/**
 * Created by appinventiv on 16/4/18.
 */

public interface IDataManager {
    void saveNotification(NotificationInfoBean notificationInfoBean);
    ArrayList retrieveNotification();
    void updateNotification(NotificationInfoBean notificationInfoBean,int i);
    void deleteNotification(int id);
}
