package com.example.user.test;

import android.Manifest;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;


public class LocationLoggingService extends Service {

    public static boolean hasStarted = false;
    private LocationManager locationManager;

    private void startListening() {
        //Check if app has permission to access location again
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "This application has not been granted the necessary permissions", Toast.LENGTH_SHORT);
            return;
        }
        //Location updates every 5 seconds or every 10 meters
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                10,
                new LocationListener() {
                    public void onLocationChanged(Location location) {
                        //Create contentValues object and insert it into database using ContentProvider
                        ContentValues contentValues = new ContentValues();
                        String longt = String.format("%f",location.getLongitude());
                        String langt = String.format("%f",location.getLatitude());
                        contentValues.put("LONG", longt);
                        contentValues.put("LANG", langt);
                        Uri uri = getContentResolver().insert(Uri.parse("content://com.example.user.test/save"), contentValues);
                        Log.i("ServiceLocationChanged", "Uri returned: "+uri);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                    }
                }
        );
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //LocationManager registration.
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        startListening();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Location Service is now started", Toast.LENGTH_SHORT).show();
        this.hasStarted = true;
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Location Service is now stopped", Toast.LENGTH_LONG).show();
        this.hasStarted = false;
    }
}
