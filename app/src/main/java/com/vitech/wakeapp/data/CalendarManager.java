package com.vitech.wakeapp.data;

import java.util.Calendar;

/**
 * Created by Home on 30-06-2016.
 */
public class CalendarManager {
    public static final int GET_HOUR = 1258;
    public static final int GET_MINUTE = 1259;
    public Calendar makeCalendar(int Hour,int Minute,int AM_PM){
        Calendar temp = Calendar.getInstance();
        if(AM_PM==1){
            if(Hour!=12) {
                Hour = Hour - 12;
            }
        }
        if (temp.before(Calendar.getInstance())) {
            temp.add(Calendar.DATE,1);
        }
        temp.set(Calendar.AM_PM,AM_PM);
        temp.set(Calendar.HOUR_OF_DAY,Hour);
        temp.set(Calendar.SECOND,0);
        temp.set(Calendar.MINUTE,Minute);
        return temp;
    }

    public String breakCalendar(Calendar temp,String field,boolean exact){
        String returnable = new String();
        switch (field){
            case "h":returnable = Integer.toString(temp.get(Calendar.HOUR_OF_DAY));
            if(exact){
                if(temp.get(Calendar.HOUR_OF_DAY)>12){
                    returnable = Integer.toString(temp.get(Calendar.HOUR_OF_DAY)-12);
                }
            }
            break;
            case  "m":returnable = Integer.toString(temp.get(Calendar.MINUTE));break;
        }
        return returnable;
    }
    public int breakCalendar(Calendar temp,int field){
        int returnable = 0;
        switch (field) {
            case GET_HOUR :returnable = temp.get(Calendar.HOUR_OF_DAY);break;
            case GET_MINUTE :returnable = temp.get(Calendar.MINUTE);break;
        }
        return returnable;
    }
    public Calendar makeCalendar(int Hour,int Minute){
        if(Hour>=12){
           return makeCalendar(Hour,Minute,1);
        }
        return makeCalendar(Hour,Minute,0);
    }



}
