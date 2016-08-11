package com.vitech.wakeapp.rearend;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.vitech.wakeapp.data.StructuredAlarm;

import java.util.Calendar;

/**
 * Created by Home on 01-07-2016.
 */
public class ScheduleManager {

    Intent target;
    PendingIntent pendingIntent;
    Calendar calendar;
    AlarmManager alarmManager;
    public void scheduleNewAlarm(Context context,StructuredAlarm structuredAlarm){
        target = new Intent(context,idtoClass(structuredAlarm.Id));
        pendingIntent = PendingIntent.getBroadcast(context,0,target,0);
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        calendar = structuredAlarm.calendar;
        System.out.println("single alarm scheduled"+Integer.toString(structuredAlarm.Id));
        if (calendar.before(Calendar.getInstance())){
            calendar.add(Calendar.DATE,1);
            System.out.println("date added\n");
        }
        calendar.set(Calendar.AM_PM,structuredAlarm.AM_PM);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
    }
    public void setDailyAlarm(Context context,StructuredAlarm structuredAlarm){
        target = new Intent(context,idtoClass(structuredAlarm.Id));
        pendingIntent = PendingIntent.getBroadcast(context,0,target,0);
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        calendar = structuredAlarm.calendar;
        if (calendar.before(Calendar.getInstance())){
            calendar.add(Calendar.DATE,1);
        }
        calendar.set(Calendar.AM_PM,structuredAlarm.AM_PM);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);
        System.out.println("daily alarm schdeuled"+Integer.toString(structuredAlarm.Id));
    }
    public void destroyAlarm(Context context,int id){
        target = new Intent(context,idtoClass(id));
        pendingIntent = PendingIntent.getBroadcast(context,0,target,0);
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
System.out.println("alarm destroyed"+Integer.toString(id));
    }
    public Class idtoClass(int id){
        Class p = AlarmReciever0.class;
        switch (id){
            case 0:break;
            case 1:p = AlarmReciever1.class;break;
            case 2:p = AlarmReciever2.class;break;
            case 3:p = AlarmReciever3.class;break;
            case 4:p = AlarmReciever4.class;break;
        }
        return p;

    }
    public void setRepeat(Context context){
        target = new Intent(context,Repeater.class);
        pendingIntent = PendingIntent.getBroadcast(context,0,target,0);
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis()+1000*3*60,pendingIntent);

    }
    public void cancelRepeat(Context context){
        target = new Intent(context,Repeater.class);
        pendingIntent = PendingIntent.getBroadcast(context,0,target,0);
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }


}

