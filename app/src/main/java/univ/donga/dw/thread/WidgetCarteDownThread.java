package univ.donga.dw.thread;

import java.util.Calendar;
import java.util.StringTokenizer;

import univ.donga.dw.R;
import univ.donga.dw.carte.CarteActivity;
import univ.donga.dw.info.carte.CarteCampusManager;
import univ.donga.dw.service.CampusManagerService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.RemoteViews;


public class WidgetCarteDownThread extends Thread{
	//����������Ʈ�� ���� �ʵ��
	private Context context;
	private AppWidgetManager appWidgetManager;
	private int widgetId;
	private CarteDownThread carteDownThread;
	//�����۷������� ����ϱ����� ����
	private String CARTE;
    private String imsi;
	
	//ķ�۽����� �Ĵ������� ������ Ŭ����
	private CarteCampusManager carteCampusManager = CampusManagerService.carteCampusManager;
	public WidgetCarteDownThread(Context context, AppWidgetManager appWidgetManager, int widgetId, String CARTE)
	{
		//����������Ʈ�� ���� �ʵ��
		this.context = context;
		this.widgetId = widgetId;
		this.appWidgetManager = appWidgetManager;
		this.CARTE = CARTE;
	}
	public void run()
	{
		//�Ľ��ϴ� �۾��� �Ѵ�.
		carteDownThread = new CarteDownThread(null, carteCampusManager);
		carteDownThread.start();
		//�Ľ��۾��� ���������� ��ٸ���
		while(carteDownThread.isAlive()){};
		
		//�����۷����� ����
		SharedPreferences prefs = context.getSharedPreferences(CARTE, 0);
		//�ش� ID�� �ش��ϴ� ������ �ϰϿ´�.
		StringTokenizer stk = new StringTokenizer(prefs.getString("carte" + widgetId, ""), " ");
		
		//���� ��¥�� ������ ���� ���� Ŭ����
		//���� ��¥��  ������ �ʵ�
		Calendar ca;
		String[] dayOfweekArr = {"��", "��", "ȭ", "��", "��", "��", "��"};
		int year, month, day;
		String dayOfweek;
        ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);//���� �� ���������
		month = ca.get(Calendar.MONTH) + 1; // ���� �� ��������
		day = ca.get(Calendar.DAY_OF_MONTH); // ���� �� ��������
		dayOfweek = dayOfweekArr[ca.get(Calendar.DAY_OF_WEEK)-1];//���� ������ ����
		
		//���ϸ��� ��¥�� �ѷ��ְ�
		String imsiStr = month + "�� " + day + "�� " + dayOfweek + "����\n";
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_widget_carte);
		//���� �������� �Ĵ缱�� �������� ������ �о�´�.
		while(stk.hasMoreTokens())
		{
			imsi = stk.nextToken();
			imsiStr = imsiStr
					+ carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10)) // ķ�۽����� �ٿ��ְ�
							.getCampusName()
					+ ": "
					+ carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10)) // ��ķ�۽����� �Ĵ�� �ٿ��ְ�
							.getLocation((int) (Integer.parseInt(imsi) % 10))
					+ "\n"
					+ carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10)) // �׽Ĵ翡�� �޴� �ٿ��ش�.
							.getMenu((int) (Integer.parseInt(imsi) % 10)) + "\n";
		}

		//������ �о�������� �̿��Ͽ� �ؽ�Ʈ�鿡 ���� ������ �۾��� ������.
		SpannableStringBuilder sp = null;
		sp = new SpannableStringBuilder(imsiStr);
		stk = new StringTokenizer(prefs.getString("carte" + widgetId, ""), " ");
		while(stk.hasMoreTokens())
		{
			//ķ�۽���� �Ĵ��̸� ���̰��
			int start, end;
			imsi = stk.nextToken();
			// ���ڿ��� ������ġ�� ã��
			start = imsiStr.indexOf(carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10))
					.getCampusName()
					+ ": "
					+ carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10))
							.getLocation((int) (Integer.parseInt(imsi) % 10)));
			// ����ġ�� ã�´�.
			end = start
					+ (carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10))
							.getCampusName() + ": " + carteCampusManager.getCarteCampusInfo((int) (Integer
							.parseInt(imsi) / 10)).getLocation((int) (Integer
							.parseInt(imsi) % 10))).length();
			sp.setSpan(new ForegroundColorSpan(Color.RED), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		views.setTextViewText(R.id.widgetresult, sp);
		
		//������ Ŭ���ҽÿ� ������ ��Ƽ��Ƽ�� ����ش�.
		Intent intent = new Intent(context, CarteActivity.class);
		intent.putExtra("CARTE", CARTE);
		PendingIntent pending = PendingIntent.getActivity(context,  widgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.widgetlayout, pending);
		appWidgetManager.updateAppWidget(widgetId, views);
	}
}
