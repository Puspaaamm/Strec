package com.strec.stream;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.pedro.encoder.input.video.CameraOpenGl;
import com.pedro.encoder.video.FormatVideoEncoder;
import com.pedro.library.rtmp.RtmpDisplay;
import com.pedro.rtmp.utils.ConnectCheckerRtmp;

import com.pedro.encoder.input.gl.render.filters.ColorFilterRender;
import com.pedro.encoder.input.gl.render.filters.ContrastFilterRender;
import com.pedro.encoder.input.gl.render.filters.SharpnessFilterRender;

public class StreamService extends Service implements ConnectCheckerRtmp {

    private static final String CHANNEL_ID = "StrecStreamingEngineChannel";
    private static final int NOTIFICATION_ID = 911;
    private static RtmpDisplay rtmpDisplay;
    private MediaProjection mediaProjection;

    @Override
    public void onCreate() {
        super.onCreate();
        // Hardware Optimized Low-Latency Ingestion Pipeline Surface binding mapping setup
        rtmpDisplay = new RtmpDisplay(getApplicationContext(), true, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) return START_NOT_STICKY;

        String action = intent.getAction();
        if ("CHANGE_FILTER".equals(action)) {
            handleLiveShaderInjection(intent.getStringExtra("FILTER_TYPE"));
            return START_STICKY;
        }

        createPersistentEngineNotificationChannel();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("STREC PIPELINE ACTIVE")
                .setContentText("Broadcasting screen assets at ultra 1080p 60fps natively...")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();

        startForeground(NOTIFICATION_ID, notification);

        String streamUrl = intent.getStringExtra("STREAM_URL");
        int resultCode = intent.getIntExtra("RESULT_CODE", -1);
        Intent data = intent.getParcelableExtra("DATA");

        MediaProjectionManager manager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        if (manager != null && data != null) {
            mediaProjection = manager.getMediaProjection(resultCode, data);
            rtmpDisplay.setMediaProjection(mediaProjection);
        }

        // Tuned 1080p 60FPS Bitrate profile target system mapping initialization
        if (rtmpDisplay.prepareVideo(1920, 1080, 60, 6000000, 0, 320, FormatVideoEncoder.SURFACE) && rtmpDisplay.prepareAudio()) {
            rtmpDisplay.startStream(streamUrl);
        } else {
            stopSelf();
        }

        return START_STICKY;
    }

    private void handleLiveShaderInjection(String filterType) {
        if (rtmpDisplay == null || !rtmpDisplay.isStreaming()) return;
        
        if (rtmpDisplay.getGlInterface() != null) {
            switch (filterType) {
                case "VIVID":
                    ContrastFilterRender vivid = new ContrastFilterRender();
                    vivid.setContrast(1.4f);
                    rtmpDisplay.getGlInterface().setFilter(vivid);
                    break;
                case "CLASSIC":
                    ColorFilterRender classic = new ColorFilterRender();
                    classic.setColorMatrix(new float[]{
                            0.393f, 0.769f, 0.189f, 0,
                            0.349f, 0.686f, 0.168f, 0,
                            0.272f, 0.534f, 0.131f, 0,
                            0, 0, 0, 1
                    });
                    rtmpDisplay.getGlInterface().setFilter(classic);
                    break;
                case "OCEAN":
                    ColorFilterRender ocean = new ColorFilterRender();
                    ocean.setColorMatrix(new float[]{
                            0.5f, 0.0f, 0.0f, 0,
                            0.0f, 0.8f, 0.0f, 0,
                            0.0f, 0.0f, 1.5f, 0,
                            0, 0, 0, 1
                    });
                    rtmpDisplay.getGlInterface().setFilter(ocean);
                    break;
                case "4K SHARP":
                    SharpnessFilterRender sharp = new SharpnessFilterRender();
                    sharp.setSharpness(2.0f);
                    rtmpDisplay.getGlInterface().setFilter(sharp);
                    break;
                default:
                    rtmpDisplay.getGlInterface().clearFilter();
                    break;
            }
        }
    }

    public static void stopStreaming() {
        if (rtmpDisplay != null && rtmpDisplay.isStreaming()) {
            rtmpDisplay.stopStream();
        }
    }

    private void createPersistentEngineNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "STREC Broadcaster Native Pipeline Engine",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) manager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onDestroy() {
        stopStreaming();
        if (mediaProjection != null) {
            mediaProjection.stop();
        }
        super.onDestroy();
    }

    @Nullable @Override public IBinder onBind(Intent intent) { return null; }
    @Override public void onConnectionSuccessRtmp() {}
    @Override public void onConnectionFailedRtmp(String reason) { stopSelf(); }
    @Override public void onNewBitrateRtmp(long bitrate) {}
    @Override public void onDisconnectRtmp() {}
    @Override public void onAuthErrorRtmp() {}
    @Override public void onAuthSuccessRtmp() {}
}

