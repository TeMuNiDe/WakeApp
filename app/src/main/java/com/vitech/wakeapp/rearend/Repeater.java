package com.vitech.wakeapp.rearend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.vitech.wakeapp.ui.FiredAlarm;

/**
 * Created by Home on 01-07-2016.
 */
public class Repeater extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context,FiredAlarm.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);

    }
}
