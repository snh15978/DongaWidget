package univ.donga.dw.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ServiceStartBR extends BroadcastReceiver{
	public void onReceive(Context context, Intent intent) {
		Log.e("error", "BroadcastReceiver Start");
		//부팅이 완료되면 서비스 시작
		context.startService(new Intent(context, CampusManagerService.class));
	}
}
