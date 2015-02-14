package com.giedrius.plugin;

import java.util.List;

import org.json.JSONObject;

import com.lf.api.EquipmentObserver;
import com.lf.api.models.WorkoutPreset;
import com.lf.api.models.WorkoutResult;
import com.lf.api.models.WorkoutStream;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class LFOpen implements EquipmentObserver {

	static WorkoutStream workoutObj = new WorkoutStream();

	@Override
	public void onAutoLoginRequest() {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void onConnected() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConsoleMaxInclineReceived(double arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConsoleMaxTimeReceived(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConsoleUnitsReceived(byte arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(Exception arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<WorkoutPreset> onSendingWorkoutPreset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onSetWorkoutInclineAckReceived(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSetWorkoutLevelAckReceived(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSetWorkoutThrAckReceived(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSetWorkoutWattsAckReceived(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onShowConsoleMessageAckReceived(boolean arg0) {
		// TODO Auto-generated method stub

	}
	
	// called every second during a workout with updated workout data.
	@Override
	public void onStreamReceived(WorkoutStream stream) {
		// TODO Auto-generated method stub
		workoutObj = stream;
	}

	@Override
	public void onWorkoutPaused() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWorkoutPresetSent() {
		// TODO Auto-generated method stub

	}

	// called after the work out is done and ended by the fitness user.
	@Override
	public void onWorkoutResultReceived(WorkoutResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWorkoutResume() {
		// TODO Auto-generated method stub

	}
	
	public WorkoutStream getWorkoutObj() {
		return workoutObj;	
	}
	
}
