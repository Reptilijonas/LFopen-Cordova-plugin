package com.giedrius.plugin;

import com.lf.api.EquipmentManager;
import com.lf.api.License;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

public class WorkoutActivity extends Activity {

	LFOpen cEquipment = new LFOpen();
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		//super.onCreate(savedInstanceState);
		
		if (License.getInstance().isLicenseValid())
		{
			//infoText.setText("App license is valid!");	
		}
		
		EquipmentManager.DEBUG_MODE = true;
		
		bindService(new Intent(this, EquipmentManager.class), new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				EquipmentManager.getInstance().registerObserver(cEquipment);
				// needs com.android.future.usb.UsbManager in order to run this
			    //EquipmentManager.getInstance().start();
			}
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				
			}
		},
		Context.BIND_AUTO_CREATE);
		
	}
}
