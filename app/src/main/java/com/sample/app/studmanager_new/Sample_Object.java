package com.sample.app.studmanager_new;

/**
 * Rahul Yadav Created com.sample.app.studmanager_new on 02-09-2015 at 15:52 for StudManager-New.
 */
public class Sample_Object {

    private String SubjectName;
    private int Attendance;

    public Sample_Object(String subjectName, int attendance) {
        this.SubjectName = subjectName;
        this.Attendance = attendance;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        this.SubjectName = subjectName;
    }

    public int getAttendance() {
        return Attendance;
    }

    public void setAttendance(int attendance) {
        this.Attendance = attendance;
    }
}
