package com.sample.app.studmanager_new;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Rahul Yadav Created com.sample.app.studmanager_new on 03-09-2015 at 08:05 for StudManager-New.
 */
public class Record_adapter extends BaseAdapter{

    private TextView subjectName,Time,Date;
    private Context context;
    private ArrayList<Record_Object> recordList;

    public Record_adapter(Context context,ArrayList<Record_Object> recordList) {
        this.context = context;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {

        return recordList.size();

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.record_adapter_layout,viewGroup,false);

        subjectName = (TextView) v.findViewById(R.id.subName);
        Time = (TextView) v.findViewById(R.id.time);
        Date = (TextView) v.findViewById(R.id.date);


        subjectName.setText(""+recordList.get(i).getsubjectName());
        Time.setText(""+recordList.get(i).getTime());
        Date.setText(""+recordList.get(i).getDate());



        return v;
    }

    public void changeList(ArrayList<Record_Object> recordList){
        this.recordList = recordList;
    }
}
