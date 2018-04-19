package com.example.appinventiv.finalmvpdemo.samplenotification;

import com.example.appinventiv.finalmvpdemo.base.BaseModel;
import com.example.appinventiv.finalmvpdemo.pojo.NotificationInfoBean;

/**
 * Created by appinventiv on 16/4/18.
 */

public class NotificationModel extends BaseModel<NotificationModelLisetener>
{
    public NotificationModel(NotificationModelLisetener listener) {
        super(listener);
    }

    protected void insertNotification(String todo, String description, String date, String time)
    {
        NotificationInfoBean  mNotificationInfoBean=new NotificationInfoBean(todo,description,date,time);
        getDataManager().saveNotification(mNotificationInfoBean);
    }

    public void updateNotification(String todo, String description, String date, String time,int i) {
        NotificationInfoBean  mNotificationInfoBean=new NotificationInfoBean(todo,description,date,time);
        getDataManager().updateNotification(mNotificationInfoBean,i);
    }
}
