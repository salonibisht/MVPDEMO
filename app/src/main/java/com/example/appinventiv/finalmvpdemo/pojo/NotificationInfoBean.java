package com.example.appinventiv.finalmvpdemo.pojo;

/**
 * Created by appinventiv on 16/4/18.
 */

public class NotificationInfoBean {
   private String mTodo="",mDescription="",mDate="",mTime="";
   private int mId;
   public NotificationInfoBean(){

   }
   public NotificationInfoBean(int id,String todo,String description, String date,String time){
      mTime=time;
      mTodo=todo;
      mDescription=description;
      mDate=date;
      mId=id;

   }
   public NotificationInfoBean(String todo,String description, String date,String time){
      mTime=time;
      mTodo=todo;
      mDescription=description;
      mDate=date;

   }

   public String getTodo() {
      return mTodo;
   }

   public void setTodo(String mTodo) {
      this.mTodo = mTodo;
   }

   public String getDescription() {
      return mDescription;
   }

   public void setDescription(String mDescription) {
      this.mDescription = mDescription;
   }

   public String getDate() {
      return mDate;
   }

   public void setDate(String mDate) {
      this.mDate = mDate;
   }

   public String getTime() {
      return mTime;
   }

   public void setTime(String mTime) {
      this.mTime = mTime;
   }


   public int  getId() {
      return mId;
   }

   public void setId(int mId) {
      this.mId = mId;
   }
}
