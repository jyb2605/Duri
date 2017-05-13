package durithon.duri;

import android.app.FragmentManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static Netty_DuriClient netty_duriClient;
    public static char ascii = (char)0x2593;


    GoogleMap mMap;
    ArrayList<LatLng> routePoints;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        netty_duriClient = new Netty_DuriClient(getApplicationContext());
        netty_duriClient.start();

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap map) {

        mMap = map;
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));

    }

    //위도경도 지도에 뿌리기
    public void diplay(double lat, double lon){
        LatLng point = new LatLng(lat,lon);

//마커 옵션
        MarkerOptions markerOption = new MarkerOptions().position(point).title("Hello Maps ");

//카메라 업데이트 최근 좌표로 설정, 카메라 줌인 17로 설정
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(point, 17);
//실제 이동
        mMap.animateCamera(cameraUpdate);
//마커옵션에 따라 마커 생성
        marker = mMap.addMarker(markerOption);

        routePoints.add(point);

        //추가된 정보로 선그리기
        drawPrimaryLinePath(routePoints);
    }
    private void drawPrimaryLinePath(ArrayList<LatLng> routePoints) {
        //추가된 위치 데이터 정보로 polyline 그리기
        if (mMap == null) {
            return;
        }

        if (routePoints.size() < 2) {
            return;
        }

        PolylineOptions options = new PolylineOptions();

        options.color(Color.parseColor("#CC0000FF"));
        options.width(5);
        options.visible(true);

        for (LatLng locRecorded : routePoints) {
            options.add(new LatLng(locRecorded.latitude, locRecorded.longitude));
        }

        mMap.addPolyline(options);
    }


}

