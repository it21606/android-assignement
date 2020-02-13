package com.example.user.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

public class InternetReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(final Context context, final Intent intent) {
        int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN);
        switch (wifiStateExtra)
        {
            case WifiManager.WIFI_STATE_ENABLED:
                if (LocationLoggingService.hasStarted) {
                    context.stopService(new Intent(context, LocationLoggingService.class));
                } else {
                    context.startService(new Intent(context, LocationLoggingService.class));
                }
                break;
        }
    }
}