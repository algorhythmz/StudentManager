package com.sample.app.studmanager_new;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Calendar;


public class Landing_Page extends AppCompatActivity {

    Shared_Pref_Util newUtil;
    private ListView subjectList;
    private ArrayList<Sample_Object> AttendanceList;
    MaterialDialog.Builder addSubjectDialog;


    List_Adapter list_adapter;
    Record_adapter record_adapter;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing__page);

        newUtil = new Shared_Pref_Util(getBaseContext());
        AttendanceList  = newUtil.getSubList();
        calendar = Calendar.getInstance();

        record_adapter = new Record_adapter(getBaseContext(),new ArrayList<Record_Object>());
        list_adapter = new List_Adapter(getBaseContext(),AttendanceList);
        subjectList = (ListView) findViewById(R.id.subjectList);

        subjectList.setAdapter(list_adapter);

        subjectList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new MaterialDialog.Builder(Landing_Page.this)
                        .title("Edit Options")
                        .negativeText("Delete")
                        .neutralText("Reset")
                        .positiveText("Change Name")
                        .input("Enter A Different Name", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog materialDialog, CharSequence charSequence) {

                                if (charSequence.toString().trim().length() > 0) {
                                    newUtil.changeSubName(charSequence.toString(), i);
                                    ArrayList<Sample_Object> newList = newUtil.getSubList();
                                    list_adapter.changeList(newList);
                                    list_adapter.notifyDataSetChanged();
                                } else {
                                    Util_Class.toastL(getBaseContext(), "Enter Valid Name");
                                }


                            }
                        })
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                newUtil.DeleteSubject(i);
                                ArrayList<Sample_Object> newList = newUtil.getSubList();
                                list_adapter.changeList(newList);
                                list_adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onNeutral(MaterialDialog dialog) {
                                newUtil.UpdateAttendance(i, 0);
                                ArrayList<Sample_Object> newList = newUtil.getSubList();
                                list_adapter.changeList(newList);
                                list_adapter.notifyDataSetChanged();
                            }
                        }).show();


                return true;
            }
        });



        //Defining The Dialog Which Will Open To Add Subject


        addSubjectDialog = new MaterialDialog.Builder(this)
                .title("Add The Subject Name")
                .positiveText("Add")

                .input("Subject Name", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog materialDialog, CharSequence charSequence) {
                        String NEW_SUB_NAME = charSequence.toString();
                        if (NEW_SUB_NAME.trim().length() <= 0) {
                            Util_Class.toastL(getBaseContext(), "Enter A Valid Name");
                        } else {
                            newUtil.AddNewSubject(NEW_SUB_NAME);
                            ArrayList<Sample_Object> newList = newUtil.getSubList();
                            list_adapter.changeList(newList);

                            list_adapter.notifyDataSetChanged();

                        }

                    }
                });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing__page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       switch(item.getItemId()){
           case  R.id.addSubject:
               addSubjectDialog.show();
               return true;
           case R.id.aboutUs:
               newUtil.clearData();
               Intent re = new Intent(getApplicationContext(),Faltu.class);
               startActivity(re);



           case R.id.Record:
               Intent RecordRedirect = new Intent(getBaseContext(),Record_Info.class);
               startActivity(RecordRedirect);
       }

        return super.onOptionsItemSelected(item);
    }
}
