package com.example.appinventiv.finalmvpdemo.data;

import com.example.appinventiv.finalmvpdemo.data.notificationdatabase.DatabaseHandler;
import com.example.appinventiv.finalmvpdemo.pojo.NotificationInfoBean;

import java.util.ArrayList;

/**
 * Created by appinventiv on 16/4/18.
 */

public class DataManager implements IDataManager{
    private static DataManager instance;
    private DatabaseHandler mDatabaseHandler;
private  DataManager(){
    mDatabaseHandler = DatabaseHandler.getInstance();
}
    public static DataManager getInstance() {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null)
                    instance = new DataManager();
            }
        }
        return instance;
    }




    @Override
    public void saveNotification(NotificationInfoBean notificationInfoBean) {
        mDatabaseHandler.setNotification(notificationInfoBean);

    }

    @Override
    public ArrayList retrieveNotification() {
        return mDatabaseHandler.onFetchEventList();
    }

    @Override
    public void updateNotification(NotificationInfoBean notificationInfoBean,int i) {
       mDatabaseHandler.updateEvent(notificationInfoBean,i);
    }

    @Override
    public void deleteNotification(int id) {
      mDatabaseHandler.deleteEvent(id);
    }

}