package vlc.awl.jp;

import android.content.Context;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by liwentian on 2017/10/12.
 */

public class RtspSurfaceRender implements RtspHelper.RtspCallback {

    private ByteBuffer mBuffer;



    private RtspListener listener;

    public RtspSurfaceRender(Context context) {
        this.context = context;
        RtspHelper.getInstance().setContext(context);
        mBuffer = ByteBuffer.allocateDirect(1280 * 720 * 4).order(ByteOrder.nativeOrder());
    }

    public void setRtspUrl(String url) {
        RtspHelper.getInstance().createPlayer(url, 1280, 720, this);
    }

    Context context;

    public void releasePlayer() {
        RtspHelper.getInstance().releasePlayer();
    }

    public void setRtspListener(RtspListener listener){
        this.listener = listener;
    }
    @Override
    public void onPreviewFrame(final ByteBuffer buffer, int width, int height) {
        synchronized (mBuffer) {
            mBuffer.rewind();

            buffer.rewind();
            mBuffer.put(buffer);
        }


        if (listener != null) {
            listener.onReceived(mBuffer, width, height);
        }
    }

    public interface RtspListener {
        public void onReceived(Buffer buffer, int width, int height);
    }
}
