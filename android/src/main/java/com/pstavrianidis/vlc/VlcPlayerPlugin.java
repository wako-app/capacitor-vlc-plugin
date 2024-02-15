package com.wako.plugin.capacitor.vlc;

import android.content.Intent;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.Serializable;

@CapacitorPlugin(name = "VlcPlayer")
public class VlcPlayerPlugin extends Plugin implements Serializable {

    private VlcPlayer implementation = new VlcPlayer();

    @PluginMethod
    public void stream(PluginCall call) {
        String link = call.getString("link");

        JSObject ret = new JSObject();
        ret.put("link", link);
        call.resolve(ret);

        Intent intent = new Intent(getContext(), StreamActivity.class);
        intent.putExtra("link", link);
        getContext().startActivity(intent);
    }
}
