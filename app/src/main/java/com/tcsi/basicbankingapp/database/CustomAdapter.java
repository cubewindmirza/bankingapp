package com.tcsi.basicbankingapp.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.tcsi.basicbankingapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter{
    Context context;
    ArrayList<DataModel> dataModels;

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.custom, data);
        this.dataModels = data;
        this.context=context;

    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=LayoutInflater.from(getContext()).inflate(R.layout.custom, parent, false);
        TextView name =(TextView) v.findViewById(R.id.fromname);
        TextView rec  =(TextView) v.findViewById(R.id.touser);
        TextView  trans=(TextView) v.findViewById(R.id.transid);
        TextView  amt=(TextView) v.findViewById(R.id.sentamount);
        TextView  date=(TextView) v.findViewById(R.id.datetime);

        name.setText("FROM: "+DataModel.NAME);
        rec.setText("TO: "+DataModel.RECEIPTENT);
        trans.setText("TRANSCATION ID: "+DataModel.TRANSID);
        amt.setText("MONEY SENT: "+DataModel.AMOUNT);
        date.setText("DATE: "+DataModel.DATE);
        return v;

    }
}
