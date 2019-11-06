
  Pod::Spec.new do |s|
    s.name = 'CapacitorKeepScreenOn'
    s.version = '0.0.1'
    s.summary = 'capacitor plugin for keeping screen on '
    s.license = 'MIT'
    s.homepage = 'https://github.com/go-u/capacitor-keep-screen-on'
    s.author = 'go'
    s.source = { :git => 'https://github.com/go-u/capacitor-keep-screen-on', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.dependency 'Capacitor'
  end