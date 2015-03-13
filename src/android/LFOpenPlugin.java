package com.giedrius.plugin;
 
import java.io.IOException;
import java.util.Map;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.lf.api.License;
import com.lf.api.VTManager;
import com.lf.api.events.AuthorizationEvent;
import com.lf.api.events.Event;
import com.lf.api.exceptions.InvalidCredentialException;
import com.lf.api.exceptions.InvalidLicenseException;
import com.lf.api.models.EventListener;
import com.lf.api.models.RequestToken;
import com.lf.api.models.WorkoutStream;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.webkit.WebView;

public class LFOpenPlugin extends CordovaPlugin {
	
    public static final String ACTION_GET_WORKOUT_OBJ = "getWorkoutObj";

    
    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    	
            if (ACTION_GET_WORKOUT_OBJ.equals(action)) {

            	cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
 
                    	//Intent intent = new Intent(Intent.ACTION_EDIT).setType("vnd.android.cursor.item/event");
                    	//this.cordova.getActivity().startActivity(intent);
                    	//showCalendar(action, args, callbackContext);
                    	
                    	//Intent intent = new Intent(cordova.getActivity(), WorkoutActivity.class);
                    	//cordova.getActivity().startActivity(intent);
                    	//callbackContext.success();
                    	LFOpenEquipmentObserver lfopen = new LFOpenEquipmentObserver();
                        JSONObject workoutJsonObj = new JSONObject();
                        WorkoutStream workoutObj = lfopen.getWorkoutObj();

                        // put workout details into JSON object
						try {
							workoutJsonObj.put("accumulatedCalories", getRoundedValue(workoutObj.getAccumulatedCalories()));
							workoutJsonObj.put("accumulatedDistance", getRoundedValue(workoutObj.getAccumulatedDistance()));
							workoutJsonObj.put("accumulatedDistanceClimbed", getRoundedValue(workoutObj.getAccumulatedDistanceClimbed()));
							workoutJsonObj.put("currentHeartRate", getRoundedValue(workoutObj.getCurrentHeartRate()));
							workoutJsonObj.put("currentIncline", getRoundedValue(workoutObj.getCurrentIncline()));
							workoutJsonObj.put("currentLevel", getRoundedValue(workoutObj.getCurrentLevel()));
							workoutJsonObj.put("currentResistance", getRoundedValue(workoutObj.getCurrentResistance()));
							workoutJsonObj.put("currentSpeed", getRoundedValue(workoutObj.getCurrentSpeed()));
							workoutJsonObj.put("currentSpeedRPM", getRoundedValue(workoutObj.getCurrentSpeedRPM()));
							workoutJsonObj.put("targetCalories", getRoundedValue(workoutObj.getTargetCalories()));
							workoutJsonObj.put("targetDistance", getRoundedValue(workoutObj.getTargetDistance()));
							workoutJsonObj.put("targetDistanceClibmed", getRoundedValue(workoutObj.getTargetDistanceClibmed()));
							workoutJsonObj.put("targetHeartRate", getRoundedValue(workoutObj.getTargetHeartRate()));
							workoutJsonObj.put("targetIncline", getRoundedValue(workoutObj.getTargetIncline()));
							workoutJsonObj.put("targetSpeed", getRoundedValue(workoutObj.getTargetSpeed()));
							workoutJsonObj.put("targetWorkoutSeconds", getRoundedValue(workoutObj.getTargetWorkoutSeconds()));
							workoutJsonObj.put("workoutElapseSeconds", getRoundedValue(workoutObj.getWorkoutElapseSeconds()));
							workoutJsonObj.put("workoutState", workoutObj.getWorkoutState());							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 

                    	callbackContext.sendPluginResult(new PluginResult(Status.OK, workoutJsonObj));
                    	//callbackContext.success();
                    }
            	});
               return true;
            }
            callbackContext.error("Invalid action");
            return false;
    }
    
    public boolean showCalendar(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            if (ACTION_GET_WORKOUT_OBJ.equals(action)) { 
                JSONObject arg_object = args.getJSONObject(0);
                Intent calIntent = new Intent(Intent.ACTION_EDIT)
                    .setType("vnd.android.cursor.item/event")
                    .putExtra("beginTime", arg_object.getLong("startTimeMillis"))
                    .putExtra("endTime", arg_object.getLong("endTimeMillis"))
                    .putExtra("title", arg_object.getString("title"))
                    .putExtra("description", arg_object.getString("description"))
                    .putExtra("eventLocation", arg_object.getString("eventLocation"));
             
                
                
               this.cordova.getActivity().startActivity(calIntent);
               callbackContext.success();
               return true;
            }
            callbackContext.error("Invalid action");
            return false;
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        } 
    }
    
    // no need to show loads of numbers after decimal point
    private double getRoundedValue(double val)
    {
    	val = val * 100;
    	val = (double)((int) val);
    	val = val / 100;
    	return val;
    }
    
    private void loginUser() throws JSONException
    {
    	Context context = cordova.getActivity().getApplicationContext();
    	
    	WebView webview = new WebView(context);
    	
    	
    	EventListener eventListner = new EventListener()
    	{
    	  public void execute(Event e) {

    	    AuthorizationEvent event  = (AuthorizationEvent) e;
    	    if(event.getEventType().equals(AuthorizationEvent.AUTHORIZATION_INIT))
    	    {
    	    }
    	    else if(event.getEventType().equals(AuthorizationEvent.AUTHORIZATION_LOGIN_SUCCESS))
    	    {
    	          //event.getRequest_token();
    	    }
    	    else if(event.getEventType().equals(AuthorizationEvent.AUTHORIZATION_NOT_ACCEPTED_BY_USER))
    	    {
    	    }
    	    else if(event.getEventType().equals(AuthorizationEvent.AUTHORIZATION_ACCEPTED))
    	    {
    	    }
    	    else if(event.getEventType().equals(AuthorizationEvent.AUTHORIZATION_COMPLETED))
    	    {
    	      //LoginOauth2Activity.this.lastAccessToken=event.getAccessToken();
    	    }   
    	  }

    	};
    	
    	VTManager.getInstance().addEventListener(AuthorizationEvent.AUTHORIZATION_INIT, eventListner);
    	VTManager.getInstance().addEventListener(AuthorizationEvent.AUTHORIZATION_COMPLETED, eventListner);
    	VTManager.getInstance().addEventListener(AuthorizationEvent.AUTHORIZATION_LOGIN_SUCCESS, eventListner);
    	VTManager.getInstance().addEventListener(AuthorizationEvent.AUTHORIZATION_ACCEPTED, eventListner);
    	VTManager.getInstance().addEventListener(AuthorizationEvent.AUTHORIZATION_NOT_ACCEPTED_BY_USER, eventListner);

    	RequestToken rt  = new RequestToken("AAAAA", "BBBBB");
    	//VTManager.getInstance().login(LoginOauth2Activity.this,  LoginOauth2Activity.this.webview,rt);
    	
    	try {
    		if (License.getInstance().isLicenseValid())
    			VTManager.getInstance().login(context, webview);
		} catch (InvalidLicenseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidCredentialException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    }
    
   /* private void registerUser()
    {
    	//logintask.execute();
    	User user = new User();
    	user.setAge(10);
    	user.setEmail("superman@yahoo.com");
    	user.setUsername("iambatman");
    	user.setPassword("password");
    	//registerUserTask.execute(user);
    	private AsyncTask registerUserTask = new AsyncTask(){

    	  @Override
    	  protected String doInBackground(User ...pParams) {
    	        
    	    try {
    	      VTManager.getInstance().;
    	    } catch (Exception e) {
    	      e.printStackTrace();
    	    }
    	  return "";
    	  }
    	  protected void onPostExecute(String result) {}
		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			return null;
		};

    	};
    } */
    
}