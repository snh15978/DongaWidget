package univ.donga.dw.carte_receiver;

import univ.donga.dw.thread.WidgetCarteDownThread;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;

public class WidgetCarteReceiverTwoFour extends AppWidgetProvider {
	//프레퍼런스에서 사용하기위한 변수
	public static String CARTETWOFOUR = "univ.donga.dw.carte_receiver.WidgetCarteReceiverTwoFour";
	private static WidgetCarteDownThread mThreadTwoFour;
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		for(int i = 0; i<appWidgetIds.length; i++)
			UpdateWidgetTwoFour(context, appWidgetManager, appWidgetIds[i]);
	}
	public static void UpdateWidgetTwoFour(Context context, AppWidgetManager appWidgetManager, int widgetId)
	{
		//BR은 5초안에 결과가 나와야함으로 다운받는기능들을 하는 쓰레드를 제작하여 동작시킨다.
		mThreadTwoFour = new WidgetCarteDownThread(context, appWidgetManager, widgetId, CARTETWOFOUR);
		mThreadTwoFour.start();
	}
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		for(int i = 0; i<appWidgetIds.length; i++)
		{
			SharedPreferences prefs = context.getSharedPreferences(CARTETWOFOUR, 0);
			SharedPreferences.Editor editor = prefs.edit();
			//여기 삭제할것을 삽입한다.
			editor.remove("carte" + appWidgetIds[i]);
			editor.commit();
		}
	}
}
