package com.pstavrianidis.vlc;

import android.content.Intent;
import android.os.Parcelable;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

@CapacitorPlugin(name = "VlcPlayer")
public class VlcPlayerPlugin extends Plugin implements Serializable {

    private VlcPlayer implementation = new VlcPlayer();

    @PluginMethod
    public void stream(PluginCall call) {
        JSArray channels = call.getArray("channels");
        Integer idx = call.getInt("idx");

        JSObject ret = new JSObject();
        ret.put("channels", channels);
        ret.put("id", idx);
        call.resolve(ret);

        Intent intent = new Intent(getContext(), StreamActivity.class);
        intent.putExtra("channels", (Serializable) channels);
        intent.putExtra("channel_idx", idx);
        getContext().startActivity(intent);
    }
}
