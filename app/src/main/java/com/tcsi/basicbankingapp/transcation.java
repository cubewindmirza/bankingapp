package com.tcsi.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tcsi.basicbankingapp.database.CustomAdapter;
import com.tcsi.basicbankingapp.database.DBHelper;
import com.tcsi.basicbankingapp.database.DataModel;

import java.util.ArrayList;
import java.util.List;

public class transcation extends AppCompatActivity {
      DBHelper dbHelper;
    ArrayList<DataModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation);
        dbHelper=new DBHelper(this);
        Cursor cursor=dbHelper.gettransdetail();
        if(cursor.getCount()!=0){
             list=new ArrayList<>();
            while (cursor.moveToNext()){
               list.add(new DataModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }
        }
        CustomAdapter adapter=new CustomAdapter(list,this);
        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
    }
}