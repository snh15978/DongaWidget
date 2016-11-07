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
		
		Toast.makeText(this, "1���� �ʰ��Ͽ� �����ϴ°��� ��õ���� �ʽ��ϴ�.", Toast.LENGTH_SHORT).show();
		// ��ư�� �̺�Ʈ�� �޾��ش�.
		viewManager.getMake_btn().setOnClickListener(new WidgetCarteConfigMakeBtnEvent(this, dataManager));
		// �Ǻ����� ���鼭
		TextView imsiText;
		CheckBox imsiCheck;
		LinearLayout imsiLayout;
		for (int i = 0; i < 2; i++) {
			// ķ�۽� �̸��� �Ѹ���.
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

		// �ϴ� ���з� �����Ѵ�.
		setResult(RESULT_CANCELED);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		// ��Ű�� ������� �̺�Ʈ �߻�
		case KeyEvent.KEYCODE_BACK:
			// ��׶���� �̵���Ų��.
			moveTaskToBack(true);
			finish();
		case KeyEvent.KEYCODE_MENU:
			return false;
		}
		return true;
	}

}
