package com.pstavrianidis.vlc;

import android.util.Log;

public class VlcPlayer {

    private Object channels;
    private Object idx;

    public <channels, idx> Object[] stream(channels idx) {
        Log.i("Channels", (String) channels);
        Log.i("Index", (String) idx);
        return new Object[]{channels, idx};
    }
}
