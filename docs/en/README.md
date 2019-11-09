[![Android](https://img.shields.io/badge/Capacitor-Android-green.svg?style=flat)](https://capacitor.ionicframework.com/)
[![iOS](https://img.shields.io/badge/Capacitor-iOS-silver.svg?style=flat)](https://capacitor.ionicframework.com/)
[![Javascript](https://img.shields.io/badge/Capacitor-Javascript-gold.svg?style=flat)](https://capacitor.ionicframework.com/)
[![MIT License](https://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE)

<p align="center"><img src="../logo.png" alt="Capacitor Keep Screen On logo"></p>
<h1 align="center">capacitor-keep-screen-on</h1>

# Documentation
[Japanese versione here](https://github.com/go-u/capacitor-keep-screen-on/tree/master/docs/ja)

# What's this?
Capacitor plugin to prevent automatic screen off.

# Implementations
## Android
`FLAG_KEEP_SCREEN_ON` is used.   
This is the recommended method described in [official documentation](https://developer.android.com/training/scheduling/wakelock#screen).

## iOS(実装中)
`UIApplication.shared.isIdleTimerDisabled` is used.     
[This is equivalent](https://developer.apple.com/documentation/uikit/uiapplication/1623070-isidletimerdisabled) to Android's `FLAG_KEEP_SCREEN_ON` 

# Set up
Install Plugin
```
npm install capacitor-keep-screen-on
```
Sync to Capacitor project
```
npx cap sync android|ios
```

Add code (Android)
Add the following to `MainActivity.java`
(Receiver is in MainActivity for the convenience of the plugin)

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
```

# Usage
Just use two methods to enable / disable.
- `enable()`  
- `disable()`
  
This switches the following flags natively:
- Android: `FLAG_KEEP_SCREEN_ON`
- iOS: `UIApplication.shared.isIdleTimerDisabled`

```
import { Plugins } from '@capacitor/core'
const { CapacitorKeepScreenOn } = Plugins

// enable
CapacitorKeepScreenOn.enable()

// disable
CapacitorKeepScreenOn.disable()
```

# Recommeded Usage
For example, consider the following when using this plugin with video player.
- `Enable()` at start of playback
- `Disable()` at end of playback