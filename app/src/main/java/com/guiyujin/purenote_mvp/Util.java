package com.guiyujin.purenote_mvp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.Settings.System.getString;

public class Util {

    public static String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        return format.format(curDate);
    }

    private static String calenderEventURL;
    static {
        if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
            calenderEventURL = "content://com.android.calendar/events";
        } else {
            calenderEventURL = "content://calendar/events";
        }
    }

    public static void OpenCalendar(Context context, String content) {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(Uri.parse(calenderEventURL))
                .putExtra("beginTime", System.currentTimeMillis())
                .putExtra("endTime", System.currentTimeMillis() + 24*60*60*1000)
                .putExtra("title", content)
                .putExtra("description", content);
        context.startActivity(intent);
    }

    public static void finger(Context context){
//        FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(context);
//        builder.callback(fingerprintCallback)
//                .fingerprintColor(ContextCompat.getColor(context, R.color.colorAccent))
//                .build();
    }


}
