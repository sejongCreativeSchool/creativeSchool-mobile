package com.example.practiceretrofit_coronamap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {

    final static String TMAP_PROJECT_KEY = "l7xx0c502b89f2e249daa747221c8bc8beeb";
    final static int MY_PERMISSIONS_REQUEST_GPS = 100;

    String[] permissionTest = new String[1];

    private LinearLayout linearLayoutTmap;
    private TMapView tMapView;
    private TMapGpsManager tMapGpsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayoutTmap = (LinearLayout) findViewById(R.id.linearLayoutTmap);
        tMapView = new TMapView(this);
        tMapGpsManager = new TMapGpsManager(this);

        permissionTest[0] = Manifest.permission.ACCESS_FINE_LOCATION;

        /*
        //퍼미션 체크 확인
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this,"권한 승인이 필요합니다",Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                requestPermissions(permissionTest,MY_PERMISSIONS_REQUEST_GPS);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_GPS);
                Toast.makeText(this,"000부분 사용을 위해 gps 권한이 필요합니다.2",Toast.LENGTH_LONG).show();

            }
        }
        else tMapGpsManager.OpenGps();
        */


        //티맵 등록 및 레이아웃에 추가
        tMapView.setSKTMapApiKey(TMAP_PROJECT_KEY);
        linearLayoutTmap.addView(tMapView);

        //현재 보는 방향
        //tMapView.setCompassMode(true);

        setGPS();


        //현위치 아이콘 표시
        tMapView.setIconVisibility(true);

    }

    void setGPS()
    {
        //티맵 gps 설정
        tMapGpsManager.setMinTime(500);
        tMapGpsManager.setMinDistance(5);
        tMapGpsManager.setProvider(TMapGpsManager.NETWORK_PROVIDER); // 네트워크로 현재위치 받기
        tMapGpsManager.OpenGps();
        tMapGpsManager.setProvider(TMapGpsManager.GPS_PROVIDER); //GPS로 현재위치 받
        tMapGpsManager.OpenGps();
    }

    @Override
    public void onLocationChange(Location location) {

        double lat = location.getLatitude();
        double lon = location.getLongitude();
        Log.d("loc", "lat = " + lat + "   lon = " + lon);
        tMapView.setCenterPoint(lon, lat);

        tMapView.setLocationPoint(lon, lat);
    }

    @Override
    protected void onStop() {
        tMapGpsManager.CloseGps();
        super.onStop();
    }
}
