package com.pstavrianidis.vlc;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.Toast;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.util.ArrayList;

public class StreamActivity extends Activity implements IVLCVout.Callback {
    public String mFilePath;

    // display surface
    private SurfaceView mSurface;
    private SurfaceHolder holder;
    
    private int DisplayHeight;
    private int DisplayWidth;

    private int mVideoWidth;
    private int mVideoHeight;

    private LibVLC libvlc;
    private MediaPlayer mMediaPlayer = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.streaming_activity);

        // Receive path to play from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mFilePath = extras.getString("link");
        }

        mSurface = (SurfaceView) findViewById(R.id.surface);
        holder = mSurface.getHolder();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        DisplayHeight = displayMetrics.heightPixels;
        DisplayWidth = displayMetrics.widthPixels;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setSize(mVideoWidth, mVideoHeight);
    }

    @Override
    protected void onResume() {
        super.onResume();
        createPlayer(mFilePath);
    }



    /*************
     * Surface
     *************/

    private void setSize(int width, int height) {
        mVideoWidth = width;
        mVideoHeight = height;
        if (mVideoWidth * mVideoHeight <= 1)
            return;

        if(holder == null || mSurface == null)
            return;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;

        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        if (w > h && isPortrait || w < h && !isPortrait) {
            int i = w;
            w = h;
            h = i;
        }

        float videoAR = (float) mVideoWidth / (float) mVideoHeight;
        float screenAR = (float) w / (float) h;

        if (screenAR < videoAR)
            h = (int) (w / videoAR);
        else
            w = (int) (h * videoAR);

        // force surface buffer size
        holder.setFixedSize(mVideoWidth, mVideoHeight);

        // set display size
        ViewGroup.LayoutParams lp = mSurface.getLayoutParams();
        lp.width = w;
        lp.height = h;
        mSurface.setLayoutParams(lp);
        mSurface.invalidate();

    }

    /*************
     * Player
     *************/

    public void createPlayer(String media) {
        releasePlayer();
        try {
            // Create LibVLC
            // TODO: make this more robust, and sync with audio demo
            ArrayList<String> options = new ArrayList<String>();
            options.add("--aout=opensles");
            options.add("--file-caching=2000");
            options.add("--live-caching=5000");
            options.add("--audio-time-stretch"); // time stretching
            options.add("-vvv"); // verbosity
            options.add("--http-reconnect");
            options.add("--ts-standard=auto");
            options.add("--drop-late-frames");
            options.add("--skip-frames");
            options.add("--rtsp-tcp");

            libvlc = new LibVLC(StreamActivity.this, options);

            // Create media player
            mMediaPlayer = new MediaPlayer(libvlc);
            mMediaPlayer.setEventListener(mPlayerListener);

            // Set up video output
            final IVLCVout vout = mMediaPlayer.getVLCVout();
            vout.setWindowSize(DisplayWidth, DisplayHeight);
            vout.setVideoView(mSurface);
            vout.addCallback(this);
            vout.attachViews();

            Uri uri = Uri.parse(media);
            Media m = new Media(libvlc, uri);
            m.setHWDecoderEnabled(true, false);
            m.addOption(":network-caching=150");
            m.addOption(":clock-jitter=0");
            m.addOption(":clock-synchro=0");
            mMediaPlayer.setMedia(m);
            mMediaPlayer.play();

            setSize(DisplayWidth, DisplayHeight);


        } catch (Exception e) {
            Toast.makeText(this, "Error creating player!", Toast.LENGTH_LONG).show();
        }
    }

        @Override
    public void onSurfacesCreated(IVLCVout vlcVout) {

    }

    @Override
    public void onSurfacesDestroyed(IVLCVout vlcVout) {

    }
}
