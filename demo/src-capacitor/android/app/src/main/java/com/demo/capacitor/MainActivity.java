package com.demo.capacitor;

import android.os.Bundle;

import com.getcapacitor.BridgeActivity;
import com.getcapacitor.Plugin;

import java.util.ArrayList;

// plugin
import com.go.capacitor.keepscreenon.CapacitorKeepScreenOn;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.view.WindowManager;
// todo: remove this import for test dialog
import android.app.AlertDialog;

public class MainActivity extends BridgeActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Initializes the Bridge
    this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
      // Additional plugins you've installed go here
      // Ex: add(TotallyAwesomePlugin.class);
       add(CapacitorKeepScreenOn.class);
    }});

    // For KEEP_SCREEN_ON plugin, Register eepScreenOnPluginReceiver
    if(this.keepScreenOnPluginReceiver == null) {
      IntentFilter intentFilter = new IntentFilter(CapacitorKeepScreenOn.BROADCAST_KEY_KEEP_SCREEN_ON_PLUGIN);
      this.keepScreenOnPluginReceiver = new KeepScreenOnPluginReceiver();
      LocalBroadcastManager.getInstance(this).registerReceiver(this.keepScreenOnPluginReceiver, intentFilter);
    }
  }

  private KeepScreenOnPluginReceiver keepScreenOnPluginReceiver = null;
  public class KeepScreenOnPluginReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
      // todo: remove this test message
      // new AlertDialog.Builder(MainActivity.this).setTitle("plugin register test").setMessage("this is test message").setPositiveButton("OK", null).show();
      Boolean mode = intent.getBooleanExtra("MODE", false);
      if (mode) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
      } else {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
      }
    }
  }
}
