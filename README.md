# capacitor-keep-screen-on
capacitor plugin for keeping screen on

# How to

## step 1: Install / Sync plugin 
```
npm install capacitor-keep-screen-on
npx cap sync android|ios
```

## step 2: Adding below code to MainActivity.java 
At your `MainActivity.java`
```
// import
import com.go.capacitor.keepscreenon.CapacitorKeepScreenOn;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.view.WindowManager;

// register plugin
public class MainActivity extends BridgeActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    ...
    ...
    // Initializes the Bridge
    this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
      // Additional plugins you've installed go here
      // Ex: add(TotallyAwesomePlugin.class);
       add(CapacitorKeepScreenOn.class);
    }});

    // For KEEP_SCREEN_ON plugin
    // Register EnableKeepScreenOnReceiver
    if(this.enableKeepScreenOnReceiver == null) {
      IntentFilter intentFilter = new IntentFilter(CapacitorKeepScreenOn.BROADCAST_KEY_ENABLE_KEEP_SCREEN_ON);
      this.enableKeepScreenOnReceiver = new EnableKeepScreenOnReceiver();
      LocalBroadcastManager.getInstance(this).registerReceiver(this.enableKeepScreenOnReceiver, intentFilter);
    }
    // Register DisableKeepScreenOnReceiver
    if(this.disableKeepScreenOnReceiver == null) {
      IntentFilter intentFilter = new IntentFilter(CapacitorKeepScreenOn.BROADCAST_KEY_DISABLE_KEEP_SCREEN_ON);
      this.disableKeepScreenOnReceiver = new DisableKeepScreenOnReceiver();
      LocalBroadcastManager.getInstance(this).registerReceiver(this.disableKeepScreenOnReceiver, intentFilter);
    }
  }

  private EnableKeepScreenOnReceiver enableKeepScreenOnReceiver = null;
  public class EnableKeepScreenOnReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
      // todo: remove this test message
      // new AlertDialog.Builder(MainActivity.this).setTitle("plugin register test").setMessage("this is test message").setPositiveButton("OK", null).show();
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
  }

  private DisableKeepScreenOnReceiver disableKeepScreenOnReceiver = null;
  public class DisableKeepScreenOnReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
      getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
  }
}
```
