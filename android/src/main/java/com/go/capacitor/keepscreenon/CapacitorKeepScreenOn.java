package com.go.capacitor.keepscreenon;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

// added for this plugin
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

@NativePlugin()
public class CapacitorKeepScreenOn extends Plugin {
    private Activity activity;

    @PluginMethod()
    public void enable(final PluginCall call) {
        this.activity = getActivity();

        getBridge().executeOnMainThread(new Runnable() {
            @Override
            public void run() {
                Window window = getActivity().getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                int flags = window.getAttributes().flags;
                Boolean state = ((flags & WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) != 0);
                JSObject ret = new JSObject();
                ret.put("isEnabled", state);
                call.success(ret);
            }
        });
    }

    @PluginMethod()
    public void disable(final PluginCall call) {
        this.activity = getActivity();

        getBridge().executeOnMainThread(new Runnable() {
            @Override
            public void run() {
                Window window = getActivity().getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);;
                int flags = window.getAttributes().flags;
                Boolean state = ((flags & WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) != 0);
                JSObject ret = new JSObject();
                ret.put("isEnabled", state);
                call.success(ret);
            }
        });
    }

    @PluginMethod()
    public void getState(final PluginCall call) {
        this.activity = getActivity();

        getBridge().executeOnMainThread(new Runnable() {
            @Override
            public void run() {
                Window window = getActivity().getWindow();
                int flags = window.getAttributes().flags;
                Boolean state = ((flags & WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) != 0);
                JSObject ret = new JSObject();
                ret.put("isEnabled", state);
                call.success(ret);
            }
        });
    }
}
