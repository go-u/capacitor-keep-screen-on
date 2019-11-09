[![Android](https://img.shields.io/badge/Capacitor-Android-green.svg?style=flat)](https://capacitor.ionicframework.com/)
[![iOS](https://img.shields.io/badge/Capacitor-iOS-silver.svg?style=flat)](https://capacitor.ionicframework.com/)
[![Javascript](https://img.shields.io/badge/Capacitor-Javascript-gold.svg?style=flat)](https://capacitor.ionicframework.com/)
[![MIT License](https://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE)

<p align="center"><img src="../logo.png" alt="Capacitor Keep Screen On logo"></p>
<h1 align="center">capacitor-keep-screen-on</h1>

# Documentation
[English versione here](https://github.com/go-u/capacitor-keep-screen-on/tree/master/docs/en)

# What's this?
画面の自動オフを防ぐためのCapacitorプラグインです。  

# Implementations
## Android
`FLAG_KEEP_SCREEN_ON` を利用しています。  
これは公式ドキュメント[画面をオンのままにする](https://developer.android.com/training/scheduling/wakelock#screen)に記載された推奨方法です。

## iOS
`UIApplication.shared.isIdleTimerDisabled` を利用しています。  
これはAndroidの `FLAG_KEEP_SCREEN_ON` に[相当する方法](https://developer.apple.com/documentation/uikit/uiapplication/1623070-isidletimerdisabled)です。

# Set up
## プラグインのインストール
```
npm install capacitor-keep-screen-on
```
## Capacitorプロジェクトに同期
```
npx cap sync android|ios
```

## コードの追加(iOS)  
npx cap sync でコードが自動追加されます。

## コードの追加(Android)  
`MainActivity.java` に以下を追加  
(プラグインの都合上ReceiverをMainActivityに記載)

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
有効化/無効化のための２つのメソッドを利用するだけです。
- `enable()`  
- `disable()`
  
これはネイティブ上で以下のフラグを切り替えています。
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
例えば動画再生にこのプラグインを使う場合は以下のような配慮をしましょう。  
- 再生開始時に`enable()`
- 再生終了時に`disable()`
