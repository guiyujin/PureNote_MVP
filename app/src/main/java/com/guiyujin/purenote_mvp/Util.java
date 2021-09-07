package com.guiyujin.purenote_mvp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss");
        Date curDate = new Date();
        return format.format(curDate);
    }

    private static String calenderEventURL = null;
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
}
