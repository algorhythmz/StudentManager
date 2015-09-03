package com.sample.app.studmanager_new;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Rahul Yadav Created com.sample.app.studmanager_new on 02-09-2015 at 15:44 for StudManager-New.
 */
public class Shared_Pref_Util {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private ArrayList<Sample_Object> AttendanceInfo;
    private String ActualData;
    private ArrayList<Record_Object> RecordInfo;
    private String ActualRecordInfo;

    public Shared_Pref_Util(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        AttendanceInfo = new ArrayList<>();
        RecordInfo = new ArrayList<>();
        ActualData = gson.toJson(AttendanceInfo);
        ActualRecordInfo = gson.toJson(RecordInfo);

        if(!sharedPreferences.contains(Constants.SUB_KEY)&&!sharedPreferences.contains(Constants.RECORD_KEY)){
            editor.putString(Constants.SUB_KEY, ActualData);
            editor.putString(Constants.RECORD_KEY,ActualRecordInfo);
            editor.commit();}
    }



    public ArrayList<Sample_Object> getSubList(){
        String json_to_edit = sharedPreferences.getString(Constants.SUB_KEY,"");
        Type type = new TypeToken<ArrayList<Sample_Object>>(){}.getType();
        ArrayList<Sample_Object> List_edit = gson.fromJson(json_to_edit, type);
        return List_edit;
    }

    public void AddNewSubject(String subName){
        ArrayList<Sample_Object> LIST_TO_EDIT = getSubList();

        LIST_TO_EDIT.add(new Sample_Object(subName,0));
        String updated_string = gson.toJson(LIST_TO_EDIT);
        editor.putString(Constants.SUB_KEY,updated_string);
        editor.commit();
    }

    public void DeleteSubject(int Index){
        ArrayList<Sample_Object> List_edit = getSubList();
        List_edit.remove(Index);
        String Edited_JSON = gson.toJson(List_edit);
        editor.putString(Constants.SUB_KEY,Edited_JSON);
        editor.commit();
    }

    public void UpdateAttendance(int Index,int Value){
        ArrayList<Sample_Object> List_edit = getSubList();
        List_edit.get(Index).setAttendance(Value);
        String Edited_JSON = gson.toJson(List_edit);
        editor.putString(Constants.SUB_KEY,Edited_JSON);
        editor.commit();
    }

    public void changeSubName(String newName,int index){
        ArrayList<Sample_Object> List_edit = getSubList();
        List_edit.get(index).setSubjectName(newName);
        String Edited_JSON = gson.toJson(List_edit);
        editor.putString(Constants.SUB_KEY,Edited_JSON);
        editor.commit();
    }

    public  ArrayList<Record_Object> getRecordList(){
        String json_to_edit = sharedPreferences.getString(Constants.RECORD_KEY,"");
        Type type = new TypeToken<ArrayList<Record_Object>>(){}.getType();
        ArrayList<Record_Object> List_edit = gson.fromJson(json_to_edit, type);
        return List_edit;

    }

    public void addAttendanceInfo(String subNAme,String Time , String Date){
        ArrayList<Record_Object> attendance_Record_Edit = getRecordList();
        attendance_Record_Edit.add(new Record_Object(subNAme,Time,Date));
        String Edited_JSON = gson.toJson(attendance_Record_Edit);
        editor.putString(Constants.RECORD_KEY,Edited_JSON);
        editor.commit();
    }

    public void clearData(){
        editor.clear();
        editor.commit();
    }

    public void DeleteRecord(int Index){
        ArrayList<Record_Object> List_edit = getRecordList();
        List_edit.remove(Index);
        String Edited_JSON = gson.toJson(List_edit);
        editor.putString(Constants.RECORD_KEY,Edited_JSON);
        editor.commit();
    }




}


