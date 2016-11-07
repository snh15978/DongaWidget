package univ.donga.dw.widget;

import java.util.ArrayList;

import univ.donga.dw.R;
import univ.donga.dw.carte_receiver.WidgetCarteReceiverTwoFour;
import univ.donga.dw.carte_receiver.WidgetCarteReceiverTwoTwo;
import univ.donga.dw.widget.event.WidgetCarteConfigCheckEvent;
import univ.donga.dw.widget.event.WidgetCarteConfigMakeBtnEvent;
import univ.donga.dw.widget.event.WidgetCarteConfigMenuClickEvent;
import univ.donga.dw.widget.event.WidgetCarteConfigMenuTouchEvent;
import univ.donga.dw.widget.info.DataManager;
import univ.donga.dw.widget.info.ViewManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WidgetCarteConfigTwoTwoActivity extends Activity {
	
	private ViewManager viewManager;
	private DataManager dataManager;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_carte_config);
		viewManager = new ViewManager(this);
		dataManager = new DataManager(this, WidgetCarteReceiverTwoTwo.CARTETWOTWO);
		
		Toast.makeText(this, "1개를 초과하여 선택하는것은 추천하지 않습니다.", Toast.LENGTH_SHORT).show();
		// 버튼에 이벤트를 달아준다.
		viewManager.getMake_btn().setOnClickListener(new WidgetCarteConfigMakeBtnEvent(this, dataManager));
		// 판복문을 돌면서
		TextView imsiText;
		CheckBox imsiCheck;
		LinearLayout imsiLayout;
		for (int i = 0; i < 2; i++) {
			// 캠퍼스 이름을 뿌린다.
			imsiText = new TextView(this);
			imsiText.setText(dataManager.getCampusName()[i]);
			imsiText.setTextColor(Color.BLACK);
			imsiText.setTextSize(20);
			imsiText.setBackgroundColor(Color.GRAY);
			viewManager.getMainLayout().addView(imsiText);

			for (int j = 0; j < 6; j++) {
				if (i == 0 && j > 4) {
					break;
				}
				imsiLayout = (LinearLayout) dataManager.getmInflater().inflate(
						R.layout.custom_config_menu, null);
				imsiLayout
						.setOnTouchListener(new WidgetCarteConfigMenuTouchEvent(
								imsiLayout));
				imsiLayout
						.setOnClickListener(new WidgetCarteConfigMenuClickEvent(
								imsiLayout));
				imsiText = (TextView) imsiLayout.findViewById(R.id.lable);
				imsiText.setText(dataManager.getLocation()[i][j]);
				imsiText.setTextColor(Color.BLACK);
				imsiCheck = (CheckBox) imsiLayout.findViewById(R.id.checkbox);
				imsiCheck
						.setOnCheckedChangeListener(new WidgetCarteConfigCheckEvent(
								dataManager.getCheckBox(), i, j));
				viewManager.getMainLayout().addView(imsiLayout);
			}
		}

		// 일단 실패로 가정한다.
		setResult(RESULT_CANCELED);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		// 백키를 누를경우 이벤트 발생
		case KeyEvent.KEYCODE_BACK:
			// 백그라운드로 이동시킨다.
			moveTaskToBack(true);
			finish();
		case KeyEvent.KEYCODE_MENU:
			return false;
		}
		return true;
	}

}
