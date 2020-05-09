[![Android](https://img.shields.io/badge/Capacitor-Android-green.svg?style=flat)](https://capacitor.ionicframework.com/)
[![iOS](https://img.shields.io/badge/Capacitor-iOS-silver.svg?style=flat)](https://capacitor.ionicframework.com/)
[![Javascript](https://img.shields.io/badge/Capacitor-Javascript-gold.svg?style=flat)](https://capacitor.ionicframework.com/)
[![MIT License](https://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE)

<p align="center"><img src="../logo.png" alt="Capacitor Keep Screen On logo"></p>
<h1 align="center">capacitor-keep-screen-on</h1>

# Documentation
[Japanese version here](https://github.com/go-u/capacitor-keep-screen-on/tree/master/docs/ja)

# What's this?
Capacitor plugin to prevent automatic screen off.

# Implementations
## Android
`FLAG_KEEP_SCREEN_ON` is used.   
This is the recommended method described in [official documentation](https://developer.android.com/training/scheduling/wakelock#screen).

## iOS
`UIApplication.shared.isIdleTimerDisabled` is used.     
[This is equivalent](https://developer.apple.com/documentation/uikit/uiapplication/1623070-isidletimerdisabled) to Android's `FLAG_KEEP_SCREEN_ON` 

# Set up
## Install Plugin
```
npm install capacitor-keep-screen-on
```
## Sync to Capacitor project
```
npx cap sync android|ios
```

## Add code (iOS)
The code is automatically added with npx cap sync.

## Add code (Android)
Add the following to `MainActivity.java`

```
// import
import com.go.capacitor.keepscreenon.CapacitorKeepScreenOn;

// register plugin
public class MainActivity extends BridgeActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    ...
    // Initializes the Bridge
    this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
      // Additional plugins you've installed go here
      // Ex: add(TotallyAwesomePlugin.class);
       add(CapacitorKeepScreenOn.class);
    }});

// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!! comment out this block if exist (this cause error) !!!
//    if (BuildConfig.DEBUG) {
//      EnableHttpsSelfSigned.enable(findViewById(R.id.webview));
//    }
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

// get current state
CapacitorKeepScreenOn.getState()
```

# Recommeded Usage
For example, consider the following when using this plugin with video player.
- `Enable()` at start of playback
- `Disable()` at end of playback

# Demoについて
This repository contains [Android / iOS demo app](https://github.com/go-u/capacitor-keep-screen-on/tree/master/demo).  
The Demo made with Vue's framework Quasar. Quasar uses Capacitor internally.  
You can run the demo with the following command.
```
// Install Dependencies
cd ./demo
yarn install

// Android Demo (Build SPA / start Android Studio)
npx quasar dev -m capacitor -T android

// iOS Demo (Build SPA / start Xcode)
npx quasar dev -m capacitor -T ios
```

After executing the above command, Android Studio / Xcode will start up, so please execute the application respectively.
