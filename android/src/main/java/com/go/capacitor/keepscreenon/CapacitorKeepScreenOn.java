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
    public static final String BROADCAST_KEY_KEEP_SCREEN_ON_PLUGIN = "KEEP_SCREEN_ON_PLUGIN";

    @PluginMethod()
    public void enable(PluginCall call) {
        Intent localBroadCastIntent = new Intent(BROADCAST_KEY_KEEP_SCREEN_ON_PLUGIN);
        localBroadCastIntent.putExtra("MODE", true);
        dispatchIntent(call, localBroadCastIntent, true);
    }

    @PluginMethod()
    public void disable(PluginCall call) {
        Intent localBroadCastIntent = new Intent(BROADCAST_KEY_KEEP_SCREEN_ON_PLUGIN);
        localBroadCastIntent.putExtra("MODE", false);
        dispatchIntent(call, localBroadCastIntent, false);
    }

    private void dispatchIntent(PluginCall call, Intent intent, Boolean mode) {
        Boolean hasDispatched = LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(intent);
        JSObject ret = new JSObject();
        if (hasDispatched) {
            ret.put("isEnabled", mode);
            call.success(ret);
        } else {
            call.error("LocalBroadcast dispatch failed");
        }
    }
}
