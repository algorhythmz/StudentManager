package com.sample.app.studmanager_new;

/**
 * Rahul Yadav Created com.sample.app.studmanager_new on 03-09-2015 at 07:42 for StudManager-New.
 */
public class Record_Object {
    private String SubjectName;
    private String Time;
    private String Date;

    public Record_Object(String subjectName, String time, String date) {
        this.SubjectName = subjectName;
        this.Time = time;
        this.Date = date;
    }

    public String getsubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        this.SubjectName = subjectName;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
}
