package com.vitech.wakeapp.rearend;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.vitech.wakeapp.R;
import com.vitech.wakeapp.data.CalendarManager;
import com.vitech.wakeapp.data.DataHelper;
import com.vitech.wakeapp.data.StructuredAlarm;
import com.vitech.wakeapp.ui.WakeAppActivity;

import java.util.ArrayList;


/**
 * Created by Home on 30-06-2016.
 */
public class AlarmListAdapter extends BaseAdapter {
    DataHelper dataHelper;
    ArrayList<StructuredAlarm> structuredAlarms;
    Context context;
    CheckBox checkBox;
    CalendarManager calendarManager;
    Switch statSwitch;
    public AlarmListAdapter(Context context) {
        this.dataHelper = new DataHelper(context);
        this.structuredAlarms = dataHelper.getAllAlarms();
        this.context = context;
    }

    @Override
    public int getCount() {
        return structuredAlarms.size();
    }

    @Override
    public Object getItem(int position) {
        return structuredAlarms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View prevView, ViewGroup parent) {
        calendarManager = new CalendarManager();
        dataHelper = new DataHelper(context);
        final ScheduleManager scheduleManager = new ScheduleManager();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView;
        if (prevView == null) {
            prevView = inflater.inflate(R.layout.alarm_item, null);
        }
        convertView = prevView;

        TextView textHr = (TextView) convertView.findViewById(R.id.textHr);
        TextView textMinute = (TextView) convertView.findViewById(R.id.textMin);
      statSwitch = (Switch) convertView.findViewById(R.id.alarmState);
        TextView textAp = (TextView) convertView.findViewById(R.id.textAp);
        ImageView delete = (ImageView) convertView.findViewById(R.id.imageView);
        checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        textHr.setText(calendarManager.breakCalendar(structuredAlarms.get(position).calendar, "h", true));
        textMinute.setText(calendarManager.breakCalendar(structuredAlarms.get(position).calendar, "m", false));
        textAp.setText(structuredAlarms.get(position).getMeridian());
        statSwitch.setChecked(structuredAlarms.get(position).State);
     /*   statSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHelper.toggleAlarm(structuredAlarms.get(position).Id, statSwitch.isChecked());
                if (!statSwitch.isChecked()) {
                    structuredAlarms = dataHelper.getAllAlarms();
                    scheduleManager.destroyAlarm(context, structuredAlarms.get(position).Id);
                } else {
                    structuredAlarms = dataHelper.getAllAlarms();
                    if (checkBox.isChecked()) {
                        System.out.println("status toggle" + Integer.toString(structuredAlarms.get(position).Id) + Boolean.toString(true));
                        scheduleManager.setDailyAlarm(context, structuredAlarms.get(position));
                    } else {
                        System.out.println("status toggle" + Integer.toString(structuredAlarms.get(position).Id) + Boolean.toString(true));
                        scheduleManager.scheduleNewAlarm(context, structuredAlarms.get(position));
                    }
                }
            }
        });*/
        statSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (statSwitch.isShown()) {
                    System.out.println(Boolean.toString(isChecked));
                    dataHelper.toggleAlarm(structuredAlarms.get(position).Id,isChecked);
                    if (!isChecked) {
                        structuredAlarms = dataHelper.getAllAlarms();
                        scheduleManager.destroyAlarm(context, structuredAlarms.get(position).Id);
                        System.out.println("status toggle" + Integer.toString(structuredAlarms.get(position).Id) + Boolean.toString(isChecked));
                    } else {
                        structuredAlarms = dataHelper.getAllAlarms();
                        if (checkBox.isChecked()) {
                            System.out.println("status toggle" + Integer.toString(structuredAlarms.get(position).Id) + Boolean.toString(isChecked));
                            scheduleManager.setDailyAlarm(context, structuredAlarms.get(position));
                        } else {
                            System.out.println("status toggle" + Integer.toString(structuredAlarms.get(position).Id) + Boolean.toString(isChecked));
                            scheduleManager.scheduleNewAlarm(context, structuredAlarms.get(position));
                        }
                    }

                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleManager.destroyAlarm(context, structuredAlarms.get(position).Id);
                dataHelper.deleteAlarm(structuredAlarms.get(position).Id);
                structuredAlarms = dataHelper.getAllAlarms();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

}