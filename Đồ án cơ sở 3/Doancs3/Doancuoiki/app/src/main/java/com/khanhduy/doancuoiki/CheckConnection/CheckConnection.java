package com.khanhduy.doancuoiki.CheckConnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckConnection {
    public static boolean   haveNetworkConnection(Context context){
        boolean haveConnectedWife = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
        for(NetworkInfo ni : networkInfos){
            if (ni.getTypeName().equalsIgnoreCase("WIFI"));
            if (ni.isConnected())
                haveConnectedWife=true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"));
            if(ni.isConnected())
                haveConnectedMobile = true;
        }
        return haveConnectedWife | haveConnectedMobile;
    }
    public static void ShowToas_Short(Context context , String thongbao){
        Toast.makeText(context,thongbao,Toast.LENGTH_SHORT).show();
    }
}
