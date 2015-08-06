 window.glassup_echo = function(str, callback) {
     cordova.exec(callback, function(err) {
         callback('Nothing to echo.');
     }, "GlassUp", "echo", [str]);
 };
