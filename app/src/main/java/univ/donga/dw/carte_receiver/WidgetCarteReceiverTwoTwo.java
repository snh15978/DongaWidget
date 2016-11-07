package univ.donga.dw.carte_receiver;

import univ.donga.dw.thread.WidgetCarteDownThread;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;

public class WidgetCarteReceiverTwoTwo extends AppWidgetProvider {
	//�����۷������� ����ϱ����� ����
	public static String CARTETWOTWO = "univ.donga.dw.carte_receiver.WidgetCarteReceiverTwoTwo";
	private static WidgetCarteDownThread mThreadTwoTwo;
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		for(int i = 0; i<appWidgetIds.length; i++)
			UpdateWidgetTwoTwo(context, appWidgetManager, appWidgetIds[i]);
	}
	public static void UpdateWidgetTwoTwo(Context context, AppWidgetManager appWidgetManager, int widgetId)
	{
		//BR�� 5�ʾȿ� ����� ���;������� �ٿ�޴±�ɵ��� �ϴ� �����带 �����Ͽ� ���۽�Ų��.
		mThreadTwoTwo = new WidgetCarteDownThread(context, appWidgetManager, widgetId, CARTETWOTWO);
		mThreadTwoTwo.start();
	}
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		for(int i = 0; i<appWidgetIds.length; i++)
		{
			SharedPreferences prefs = context.getSharedPreferences(CARTETWOTWO, 0);
			SharedPreferences.Editor editor = prefs.edit();
			//���� �����Ұ��� �����Ѵ�.
			editor.remove("carte" + appWidgetIds[i]);
			editor.commit();
		}
		
	}
}
