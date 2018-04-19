package com.example.appinventiv.finalmvpdemo.samplehome;

import android.content.DialogInterface;

import com.example.appinventiv.finalmvpdemo.base.BasePresenter;

import java.util.ArrayList;

/**
 * Created by appinventiv on 17/4/18.
 */

public class HomePresentor extends BasePresenter<HomeView> implements HomeModelListener {
    private HomeModel model;
    public HomePresentor(HomeView view){
        super(view);

    }
    @Override
    protected void setModel() {
        model = new HomeModel(this);
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
     * Fetch details from database....
     */
    public void fetchData(){
        model.getNotificationInfo();
    }
    /**
     * Fire Intent on click of Fab button....
     */
    public void onFabButtonClicked()
    {
        getView().intentFireAddTododetails();
    }
    @Override
    public void onRetrieveNotification(ArrayList list) {
        getView().showNotification(list);

    }

    /**
     * Update details corresponding to selected row
     * @param id
     */
    public void updateSelectedRow(int id ) {
        getView().intentEditTodoDetails(id);
    }

    /**
     * Delete selected row of event....
     * @param id
     */

    public void deleteSelectedRow(int id) {
        model.deleteRow(id);
        getView().showToastLong("Event Deleted");
    }

    /**
     * Select option for update,delete or cancel dialog....
     * @param dialogInterface
     * @param i
     * @param id
     */
    public void selectOption(DialogInterface dialogInterface,int i,int id) {
        final CharSequence[] option = {"delete", "update","cancel"};
        if (option[i].equals("update")) {
           updateSelectedRow(id);
        }else if (option[i].equals("delete")) {
           deleteSelectedRow(id);
        }
        else if (option[i].equals("cancel")) {
            dialogInterface.dismiss();
        }

    }
}
