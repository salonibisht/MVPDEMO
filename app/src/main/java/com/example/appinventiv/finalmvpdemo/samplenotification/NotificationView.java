package com.example.appinventiv.finalmvpdemo.samplenotification;

import com.example.appinventiv.finalmvpdemo.base.BaseView;

/**
 * Created by appinventiv on 17/4/18.
 */

public interface NotificationView extends BaseView{
   void todoError();
   void descriptionError();
   void dateError();
   void timeError();
   void setAlarm();
   void successfullySaved();
}
