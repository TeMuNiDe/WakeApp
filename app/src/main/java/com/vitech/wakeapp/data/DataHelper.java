package com.vitech.wakeapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Calendar;

import static java.util.Calendar.*;

/**
 * Created by Home on 30-06-2016.
 */
public class DataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "WakeAppAlarms.db";
    public static final String TABLE_NAME = "WakeappAlarms";
    public static final String COL_1 = "HOUR";
    public static final String COL_2 = "MINUTE";
    public static final String COL_3 = "AMPM";
    public static final String COL_4 = "STATUS";
    public static final String COL_5 = "ID";



  public   DataHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table " + TABLE_NAME + "(HOUR INTEGER,MINUTE INTEGER,AMPM INTEGER,STATUS INTEGER,ID INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
    onCreate(db);
    }

    public StructuredAlarm insertNewAlarm(StructuredAlarm newAlarm){
        SQLiteDatabase db = this.getWritableDatabase();
        CalendarManager calendarManager = new CalendarManager();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,calendarManager.breakCalendar(newAlarm.calendar,CalendarManager.GET_HOUR));
        contentValues.put(COL_2,calendarManager.breakCalendar(newAlarm.calendar,CalendarManager.GET_MINUTE));
        contentValues.put(COL_3,newAlarm.AM_PM);
        contentValues.put(COL_4,Booli(newAlarm.State));
        contentValues.put(COL_5, newAlarm.Id);
        db.insert(TABLE_NAME, null, contentValues);
        return newAlarm;
    }
    public void deleteAlarm(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?", new String[]{Integer.toString(id)});
    }
public void toggleAlarm(int id,boolean state){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(COL_4,Booli(state));
    db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {Integer.toString(id)});
}

    public ArrayList<StructuredAlarm> getAllAlarms(){
        CalendarManager cM = new CalendarManager();
        ArrayList<StructuredAlarm> all = new ArrayList<StructuredAlarm>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor pos = db.rawQuery("select * from "+TABLE_NAME,null);
        if(pos.getCount()==0){
            return all;
        }
        while (pos.moveToNext()){
            all.add(new StructuredAlarm(cM.makeCalendar(pos.getInt(0),pos.getInt(1),pos.getInt(2)),pos.getInt(2),Booli(pos.getInt(3)),pos.getInt(4)));
        }
        return all;
    }
public     int Booli(boolean p){
        if(p){return 1;}else {return 0;}
    }
    boolean Booli(int p){return p==1;}
}
