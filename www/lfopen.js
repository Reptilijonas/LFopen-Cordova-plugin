var lfopen = {
    updateWorkoutObj: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'LFOpenPlugin', // mapped to our native Java class called "LFOpenPlugin"
            'getWorkoutObj', // with this action name
            [{}]
        ); 
    }
}
module.exports = lfopen;