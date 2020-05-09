[![Android](https://img.shields.io/badge/Capacitor-Android-green.svg?style=flat)](https://capacitor.ionicframework.com/)
[![iOS](https://img.shields.io/badge/Capacitor-iOS-silver.svg?style=flat)](https://capacitor.ionicframework.com/)
[![Javascript](https://img.shields.io/badge/Capacitor-Javascript-gold.svg?style=flat)](https://capacitor.ionicframework.com/)
[![MIT License](https://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE)

<p align="center"><img src="../logo.png" alt="Capacitor Keep Screen On logo"></p>
<h1 align="center">capacitor-keep-screen-on</h1>

# :star: Important :star: 
**AndroidXのライブラリ向けに開発する場合v1.0.0以降を使ってください。**  
(v0.0.xはAndroidX以前の古い環境用です)

# Documentation
[English version here](https://github.com/go-u/capacitor-keep-screen-on/tree/master/docs/en)

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

// get current state
CapacitorKeepScreenOn.getState()
```

# Recommeded Usage
例えば動画再生にこのプラグインを使う場合は以下のような配慮をしましょう。  
- 再生開始時に`enable()`
- 再生終了時に`disable()`

# Demoについて
このリポジトリにはVueのフレームワークである [Quasarを使ったAndroid/iOSのデモアプリ](https://github.com/go-u/capacitor-keep-screen-on/tree/master/demo) が含まれています。   
Quasarは内部でCapacitorを利用しています。デモは以下のコマンドで動かせます。  
```
// Install Dependencies
cd ./demo
yarn install

// Android Demo (Build SPA / start Android Studio)
npx quasar dev -m capacitor -T android

// iOS Demo (Build SPA / start Xcode)
npx quasar dev -m capacitor -T ios
```

上記のコマンドを実行した後、Android Studio / Xcode が立ち上がるのでそれぞれでアプリを実行して下さい。
