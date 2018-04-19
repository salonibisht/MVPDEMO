package com.example.appinventiv.finalmvpdemo.samplehome;

import com.example.appinventiv.finalmvpdemo.base.BaseModel;

/**
 * Created by appinventiv on 17/4/18.
 */

public class HomeModel  extends BaseModel<HomeModelListener> {
    public HomeModel(HomeModelListener listener) {
        super(listener);
    }
    protected void getNotificationInfo(){
        getListener().onRetrieveNotification(getDataManager().retrieveNotification());

    }


    public void deleteRow(int id) {
       getDataManager().deleteNotification(id);
    }
}
