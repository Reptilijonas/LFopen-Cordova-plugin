var plugin = {
    test: function(str, successCallback, errorCallback) {
        alert("pries cordova.exec");
        cordova.exec(successCallback, errorCallback, 'Echo', 'echo', [str]);
        alert("po cordova.exec");
    };
}
module.exports = plugin;
