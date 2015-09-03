package com.sample.app.studmanager_new;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


public class Record_Info extends AppCompatActivity{

    private ListView recordList;
    private ArrayList<Record_Object> recordItemList;
    private Shared_Pref_Util newUtil;
    private Record_adapter record_adapter;
    private ArrayList<Sample_Object> spinnerEntries;
    private Spinner spinner;
    ArrayList<String> array = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record__info);

        newUtil = new Shared_Pref_Util(getBaseContext());
        spinnerEntries = newUtil.getSubList();
        array.add("Select Subject...");

        for(int index=0;index<spinnerEntries.size();index++){
            array.add(spinnerEntries.get(index).getSubjectName());
        }

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,array));

        recordItemList = newUtil.getRecordList();

        recordList = (ListView) findViewById(R.id.recordList);
        recordItemList = newUtil.getRecordList();
        record_adapter = new Record_adapter(getBaseContext(),recordItemList);
        
        recordList.setAdapter(record_adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = spinner.getItemAtPosition(i).toString();
                ArrayList<Record_Object> newList = new ArrayList<Record_Object>();
                for (int j=0;j<recordItemList.size();j++){
                    if(recordItemList.get(j).getsubjectName().matches(item)){
                        newList.add(recordItemList.get(j));
                    }
                }

                recordList.setAdapter(new Record_adapter(getBaseContext(),newList));
                record_adapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





    }


}