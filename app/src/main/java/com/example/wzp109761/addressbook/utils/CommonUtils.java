package com.example.wzp109761.addressbook.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class CommonUtils {

    private static OnItemClickListener onItemClickListener;
    public static interface OnItemClickListener<E> {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    //权限请求
    public static void getPer(Context context, Activity activity)
    {
        int permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED)
        {
            // 请求权限
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS}, 1);
        }
//

    }
    /**
     * 根据URL获取Bitmap
     * */

    public static Bitmap getHttpBitmap(String url){
        Bitmap bitmap=null;
        URL myUrl;
        try {
            myUrl=new URL(url);
            HttpURLConnection conn=(HttpURLConnection)myUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.connect();
            InputStream is=conn.getInputStream();
            bitmap= BitmapFactory.decodeStream(is);
            //把bitmap转成圆形
            //bitmap=toRoundBitmap(bitmap);
            is.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //返回圆形bitmap
        return bitmap;
    }
}
