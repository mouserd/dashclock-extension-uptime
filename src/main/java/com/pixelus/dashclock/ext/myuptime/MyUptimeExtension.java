package com.pixelus.dashclock.ext.myuptime;

import android.os.SystemClock;
import android.util.Log;
import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;

import static java.lang.String.format;

public class MyUptimeExtension extends DashClockExtension {

  public static final String TAG = MyUptimeExtension.class.getName();

  @Override
  protected void onUpdateData(int i) {

    String formattedUptime = getFormattedUptime();

    publishUpdate(new ExtensionData()
            .visible(true)
            .icon(R.drawable.ic_launcher)
            .status(getString(R.string.extension_status_uptime, formattedUptime))
            .expandedTitle(getString(R.string.extension_expanded_body_uptime_line, formattedUptime))
    );
  }
  private String getFormattedUptime() {

    Uptime uptime = new Uptime(SystemClock.elapsedRealtime());
    Log.d(TAG, format("Uptime [%s]", uptime));

    if (!uptime.isValid()) {

      return getString(R.string.extension_expanded_body_uptime_unavailable);
    }

    return getString(R.string.extension_expanded_body_uptime_format,
        uptime.getDays(), uptime.getHours(), uptime.getMinutes(), uptime.getSeconds());
  }
}