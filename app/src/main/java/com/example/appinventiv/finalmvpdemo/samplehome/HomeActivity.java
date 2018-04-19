package com.example.appinventiv.finalmvpdemo.samplehome;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appinventiv.finalmvpdemo.EditSample.EditTodoActivity;
import com.example.appinventiv.finalmvpdemo.interfaces.EventInfoInterface;
import com.example.appinventiv.finalmvpdemo.broadcastreceiver.NotificationReceiver;
import com.example.appinventiv.finalmvpdemo.R;
import com.example.appinventiv.finalmvpdemo.adapter.NotificationAdapter;
import com.example.appinventiv.finalmvpdemo.base.BaseActivity;
import com.example.appinventiv.finalmvpdemo.pojo.NotificationInfoBean;
import com.example.appinventiv.finalmvpdemo.samplenotification.NotificationInfoActivity;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements HomeView, EventInfoInterface {
    @BindView(R.id.rv_notification)
    RecyclerView rvNotification;
    private HomePresentor mHomePresentor;
    private NotificationAdapter mNotificationAdapter;
    private ArrayList<NotificationInfoBean> mNotificationList;
    private int id;
    private Calendar alarmCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.event_notification);
        toolbar.setTitleTextColor(Color.WHITE);
        alarmCalendar=Calendar.getInstance();
        mHomePresentor = new HomePresentor(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        mHomePresentor.fetchData();
        rvNotification.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHomePresentor.onFabButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNotificationList.clear();
        mHomePresentor.fetchData();
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_sample;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNotificationAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHomePresentor.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.seach_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mNotificationAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mNotificationAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public void showNotification(ArrayList<NotificationInfoBean> notificationList) {
        mNotificationList = notificationList;
        mNotificationAdapter = new NotificationAdapter(HomeActivity.this, mNotificationList, this);
        rvNotification.setAdapter(mNotificationAdapter);
    }


    @Override
    public void intentFireAddTododetails() {
        Intent intent = new Intent(HomeActivity.this, NotificationInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void intentEditTodoDetails(int id) {
        Intent intent = new Intent(HomeActivity.this, EditTodoActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }


    @Override
    public void selectEventListener(View view, int position) {
         id = mNotificationList.get(position).getId();

        final CharSequence[] option = {getString(R.string.delete), getString(R.string.update),getString(R.string.cancel)};
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(R.string.select_option);
        alertDialogBuilder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mHomePresentor.selectOption(dialogInterface,i,id);

            }
        });
        alertDialogBuilder.show();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showToastLong(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void createReminder(Notification notification) {
        Intent notificationIntent = new Intent(this, NotificationReceiver.class);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long delay = alarmCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

}
