package com.tcsi.basicbankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tcsi.basicbankingapp.database.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeScreen extends AppCompatActivity {
    DBHelper dbHelper;
    Button viewuser,transcation;
    int position=0;
    TextView Name,Email,Place,Number,AccountNumber,Amount,SelectUser,FromUser;
    EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().setTitle("Banking App");
        viewuser=findViewById(R.id.users);
        transcation=findViewById(R.id.transcationdetails);
        dbHelper=new DBHelper(this);
        Cursor check=dbHelper.getdata();
        if(check.getCount()==0) {
            //dummy data
            dbHelper.insertuserdata("Arun", "Arun12@gmail.com", "8553889686", "Singasandra", "xxxxxxxx6915", 250000);
            dbHelper.insertuserdata("Kavya", "Kavya@gmail.com", "92423789686", "Bommanahalli", "xxxxxxxx2105", 20000);
            dbHelper.insertuserdata("Jeevan", "Jeevan@gmail.com", "8217395666", "Kattriguppe", "xxxxxxxx4004", 600000);
            dbHelper.insertuserdata("Deepak", "Deepak@gmail.com", "9036568971", "Srinivasanagar", "xxxxxxxx3516", 500000);
            dbHelper.insertuserdata("Vivek", "Vivek@gmail.com", "8553837566", "Bda", "xxxxxxxx4897", 270000);
            dbHelper.insertuserdata("Prasanna", "Prasanna@gmail.com", "9966996633", "Market", "xxxxxxxx5515", 350000);
            dbHelper.insertuserdata("Prince", "Prince@gmail.com", "9538513656", "Mysore", "xxxxxxxx6215", 350000);
            dbHelper.insertuserdata("Krishna", "Krishna@gmail.com", "8553229686", "Singasandra", "xxxxxxxx6969", 50000);
            dbHelper.insertuserdata("Vikas", "Vikas@gmail.com", "8553835566", "Govindpalya", "xxxxxxxx1215", 20000);
            dbHelper.insertuserdata("Sathish", "Sathish@gmail.com", "8553855686", "Nangapura", "xxxxxxxx3405", 290000);
        }
        transcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeScreen.this, com.tcsi.basicbankingapp.transcation.class);
                startActivity(i);
            }
        });
        viewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=dbHelper.getdata();
                if(res.getCount()==0){
                    Toast.makeText(HomeScreen.this,"No Data Available",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                List<String> list=new ArrayList<>();
                while (res.moveToNext()){
                    buffer.append("Name: "+res.getString(0)+"\n");
                  /*  buffer.append("Email: "+res.getString(1)+"\n");
                    buffer.append("Number: "+res.getString(2)+"\n");
                    buffer.append("Place: "+res.getString(3)+"\n");
                    buffer.append("Accountnumber: "+res.getString(4)+"\n");
                    buffer.append("Amount: "+res.getString(5)+"\n");
                    buffer.append("\n\n");

                   */
                    list.add( res.getString(0));

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(HomeScreen.this);
                builder.setCancelable(true);
                builder.setTitle("User Names");
                String[] lis= list.toArray(new String[0]);
                builder.setSingleChoiceItems(lis, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                    }
                });
               // builder.setMessage(buffer.toString());
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    // Toast.makeText(HomeScreen.this,lis[position],Toast.LENGTH_LONG).show();
                     getdata(lis[position]);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            }
        });

    }
    public void getdata(String name){
        Cursor res=dbHelper.getuserdata(name);
        res.moveToNext();
        if(res.getCount()>0){
            View view;
            LayoutInflater inflater = (LayoutInflater)   getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.userdetails, null);
            Name=view.findViewById(R.id.name);
            Email=view.findViewById(R.id.email);
            Place=view.findViewById(R.id.place);
            Number=view.findViewById(R.id.number);
            AccountNumber=view.findViewById(R.id.accountnumber);
            Amount=view.findViewById(R.id.amount);
            Name.setText(res.getString(0)+"\n");
            Email.setText("Email: "+res.getString(1)+"\n");
            Place.setText("Address: "+res.getString(3)+"\n");
            Number.setText("Phone Number: "+res.getString(2)+"\n");
            AccountNumber.setText("Accountnumber: "+res.getString(4)+"\n");
            Amount.setText("Balance Amount: "+res.getString(5)+"\n");

           /* StringBuffer buffer=new StringBuffer();
            buffer.append("Name: "+res.getString(0)+"\n");
                   buffer.append("Email: "+res.getString(1)+"\n");
                    buffer.append("Number: "+res.getString(2)+"\n");
                    buffer.append("Place: "+res.getString(3)+"\n");
                    buffer.append("Accountnumber: "+res.getString(4)+"\n");
                    buffer.append("Amount: "+res.getString(5)+"\n");
                    buffer.append("\n\n");

            */
            AlertDialog.Builder builder=new AlertDialog.Builder(HomeScreen.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
          //  builder.setCancelable(false);
            builder.setView(view);
            //builder.setMessage(buffer.toString());
            builder.setPositiveButton("Transfer Money", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AlertDialog.Builder build=new AlertDialog.Builder(HomeScreen.this);
                    build.setTitle("Transcation");
                    View view;
                    LayoutInflater inflater = (LayoutInflater)   getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.transfermoney, null);
                    SelectUser=view.findViewById(R.id.to);
                    FromUser=view.findViewById(R.id.from);
                    amount=view.findViewById(R.id.enteramount);
                    FromUser.setText(Name.getText().toString());
                    SelectUser.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Cursor res=dbHelper.getdata();
                            if(res.getCount()==0){
                                Toast.makeText(HomeScreen.this,"No Data Available",Toast.LENGTH_LONG).show();
                                return;
                            }
                            StringBuffer buffer=new StringBuffer();
                            List<String> list=new ArrayList<>();
                            while (res.moveToNext()){
                                buffer.append("Name: "+res.getString(0)+"\n");
                  /*  buffer.append("Email: "+res.getString(1)+"\n");
                    buffer.append("Number: "+res.getString(2)+"\n");
                    buffer.append("Place: "+res.getString(3)+"\n");
                    buffer.append("Accountnumber: "+res.getString(4)+"\n");
                    buffer.append("Amount: "+res.getString(5)+"\n");
                    buffer.append("\n\n");

                   */
                                list.add( res.getString(0));

                            }
                            AlertDialog.Builder builder=new AlertDialog.Builder(HomeScreen.this);
                            builder.setCancelable(true);
                            builder.setTitle("User Names");
                            String[] lis= list.toArray(new String[0]);
                            builder.setSingleChoiceItems(lis, position, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    position=which;
                                }
                            });
                            // builder.setMessage(buffer.toString());
                            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Toast.makeText(HomeScreen.this,lis[position],Toast.LENGTH_LONG).show();
                                    SelectUser.setText(lis[position]);
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();

                        }
                    });
                    build.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            SimpleDateFormat formatrans = new SimpleDateFormat("ddMMyyyyHHmmss");
                            Date date = new Date();
                            String loctime=formatter.format(date);
                            String loctrans=formatrans.format(date);
                            boolean insert=dbHelper.inserttranscation(FromUser.getText().toString(),SelectUser.getText().toString(),loctrans,loctime,amount.getText().toString());
                            if(insert) {
                                AlertDialog.Builder build = new AlertDialog.Builder(HomeScreen.this);
                                build.setView(R.layout.transcationsuccess);
                                build.show();
                            }
                            else
                                Toast.makeText(HomeScreen.this,"faailed",Toast.LENGTH_LONG).show();
                        }
                    });
                    build.setView(view);
                    build.show();
                }
            });
            builder.show();

        }
    }
}