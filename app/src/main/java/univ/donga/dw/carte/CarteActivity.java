package univ.donga.dw.carte;

import java.util.Calendar;

import univ.donga.dw.R;
import univ.donga.dw.carte.event.CarteActivityGridMenuEvent;
import univ.donga.dw.carte.info.DataManager;
import univ.donga.dw.carte.info.ViewManager;
import univ.donga.dw.carte_receiver.WidgetCarteReceiverTwoFour;
import univ.donga.dw.carte_receiver.WidgetCarteReceiverTwoTwo;
import univ.donga.dw.thread.CarteDownThread;
import android.app.Activity;
import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class CarteActivity extends Activity {
	private ViewManager viewManager;
	private DataManager dataManager;
    public static ProgressDialog mProgress = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);
        viewManager = new ViewManager(this);
        dataManager = new DataManager(this);
        
        try {
			// 위젯을 클릭할경우 해당위젯을 업데이트 시킨다.
			Intent intent = getIntent();
			// 클릭한 위젯을 찾고
			String widget = intent.getStringExtra("CARTE");
			if (widget.equals(WidgetCarteReceiverTwoFour.CARTETWOFOUR)) {
				AppWidgetManager mgr = AppWidgetManager.getInstance(CarteActivity.this);
				Intent update = new Intent(
						AppWidgetManager.ACTION_APPWIDGET_UPDATE);
				update.setClass(CarteActivity.this, WidgetCarteReceiverTwoFour.class);
				update.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, mgr
						.getAppWidgetIds(new ComponentName(CarteActivity.this,
								WidgetCarteReceiverTwoFour.class)));
				CarteActivity.this.sendBroadcast(update);
			} else if (widget.equals(WidgetCarteReceiverTwoTwo.CARTETWOTWO)) {
				AppWidgetManager mgr = AppWidgetManager.getInstance(CarteActivity.this);
				Intent update = new Intent(
						AppWidgetManager.ACTION_APPWIDGET_UPDATE);
				update.setClass(CarteActivity.this, WidgetCarteReceiverTwoTwo.class);
				update.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, mgr
						.getAppWidgetIds(new ComponentName(CarteActivity.this,
								WidgetCarteReceiverTwoTwo.class)));
				CarteActivity.this.sendBroadcast(update);
			}
		} catch (Exception e) {
			Log.e("error", "CARTE WIDGET UPDATE ERROR");
		}
		// 대기창을 띄우고 쓰레드시작
		mProgress = ProgressDialog.show(CarteActivity.this, "Wait",
				"Downloading...");
		mProgress.setCancelable(true);
		dataManager.setmThread(new CarteDownThread(mAfterDown, dataManager
				.getCarteCampusManager()));
		dataManager.getmThread().start();
	}
    
    //쓰레드의 결과를 받아서 출력을 담당하는 핸들러
	Handler mAfterDown = new Handler() {
		public void handleMessage(Message msg)
    	{
			mProgress.dismiss();
			viewManager.getTitle1().setText(dataManager.getCarteCampusManager().getCarteCampusInfo(0).getCampusName());
			viewManager.getGrid1().setAdapter(new CarteActivityMenuAdapter(CarteActivity.this, dataManager.getCarteCampusManager().getCarteCampusInfo(0).getLocations()));
			viewManager.getGrid1().setOnItemClickListener(new CarteActivityGridMenuEvent(CarteActivity.this, dataManager.getCarteCampusManager().getCarteCampusInfo(0).getLocations(), dataManager.getCarteCampusManager().getCarteCampusInfo(0).getMenus()));
			viewManager.getTitle2().setText(dataManager.getCarteCampusManager().getCarteCampusInfo(1).getCampusName());
			viewManager.getGrid2().setAdapter(new CarteActivityMenuAdapter(CarteActivity.this, dataManager.getCarteCampusManager().getCarteCampusInfo(1).getLocations()));
			viewManager.getGrid2().setOnItemClickListener(new CarteActivityGridMenuEvent(CarteActivity.this, dataManager.getCarteCampusManager().getCarteCampusInfo(1).getLocations(), dataManager.getCarteCampusManager().getCarteCampusInfo(1).getMenus()));
			if(viewManager.getTitle1().getText().toString().length() == 0)
				Toast.makeText(CarteActivity.this, "인터넷 연결을 확인해 주세요.",
						Toast.LENGTH_SHORT).show();
    	}
    };
}