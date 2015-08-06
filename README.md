GlassUp plugin for Cordova
==========================

Cordova Plugin for GlassUp - implemented for Android 4.0 and above.

## Installing the plugin

The plugin conforms to the Cordova plugin specification, it can be installed
using the Cordova / Phonegap command line interface.

It is at a very early stage of development so you ave to download or clone this
repository and install it with something like:

    cordova plugin add /path/to/cordova-glassup --link --save

## Using the plugin

The plugin creates the function `window.glassup_echo` to send strings to GlassUp.

For now there's no way to listen to the connection status so you have to wait a
few seconds before call the function for the first time.

Example - Send a string to GlassUp:
```javascript
setTimeout(function () {
  window.glassup_echo("Hello.", function(echoValue) {
    alert(echoValue); // should alert "Hello.".
  });
}, 30000);
```

## License
MIT
