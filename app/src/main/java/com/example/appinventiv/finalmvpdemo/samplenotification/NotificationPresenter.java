package com.example.appinventiv.finalmvpdemo.samplenotification;

import com.example.appinventiv.finalmvpdemo.R;
import com.example.appinventiv.finalmvpdemo.base.BasePresenter;

/**
 * Created by appinventiv on 16/4/18.
 */

public class NotificationPresenter extends BasePresenter<NotificationView> implements NotificationModelLisetener {
    private NotificationModel model;
    public NotificationPresenter(NotificationView view){
        super(view);

    }
    @Override
    protected void setModel() {
        model = new NotificationModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }


    /**
     * Perform action on submit button...
     */
    public void onSaveButtonClicked(String todo,String description,String date,String time)
    {
            if (isInputValid(todo,description,date,time))
            {
                model.insertNotification(todo,description,date,time);
                getView().showToastLong("Event Saved");


        }


    }

    /**
     * Validation on input fields.....
     * @param todo
     * @param discription
     * @param date
     * @param time
     * @return
     */
    private boolean isInputValid(String todo, String discription, String date, String time) {
        if (todo.equals("") )
        {
            getView().todoError();
          return false;

        }
        else if (discription.equals("")){
            getView().descriptionError();
return false;
        }else if (date.equals("")){
            getView().dateError();
            return false;

        }
        else if (time.equals("")){
            getView().timeError();
            return false;

        }
            return true;
    }


    public void onUpdateClicked(String todo,String description,String date,String time,int i) {
        if (isInputValid(todo,description,date,time))
        {
            model.updateNotification(todo,description,date,time,i);
            getView().showToastLong("Event Updated");
        }
    }

    public void setNotificationAlarm() {
        getView().setAlarm();
    }
}
