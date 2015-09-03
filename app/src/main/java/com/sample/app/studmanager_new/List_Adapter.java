package com.sample.app.studmanager_new;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Rahul Yadav Created com.sample.app.studmanager_new on 02-09-2015 at 16:16 for StudManager-New.
 */
public class List_Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<Sample_Object> AttendanceList;
    Shared_Pref_Util newUtil ;

    public List_Adapter(Context context, ArrayList<Sample_Object> attendanceList) {
        this.context = context;
        this.AttendanceList = attendanceList;
    }

    @Override
    public int getCount() {
        return AttendanceList.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        newUtil = new Shared_Pref_Util(context);

        final Calendar calendar = Calendar.getInstance();

        View v = view;
        ViewHolder viewHolder = new ViewHolder();
        if (view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.adapter_layout, null);

            viewHolder.minus = (Button) v.findViewById(R.id.SubjectMinus);
            viewHolder.plus = (Button) v.findViewById(R.id.SubjectPlus);
            viewHolder.SubName = (TextView) v.findViewById(R.id.SubjectName);
            viewHolder.SubAttendance = (TextView) v.findViewById(R.id.SubjectAttendance);

            v.setTag(viewHolder);


        }

        else{
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.SubAttendance.setText("" + AttendanceList.get(i).getAttendance());
        viewHolder.SubName.setText("" + AttendanceList.get(i).getSubjectName());
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                int Value = Integer.parseInt(finalViewHolder.SubAttendance.getText().toString());
                Value = Value -1;
                newUtil.UpdateAttendance(i, Value);
                finalViewHolder.SubAttendance.setText("" + Value);
                AttendanceList.get(i).setAttendance(Value);

                List_Adapter.this.notifyDataSetChanged();

            }
        });

        viewHolder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = ""+calendar.get(Calendar.DATE)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.YEAR);
                int second = calendar.get(Calendar.SECOND);
                int minute = calendar.get(Calendar.MINUTE);
                int hour = calendar.get(Calendar.HOUR);
                String time = ""+hour+":"+minute+":"+second;
                String SubName = AttendanceList.get(i).getSubjectName();
                newUtil.addAttendanceInfo(SubName,time,date);
                int Value = Integer.parseInt(finalViewHolder.SubAttendance.getText().toString());
                Value = Value +1;
                newUtil.UpdateAttendance(i, Value);
                finalViewHolder.SubAttendance.setText("" + Value);
                AttendanceList.get(i).setAttendance(Value);

                List_Adapter.this.notifyDataSetChanged();

            }
        });

        return v;
    }

    public static class ViewHolder{
        private Button  minus;
        private Button plus;
        private TextView SubAttendance;
        private TextView SubName;
    }

    public void changeList(ArrayList<Sample_Object> AttendanceList){
        this.AttendanceList = AttendanceList;
    }
}
