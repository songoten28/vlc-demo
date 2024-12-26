package com.example.frank.vlcdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.nio.Buffer;


public class MainActivity extends Activity {

    public static final String URL = "rtsp://admin:L23FF105@116.110.52.161:554/cam/realmonitor?channel=1&subtype=0";
//    public static final String URL = "rtsp://root:dsoft123@192.168.4.24/axis-media/media.amp";


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
