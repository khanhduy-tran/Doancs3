package com.khanhduy.doancuoiki.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.khanhduy.doancuoiki.Adapter.DeluxeAdapter;
import com.khanhduy.doancuoiki.DirectionHelper.DirectionFinder;
import com.khanhduy.doancuoiki.DirectionHelper.DirectionFinderListener;
import com.khanhduy.doancuoiki.DirectionHelper.FetchURL;
import com.khanhduy.doancuoiki.DirectionHelper.Route;
import com.khanhduy.doancuoiki.DirectionHelper.TaskLoadedCallback;
import com.khanhduy.doancuoiki.R;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMap#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMap extends Fragment implements TaskLoadedCallback{
    View view;
    private MapView mMapView;
    private GoogleMap googleMap1;
    ImageView imgLocation,imgChiDuong,imgHotel,imgZoomInt,imgZoomOut;
    FusedLocationProviderClient locationProviderClient;
    MarkerOptions locationHotel,locationUser;

    private Polyline currentPolyline;

    public FragmentMap() {
    }

    public static FragmentMap newInstance() {
        FragmentMap fragmentMap = new FragmentMap();
        return fragmentMap;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        locationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap1 = mMap;
                //To add marker
                LatLng sydney = new LatLng(15.975175620995481, 108.2532266679186);
                googleMap1.addMarker(new MarkerOptions().position(sydney).title("Đà Nẵng Hotel").snippet("Nam Kì Khởi Nghĩa,Quận Ngũ Hành Sơn, Đà Nẵng"));
                // For zooming functionality

                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                googleMap1.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }
        });
        imgLocation = (ImageView) view.findViewById(R.id.imgLocation);
        imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                   Task<Location> task = locationProviderClient.getLastLocation();
                   task.addOnSuccessListener(new OnSuccessListener<Location>() {
                       @Override
                       public void onSuccess(Location location) {
                           mMapView.getMapAsync(new OnMapReadyCallback() {
                               @Override
                               public void onMapReady(@NonNull GoogleMap googleMap) {
                                   LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                                   MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Vị trí hiện tại của bạn");
                                   googleMap.addMarker(markerOptions);
                                   googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                               }
                           });
                       }
                   });
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
        imgChiDuong = (ImageView) view.findViewById(R.id.imgChiDuong);
        imgChiDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Task<Location> task = locationProviderClient.getLastLocation();
                    task.addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            mMapView.getMapAsync(new OnMapReadyCallback() {
                                @Override
                                public void onMapReady(@NonNull GoogleMap googleMap) {
                                        googleMap1 = googleMap;
                                        locationHotel = new MarkerOptions().position(new LatLng(15.975175620995481, 108.2532266679186));
                                    LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                                        locationUser = new MarkerOptions().position(latLng);
                                        googleMap1.addMarker(locationUser);
                                    new FetchURL(FragmentMap.this).execute(getUrl(locationUser.getPosition(), locationHotel.getPosition(), "driving"), "driving");


                                }
                            });
                        }
                    });
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
        imgHotel = (ImageView) view.findViewById(R.id.imgHotel);
        imgHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        googleMap1 = googleMap;
                        //To add marker
                        LatLng sydney = new LatLng(15.975175620995481, 108.2532266679186);
                        googleMap1.addMarker(new MarkerOptions().position(sydney).title("Đà Nẵng Hotel").snippet("Nam Kì Khởi Nghĩa,Quận Ngũ Hành Sơn, Đà Nẵng"));
                        // For zooming functionality

                        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                    }
                });
            }
        });
        imgZoomInt = (ImageView) view.findViewById(R.id.imgZoomInt);
        imgZoomInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleMap1.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });
        imgZoomOut = (ImageView) view.findViewById(R.id.imgZoomOut);
        imgZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleMap1.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
        return view;

    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.map_api_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = googleMap1.addPolyline((PolylineOptions) values[0]);
    }

}

