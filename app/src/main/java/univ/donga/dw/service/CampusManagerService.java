package univ.donga.dw.service;

import univ.donga.dw.info.carte.CarteCampusManager;
import univ.donga.dw.info.job.CollegeManager;
import univ.donga.dw.info.seat.SeatCampusManager;
import univ.donga.dw.service.book_info.BookListServiceManager;
import univ.donga.dw.thread.BookListDownThread;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class CampusManagerService extends Service {
	
	//학식메뉴관리
	public static CarteCampusManager carteCampusManager;
	
	//도서관 좌석관리
	public static SeatCampusManager seatCampusManager;
	
	//서비스에서 대출도서관리
	private BookListServiceManager bookListServiceManager;
	
	//자동로그인
	public static boolean auto_login;
	//주기알람여부
	public static boolean auto_book_alarm;
	
	//직업게시판관리
	public static CollegeManager jobCollegeManager;
	//주기알람여부 알림할 과를 기록
	public static boolean auto_job_alarm;
	public static int select_departement;
	
	public void onCreate()
	{
		super.onCreate();
		Log.e("error", "Service Create");
		//서비스가 종료되지 않도록 포그라운드로 시작한다.
		startForeground(1, new Notification());
		
		//각부분 초기화 부분
		carteCampusManager = new CarteCampusManager();
		//
		seatCampusManager = new SeatCampusManager();
		//
		bookListServiceManager = new BookListServiceManager(this);
		auto_login = false;
		auto_book_alarm = false;
		//
		jobCollegeManager = new CollegeManager(); 
		auto_job_alarm = false;
		select_departement = -1;
		
		//6시간뒤에 알림메시지가 울린다.
		alarmHandler.sendEmptyMessageDelayed(0, 21600000);
		//alarmHandler.sendEmptyMessageDelayed(0, 10000);
		
	}
	public void onDestroy()
	{
		Log.e("error", " Destroy on Service");
		super.onDestroy();
	}
	public int onStartCommand (Intent intent, int flags, int startId)
	{
		Log.e("error", " Start on Service");
		return START_REDELIVER_INTENT;
	}
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.e("error", "bind on Service");
		return null;
	}
	
	//특정 시간이 지나면 웹에서 다시 파싱을 하기 위한 핸들러
	Handler alarmHandler = new Handler() {
		public void handleMessage(Message msg) {
			//6시간뒤에 알림메시지가 울린다.(재등록)
			alarmHandler.sendEmptyMessageDelayed(0, 21600000);
			//alarmHandler.sendEmptyMessageDelayed(0, 10000);
			
			//대출도서 주기알람을 해놓은경우
			if(auto_book_alarm == true)
			{
				Log.e("error", "download BookList of Web inside Service");
				//다운받는 스레드를 시작시킨다.
				new BookListDownThread(CampusManagerService.this, bookListServiceManager).start();
			}
			
			//취업게시판 알림을 true로 해논경우
			if(auto_job_alarm == true)
			{
				
			}
		}
	};
}
