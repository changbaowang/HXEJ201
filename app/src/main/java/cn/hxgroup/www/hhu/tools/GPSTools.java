package cn.hxgroup.www.hhu.tools;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import cn.hxgroup.www.hhu.ui.feature.GpsSetActivity;

/**
 * Created by hex170 on 2016/8/23.
 * GPS工具类
 */
public class GPSTools {
    private Location location;
    private GpsSetActivity activity;

    public GPSTools(GpsSetActivity activity) {
        this.activity = activity;
        getGPS();
    }

    private void getGPS() {
        LocationManager lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String bestProvider = lm.getBestProvider(criteria, true);
        Log.i("yao", "bestProvider = " + bestProvider);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        location = lm.getLastKnownLocation(bestProvider);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("yao", location.toString());
                activity.getData(location);

            }
            @Override
            public void onProviderDisabled(String arg0) {
                Log.i("yao", arg0);

            }
            @Override
            public void onProviderEnabled(String arg0) {
                Log.i("yao", arg0);
            }

            @Override
            public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
                Log.i("yao", "onStatusChanged");
            }
        };
        lm.requestLocationUpdates(bestProvider, 500, 0, locationListener);
    }
}
