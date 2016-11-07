package univ.donga.dw.carte_receiver;

import univ.donga.dw.thread.WidgetCarteDownThread;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;

public class WidgetCarteReceiverTwoFour extends AppWidgetProvider {
	//�����۷������� ����ϱ����� ����
	public static String CARTETWOFOUR = "univ.donga.dw.carte_receiver.WidgetCarteReceiverTwoFour";
	private static WidgetCarteDownThread mThreadTwoFour;
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		for(int i = 0; i<appWidgetIds.length; i++)
			UpdateWidgetTwoFour(context, appWidgetManager, appWidgetIds[i]);
	}
	public static void UpdateWidgetTwoFour(Context context, AppWidgetManager appWidgetManager, int widgetId)
	{
		//BR�� 5�ʾȿ� ����� ���;������� �ٿ�޴±�ɵ��� �ϴ� �����带 �����Ͽ� ���۽�Ų��.
		mThreadTwoFour = new WidgetCarteDownThread(context, appWidgetManager, widgetId, CARTETWOFOUR);
		mThreadTwoFour.start();
	}
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		for(int i = 0; i<appWidgetIds.length; i++)
		{
			SharedPreferences prefs = context.getSharedPreferences(CARTETWOFOUR, 0);
			SharedPreferences.Editor editor = prefs.edit();
			//���� �����Ұ��� �����Ѵ�.
			editor.remove("carte" + appWidgetIds[i]);
			editor.commit();
		}
	}
}
