package durithon.duri;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

/**
 * Created by sh112 on 2017-05-14.
 */

public class MapFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {
    View v;
    GoogleMap mMap;
    ArrayList<LatLng> routePoints;
    Marker marker;
    MapView mapview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_map, container, false);

        MapsInitializer.initialize(getActivity());

        //googlemap 뷰를 fragment에 띄움
        mapview = (MapView) v.findViewById(R.id.map_fragment);
        mapview.onCreate(savedInstanceState);
        mapview.onResume(); // oncreate와 onresume을 써야 맵뷰가 보인다.
        mapview.getMapAsync(this);//getmap에서 getmapAsync로 바뀜

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(getActivity(),"fragment",Toast.LENGTH_LONG).show();
        return v;

        //diplay();
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
