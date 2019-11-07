package com.go.capacitor.keepscreenon;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

// added for this plugin
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

@NativePlugin()
public class CapacitorKeepScreenOn extends Plugin {

    // Keys For LoacalBroadcast
    public static final String BROADCAST_KEY_ENABLE_KEEP_SCREEN_ON = "ENABLE_KEEP_SCREEN_ON";
    public static final String BROADCAST_KEY_DISABLE_KEEP_SCREEN_ON = "DISABLE_KEEP_SCREEN_ON";

    @PluginMethod()
    public void enable(PluginCall call) {
        Intent localBroadCastIntent = new Intent(BROADCAST_KEY_ENABLE_KEEP_SCREEN_ON);
        dispatchIntent(call, localBroadCastIntent);
    }

    @PluginMethod()
    public void disable(PluginCall call) {
        Intent localBroadCastIntent = new Intent(BROADCAST_KEY_DISABLE_KEEP_SCREEN_ON);
        dispatchIntent(call, localBroadCastIntent);
    }

    private void dispatchIntent(PluginCall call, Intent intent) {
        Boolean hasDispatched = LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(intent);
        JSObject ret = new JSObject();
        if (hasDispatched) {
            Boolean mode = (BROADCAST_KEY_ENABLE_KEEP_SCREEN_ON.equals(intent.toString()));
            ret.put("isEnabled", mode);
            call.success(ret);
        } else {
            call.error("LocalBroadcast dispatch failed");
        }
    }
}
