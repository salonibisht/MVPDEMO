package com.example.appinventiv.finalmvpdemo.EditSample;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.appinventiv.finalmvpdemo.R;
import com.example.appinventiv.finalmvpdemo.base.BaseActivity;
import com.example.appinventiv.finalmvpdemo.broadcastreceiver.NotificationReceiver;
import com.example.appinventiv.finalmvpdemo.samplenotification.NotificationInfoActivity;
import com.example.appinventiv.finalmvpdemo.samplenotification.NotificationPresenter;
import com.example.appinventiv.finalmvpdemo.samplenotification.NotificationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditTodoActivity extends BaseActivity implements NotificationView {

    @BindView(R.id.todo)
    TextInputEditText todo;
    @BindView(R.id.description)
    TextInputEditText description;
    @BindView(R.id.date)
    TextInputEditText date;
    @BindView(R.id.time)
    TextInputEditText time;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Calendar myCalendar;
    private NotificationPresenter mNotificationPresenter;
    private int id;
    private AlarmManager mAlarmMgr;
    private PendingIntent mAlarmIntent;
    private int mSelectedHour, mSelectedMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.edit_details);
        toolbar.setTitleTextColor(Color.WHITE);
        id = getIntent().getIntExtra("id", 0);
        mNotificationPresenter = new NotificationPresenter(this);
        setDate();
        setTime();
        //myCalendar = Calendar.getInstance();
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_edit_todo;
    }

    /**
     * Open Clock on click of time edit text....
     */
    private void setTime() {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(EditTodoActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mSelectedHour = selectedHour;
                        mSelectedMinute = selectedMinute;
                        myCalendar.set(Calendar.HOUR_OF_DAY,selectedHour);
                        myCalendar.set(Calendar.MINUTE,selectedMinute);
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
    }

    /**
     * Open calender on click of date edit text....
     */
    private void setDate() {
        date.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String myFormat = "MM/dd/yy";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    date.setText(sdf.format(myCalendar.getTime()));
                }

            };

            @Override
            public void onClick(View view) {
                new DatePickerDialog(EditTodoActivity.this, datePickerListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNotificationPresenter.detachView();
    }

    @Override
    public void todoError() {
        todo.setError(getString(R.string.please_enter_todo_event));

    }

    @Override
    public void descriptionError() {
        description.setError(getString(R.string.please_enter_description));

    }

    @Override
    public void dateError() {
        date.setError(getString(R.string.please_enter_date));

    }

    @Override
    public void timeError() {
        time.setError(getString(R.string.please_enter_time));

    }

    @Override
    public void setAlarm() {
        mAlarmMgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, mSelectedHour);
        calendar.set(Calendar.MINUTE, mSelectedMinute);
        Intent intent = new Intent(EditTodoActivity.this, NotificationReceiver.class);
        mAlarmIntent = PendingIntent.getBroadcast(EditTodoActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmMgr.set(AlarmManager.RTC,
                myCalendar.getTimeInMillis(), mAlarmIntent);
    }

    @Override
    public void successfullySaved() {

    }





    @OnClick(R.id.btn_update)
    public void onViewClicked() {
        mNotificationPresenter.onUpdateClicked(todo.getText().toString(),description.getText().toString(),date.getText().toString(),time.getText().toString(),id);
        mNotificationPresenter.setNotificationAlarm();
    }

    @Override
    public void showToastLong(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
