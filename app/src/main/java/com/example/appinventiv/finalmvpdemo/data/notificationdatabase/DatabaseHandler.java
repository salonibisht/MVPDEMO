package com.example.appinventiv.finalmvpdemo.data.notificationdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.appinventiv.finalmvpdemo.pojo.NotificationInfoBean;

import java.util.ArrayList;

/**
 * Created by appinventiv on 16/4/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static DatabaseHandler mDatabaseHandler;
    private static int DataBase_Version = 1;
    private static final String DATABASE_NAME = "data";
    private static final String TABLE_NAME = "eventnotification";
    private static final String KEY_TODO = "todo";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_ID = "id";
    private ArrayList<NotificationInfoBean> eventList=new ArrayList<>();

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME, null, DataBase_Version);
    }
    public static DatabaseHandler getInstance() {
        return mDatabaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INFORMATION_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_TODO + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_DATE + " TEXT," +
                KEY_TIME + " TEXT " + ")";
        db.execSQL(CREATE_INFORMATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Save data in database....
     * @param notificationInfoBean
     */
    public void setNotification(NotificationInfoBean notificationInfoBean){
        SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TODO, notificationInfoBean.getTodo());
        values.put(KEY_DESCRIPTION, notificationInfoBean.getDescription());
        values.put(KEY_DATE, notificationInfoBean.getDate());
        values.put(KEY_TIME, notificationInfoBean.getTime());
         db.insert(TABLE_NAME, null, values);
        Log.d("*****","saved");
        }


    public static void initContext(Context context) {
        if (mDatabaseHandler ==null){
          mDatabaseHandler =new DatabaseHandler(context.getApplicationContext());
        }
    }

    public ArrayList onFetchEventList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToLast();
            do {
                eventList.add(new NotificationInfoBean(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToPrevious());
        }
        return eventList;
    }

    public void updateEvent(NotificationInfoBean notificationInfoBean,int i) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TODO, notificationInfoBean.getTodo());
        values.put(KEY_DESCRIPTION, notificationInfoBean.getDescription());
        values.put(KEY_DATE, notificationInfoBean.getDate());
        values.put(KEY_TIME, notificationInfoBean.getTime());

        db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(i)});

    }

    public void deleteEvent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (id >= 0) {
            db.delete(TABLE_NAME, KEY_ID + " = ?",
                    new String[]{String.valueOf(id)});
        } else {
            db.delete(TABLE_NAME, null, null);
        }
        db.close();
    }
    }



