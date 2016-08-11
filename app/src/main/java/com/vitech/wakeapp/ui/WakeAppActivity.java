package com.vitech.wakeapp.ui;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.vitech.wakeapp.R;
import com.vitech.wakeapp.data.CalendarManager;
import com.vitech.wakeapp.data.DataHelper;
import com.vitech.wakeapp.data.StructuredAlarm;
import com.vitech.wakeapp.rearend.AlarmListAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class WakeAppActivity extends AppCompatActivity {
DataHelper dataHelper;
    ListView listView;
    AlarmListAdapter alarmListAdapter;
    ArrayList<StructuredAlarm> structuredAlarms;
    CalendarManager calendarManager;
    Calendar currentTime;
    Calendar calendar;
    TextView tv;
    TextView tv2;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
calendarManager = new CalendarManager();
tv = (TextView)findViewById(R.id.textView2);
dataHelper = new DataHelper(WakeAppActivity.this);
structuredAlarms = dataHelper.getAllAlarms();
        id = structuredAlarms.size();
listView = (ListView)findViewById(R.id.listView);
        tv2 = (TextView)findViewById(R.id.textView3);
        alarmListAdapter = new AlarmListAdapter(WakeAppActivity.this);
listView.setAdapter(alarmListAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                structuredAlarms = dataHelper.getAllAlarms();

                if(dataHelper.getAllAlarms().size()<5) {


                    currentTime = Calendar.getInstance();
                    calendar = Calendar.getInstance();
                    new TimePickerDialog(WakeAppActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            calendar = calendarManager.makeCalendar(hourOfDay, minute);
                            id = fetchId(structuredAlarms);
                            structuredAlarms.add(dataHelper.insertNewAlarm(new StructuredAlarm(calendar,dataHelper.Booli(hourOfDay >= 12),false,id))
                            );
                            alarmListAdapter = new AlarmListAdapter(WakeAppActivity.this);
                            listView.setAdapter(alarmListAdapter);
                        }
                    }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), true).show();
                }
                else{
                    Toast.makeText(WakeAppActivity.this,"Maximum no of Alarms is \"5\" Sorry for Inconvenience",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        calendarManager = new CalendarManager();
        tv = (TextView)findViewById(R.id.textView2);
        dataHelper = new DataHelper(WakeAppActivity.this);
        structuredAlarms = dataHelper.getAllAlarms();
        id = structuredAlarms.size();
        listView = (ListView)findViewById(R.id.listView);
        tv2 = (TextView)findViewById(R.id.textView3);
        alarmListAdapter = new AlarmListAdapter(WakeAppActivity.this);
        listView.setAdapter(alarmListAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                structuredAlarms = dataHelper.getAllAlarms();

                if (dataHelper.getAllAlarms().size() < 5) {


                    currentTime = Calendar.getInstance();
                    calendar = Calendar.getInstance();
                    new TimePickerDialog(WakeAppActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            calendar = calendarManager.makeCalendar(hourOfDay, minute);
                            id = fetchId(structuredAlarms);
                            structuredAlarms.add(dataHelper.insertNewAlarm(new StructuredAlarm(calendar, dataHelper.Booli(hourOfDay >= 12), false, id))
                            );
                            alarmListAdapter = new AlarmListAdapter(WakeAppActivity.this);
                            listView.setAdapter(alarmListAdapter);
                        }
                    }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), true).show();
                } else {
                    Toast.makeText(WakeAppActivity.this, "Maximum no of Alarms is \"5\" Sorry for Inconvenience", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    int fetchId(ArrayList<StructuredAlarm> structuredAlarms){
        int id ;
        int count = 0;

for (id = 0;id<5;id++){
    count = 0;
    for (int i = 0; i < structuredAlarms.size(); i++) {
        if (structuredAlarms.get(i).Id == id){
            count++;
        }
    }
    if(count==0){return id;}
}
        return id;
    }

}
