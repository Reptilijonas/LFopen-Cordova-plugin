var plugin = {
    test: function(str, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Echo", "echo", [str]);
    };
}
module.exports = plugin;
