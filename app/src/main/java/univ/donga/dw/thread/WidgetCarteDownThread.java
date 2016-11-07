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
	//위젯업데이트를 위한 필드들
	private Context context;
	private AppWidgetManager appWidgetManager;
	private int widgetId;
	private CarteDownThread carteDownThread;
	//프레퍼런스에서 사용하기위한 변수
	private String CARTE;
    private String imsi;
	
	//캠퍼스마다 식당정보를 저장할 클래스
	private CarteCampusManager carteCampusManager = CampusManagerService.carteCampusManager;
	public WidgetCarteDownThread(Context context, AppWidgetManager appWidgetManager, int widgetId, String CARTE)
	{
		//위젯업데이트를 위한 필드들
		this.context = context;
		this.widgetId = widgetId;
		this.appWidgetManager = appWidgetManager;
		this.CARTE = CARTE;
	}
	public void run()
	{
		//파싱하는 작업을 한다.
		carteDownThread = new CarteDownThread(null, carteCampusManager);
		carteDownThread.start();
		//파싱작업이 끝날때까지 기다린후
		while(carteDownThread.isAlive()){};
		
		//프레퍼런스를 열고
		SharedPreferences prefs = context.getSharedPreferences(CARTE, 0);
		//해당 ID에 해당하는 정보를 일겅온다.
		StringTokenizer stk = new StringTokenizer(prefs.getString("carte" + widgetId, ""), " ");
		
		//현제 날짜를 가지고 오기 위한 클래스
		//오늘 날짜를  저장할 필드
		Calendar ca;
		String[] dayOfweekArr = {"일", "월", "화", "수", "목", "금", "토"};
		int year, month, day;
		String dayOfweek;
        ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);//현제 년 가지고오기
		month = ca.get(Calendar.MONTH) + 1; // 현제 월 가져오기
		day = ca.get(Calendar.DAY_OF_MONTH); // 현재 일 가져오기
		dayOfweek = dayOfweekArr[ca.get(Calendar.DAY_OF_WEEK)-1];//요일 가지고 오기
		
		//제일먼저 날짜를 뿌려주고
		String imsiStr = month + "월 " + day + "일 " + dayOfweek + "요일\n";
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_widget_carte);
		//위젯 설정에서 식당선택 기준으로 정보를 읽어온다.
		while(stk.hasMoreTokens())
		{
			imsi = stk.nextToken();
			imsiStr = imsiStr
					+ carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10)) // 캠퍼스명을 붙여주고
							.getCampusName()
					+ ": "
					+ carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10)) // 그캠퍼스에서 식당명 붙여주고
							.getLocation((int) (Integer.parseInt(imsi) % 10))
					+ "\n"
					+ carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10)) // 그식당에서 메뉴 붙여준다.
							.getMenu((int) (Integer.parseInt(imsi) % 10)) + "\n";
		}

		//위에서 읽어온정보를 이용하여 텍스트들에 색을 입히는 작업을 ㅎ나다.
		SpannableStringBuilder sp = null;
		sp = new SpannableStringBuilder(imsiStr);
		stk = new StringTokenizer(prefs.getString("carte" + widgetId, ""), " ");
		while(stk.hasMoreTokens())
		{
			//캠퍼스명과 식당이름 길이계산
			int start, end;
			imsi = stk.nextToken();
			// 문자열의 시작위치를 찾고
			start = imsiStr.indexOf(carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10))
					.getCampusName()
					+ ": "
					+ carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10))
							.getLocation((int) (Integer.parseInt(imsi) % 10)));
			// 끝위치를 찾는다.
			end = start
					+ (carteCampusManager.getCarteCampusInfo((int) (Integer.parseInt(imsi) / 10))
							.getCampusName() + ": " + carteCampusManager.getCarteCampusInfo((int) (Integer
							.parseInt(imsi) / 10)).getLocation((int) (Integer
							.parseInt(imsi) % 10))).length();
			sp.setSpan(new ForegroundColorSpan(Color.RED), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		views.setTextViewText(R.id.widgetresult, sp);
		
		//위젯을 클릭할시에 보여줄 액티비티를 띄워준다.
		Intent intent = new Intent(context, CarteActivity.class);
		intent.putExtra("CARTE", CARTE);
		PendingIntent pending = PendingIntent.getActivity(context,  widgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.widgetlayout, pending);
		appWidgetManager.updateAppWidget(widgetId, views);
	}
}
