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
	
	//�нĸ޴�����
	public static CarteCampusManager carteCampusManager;
	
	//������ �¼�����
	public static SeatCampusManager seatCampusManager;
	
	//���񽺿��� ���⵵������
	private BookListServiceManager bookListServiceManager;
	
	//�ڵ��α���
	public static boolean auto_login;
	//�ֱ�˶�����
	public static boolean auto_book_alarm;
	
	//�����Խ��ǰ���
	public static CollegeManager jobCollegeManager;
	//�ֱ�˶����� �˸��� ���� ���
	public static boolean auto_job_alarm;
	public static int select_departement;
	
	public void onCreate()
	{
		super.onCreate();
		Log.e("error", "Service Create");
		//���񽺰� ������� �ʵ��� ���׶���� �����Ѵ�.
		startForeground(1, new Notification());
		
		//���κ� �ʱ�ȭ �κ�
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
		
		//6�ð��ڿ� �˸��޽����� �︰��.
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
	
	//Ư�� �ð��� ������ ������ �ٽ� �Ľ��� �ϱ� ���� �ڵ鷯
	Handler alarmHandler = new Handler() {
		public void handleMessage(Message msg) {
			//6�ð��ڿ� �˸��޽����� �︰��.(����)
			alarmHandler.sendEmptyMessageDelayed(0, 21600000);
			//alarmHandler.sendEmptyMessageDelayed(0, 10000);
			
			//���⵵�� �ֱ�˶��� �س������
			if(auto_book_alarm == true)
			{
				Log.e("error", "download BookList of Web inside Service");
				//�ٿ�޴� �����带 ���۽�Ų��.
				new BookListDownThread(CampusManagerService.this, bookListServiceManager).start();
			}
			
			//����Խ��� �˸��� true�� �س���
			if(auto_job_alarm == true)
			{
				
			}
		}
	};
}
