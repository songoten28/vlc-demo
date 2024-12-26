package com.example.frank.vlcdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.nio.Buffer;

import vlc.awl.jp.RtspSurfaceRender;

public class MainActivity extends Activity {

    public static final String URL = "rtsp://admin:L23FF105@116.110.52.161:554/cam/realmonitor?channel=1&subtype=0";
//    public static final String URL = "rtsp://root:dsoft123@192.168.4.24/axis-media/media.amp";

    private RtspSurfaceRender mRender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRender = new RtspSurfaceRender(this);
        mRender.setRtspUrl(URL);

        mRender.setRtspListener(new RtspSurfaceRender.RtspListener() {
            @Override
            public void onReceived(Buffer buffer, int width, int height) {
                Log.e("widt", width + "/" + height);
                buffer.rewind();
                Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                bitmap.copyPixelsFromBuffer(buffer);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((ImageView) findViewById(R.id.image)).setImageBitmap(bitmap);
                    }
                });
            }
        });
    }

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
        mRender.releasePlayer();
        super.onDestroy();
    }
}
