package com.vitech.wakeapp.data;

import java.util.Calendar;

/**
 * Created by Home on 30-06-2016.
 */
public class StructuredAlarm {
 public   Calendar calendar;
    public boolean State;
    public int AM_PM;
    public int Id;
    public StructuredAlarm(Calendar calendar,int AM_PM,boolean State,int Id){
        this.AM_PM = AM_PM;
     this.calendar = calendar;
        this.State = State;
        this.Id = Id;

    }
    public String getMeridian(){
        if(AM_PM==1){
            return "pm";
        }
        return "am";
    }



}
