package com.tcsi.basicbankingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context,"UserDetails", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table UserDetails(name TEXT primary key,email TEXT,phonenumber TEXT,place TEXT,accountnumber NUMBER,amount NUMBER)");
        db.execSQL("create table TranscationDetails(name TEXT,receiptentname TEXT,transcationid TEXT,amount TEXT,time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("drop table if exists UserDetails");
       db.execSQL("drop table if exists TranscationDetails");
    }
    public Boolean insertuserdata(String name,String email,String phonenumber,String place,String accountnumber,int amount){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("email",email);
        values.put("phonenumber",phonenumber);
        values.put("place",place);
        values.put("accountnumber",accountnumber);
        values.put("amount",amount);
        long result=database.insert("UserDetails",null,values);
        if(result==-1){
            return  false;
        }
        else
            return true;
    }
    public Cursor getdata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from UserDetails",null);
        return cursor;
    }
    public Cursor getuserdata(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from UserDetails where name=?",new String[]{name});
        return cursor;
    }

    public boolean inserttranscation(String from,String to,String transid,String time,String amount){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",from);
        values.put("receiptentname",to);
        values.put("transcationid",transid);
        values.put("time",time);
        values.put("amount",amount);
        long result=database.insert("TranscationDetails",null,values);
        if(result==-1){
            return  false;
        }
        else
            return true;
    }
    public Cursor gettransdetail(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from TranscationDetails",null);
        return cursor;
    }
}
