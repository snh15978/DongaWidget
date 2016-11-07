package univ.donga.dw.widget.event;

import univ.donga.dw.carte_receiver.WidgetCarteReceiverTwoFour;
import univ.donga.dw.carte_receiver.WidgetCarteReceiverTwoTwo;
import univ.donga.dw.widget.info.DataManager;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
public class WidgetCarteConfigMakeBtnEvent implements Button.OnClickListener{

	private Context context;
	private DataManager dataManager;
	public WidgetCarteConfigMakeBtnEvent(Context context, DataManager dataManager)
	{
		this.context = context;
		this.dataManager = dataManager;
	}
	
	public void onClick(View v) {
		//이 위쳇은 어떤어떤것을 띄울것인가 저장한다. mId별로
		String imsiStr = "";
		SharedPreferences prefs = context.getSharedPreferences(dataManager.getCARTE(), 0);
		SharedPreferences.Editor editor = prefs.edit();
		for(int i = 0; i<5; i++)
		{
			if(dataManager.getCheckBox()[0][i] == true)
				imsiStr = imsiStr + "0" + i + " ";
		}
		for(int i = 0; i<6; i++)
		{
			if(dataManager.getCheckBox()[1][i] == true)
				imsiStr = imsiStr + "1" + i + " ";
		}
		editor.putString("carte" + dataManager.getmId(), imsiStr);
		editor.commit();
		
		if(dataManager.getCARTE() == WidgetCarteReceiverTwoFour.CARTETWOFOUR)
	         WidgetCarteReceiverTwoFour.UpdateWidgetTwoFour(context, AppWidgetManager.getInstance(context), dataManager.getmId());
	      else
	         WidgetCarteReceiverTwoTwo.UpdateWidgetTwoTwo(context, AppWidgetManager.getInstance(context), dataManager.getmId());
		
		Intent intent = new Intent();
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, dataManager.getmId());
		((Activity)context).setResult(((Activity)context).RESULT_OK, intent);
		((Activity)context).finish();
	}

}
