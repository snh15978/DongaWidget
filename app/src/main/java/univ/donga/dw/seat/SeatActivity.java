package univ.donga.dw.seat;

import univ.donga.dw.R;
import univ.donga.dw.seat.event.SeatDontNotifyBtnEvent;
import univ.donga.dw.seat.event.SeatListClickEvent;
import univ.donga.dw.seat.info.DataManager;
import univ.donga.dw.seat.info.ViewManager;
import univ.donga.dw.thread.SeatDownThread;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class SeatActivity extends Activity {

	private ViewManager viewManager;
	private DataManager dataManager;
	//쓰레드를 실행하고 있는동안 동작할 프로그래스
    public static ProgressDialog mProgress = null;
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seat);
		viewManager = new ViewManager(this);
		dataManager = new DataManager(this);
		
		//도서관 자리를 읽어오는 스레드
		mProgress = ProgressDialog.show(this, "Wait",	"Downloading...");
		dataManager.setSeatDownThread(new SeatDownThread(mAfterDown, dataManager.getSeatCampusManager()));
		dataManager.getSeatDownThread().start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seat, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		
		switch(item.getItemId())
		{
		case R.id.seat_menu_start_end_day:
			int year, month, day;
			//커스텀뷰 초기화
			viewManager.initSeatMenuStartEndCustomView();
			//프레퍼런스에서 파일을 읽어서 기존에 저장되어있던 날짜들을 가지고온다.
			dataManager.initPrefs("SEAT_NOTIFY_START_AND_END");
			// 시작날짜를 선택할수있는 데이터피커에 이벤트를 달아준다.
			year = dataManager.getPrefs().getInt("START_YEAR", viewManager.getStart_day().getYear());
			month = dataManager.getPrefs().getInt("START_MONTH", viewManager.getStart_day().getMonth());
			day = dataManager.getPrefs().getInt("START_DAY", viewManager.getStart_day().getDayOfMonth());
			viewManager.getStart_day().init(year, month-1, day, null);
			// 끝 날짜를 선택할수 있는 데이터피커에 이벤트를 달아준다.
			year = dataManager.getPrefs().getInt("END_YEAR", viewManager.getEnd_day().getYear());
			month = dataManager.getPrefs().getInt("END_MONTH", viewManager.getEnd_day().getMonth());
			day = dataManager.getPrefs().getInt("END_DAY", viewManager.getEnd_day().getDayOfMonth());
			viewManager.getEnd_day().init(year, month-1, day, null);
			
			new AlertDialog.Builder(this)
			.setTitle("기간을 선택하세요")
			.setView(viewManager.getSeatMenuCustomView())
			.setPositiveButton("확인", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//예외처리
					if(viewManager.getStart_day().getYear() < viewManager.getEnd_day().getYear())
						Toast.makeText(SeatActivity.this, "통지 시작날짜가 종료날자보다 작을순 없습니다.", Toast.LENGTH_SHORT).show();
					if(viewManager.getStart_day().getMonth()+1 < viewManager.getEnd_day().getMonth()+1)
						Toast.makeText(SeatActivity.this, "통지 시작날짜가 종료날자보다 작을순 없습니다.", Toast.LENGTH_SHORT).show();
					if(viewManager.getStart_day().getDayOfMonth() < viewManager.getEnd_day().getDayOfMonth())
						Toast.makeText(SeatActivity.this, "통지 시작날짜가 종료날자보다 작을순 없습니다.", Toast.LENGTH_SHORT).show();

					//시작년월일을 저장할 파일을열고 키워드를 이용하여 저장해준다.
					dataManager.initPrefs("SEAT_NOTIFY_START_AND_END");
					dataManager.initEditor();
					
					dataManager.getEditor().putInt("START_YEAR", viewManager.getStart_day().getYear());
					dataManager.getEditor().putInt("START_MONTH", viewManager.getStart_day().getMonth()+1);
					dataManager.getEditor().putInt("START_DAY", viewManager.getStart_day().getDayOfMonth());
					
					dataManager.getEditor().putInt("END_YEAR", viewManager.getEnd_day().getYear());
					dataManager.getEditor().putInt("END_MONTH", viewManager.getEnd_day().getMonth()+1);
					dataManager.getEditor().putInt("END_DAY", viewManager.getEnd_day().getDayOfMonth());
					//적용
					dataManager.getEditor().commit();
				}
			})
			.setNegativeButton("취소", null)
			.show();
			return true;
		case R.id.seat_mnue_time_term:
			int term;
			//커스텀뷰 초기화
			viewManager.initSeatMenuTimeTermCustomView();
			//프레퍼런스에서 파일을 읽어 기존의 시간텀을 읽어온다.
			dataManager.initPrefs("SEAT_NOTIFY_TIME_TERM");
			term = dataManager.getPrefs().getInt("TIME_TERM", 5);
			//기존에 저장되어있던 정보로 초기화
			viewManager.getTime_term().setText(String.valueOf(term));
			
			new AlertDialog.Builder(this)
			.setTitle("통지간격을 입력해주세요")
			.setView(viewManager.getSeatMenuCustomView())
			.setPositiveButton("확인", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//통지 가격을 저장할 파일을 열고 키워들르 이용하여 저장한다.
					dataManager.initPrefs("SEAT_NOTIFY_TIME_TERM");
					dataManager.initEditor();
					dataManager.getEditor().putInt("TIME_TERM", Integer.parseInt(viewManager.getTime_term().getText().toString().trim()));
					//적용
					dataManager.getEditor().commit();
				}
			})
			.setNegativeButton("취소", null)
			.show();
			return true;
		case R.id.seat_mnue_select_item:
			//프레퍼런스에서 파일을 읽어서 기존에 저장되어있던 체크목록을 가지고온다.
			dataManager.initPrefs("SEAT_NOTIFY_CHECKED_ITEMS");
			String[] items = new String[dataManager.getSeatCampusManager().sizeOfseatCampusInfo()];
			for(int i = 0; i<dataManager.getSeatCampusManager().sizeOfseatCampusInfo(); i++)
			{
				items[i] = dataManager.getSeatCampusManager().getSeatCampusInfo(i).getName();
				dataManager.getCheckedItems()[i] = dataManager.getPrefs().getBoolean("ITEM_"+i, false);
			}
			new AlertDialog.Builder(this)
			.setTitle("통지할 아이템을 선택해주세요")
			.setMultiChoiceItems(items, dataManager.getCheckedItems(), new DialogInterface.OnMultiChoiceClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					//선택여부 저장한다.
					dataManager.getCheckedItems()[which] = isChecked;
					
				}
			})
			.setPositiveButton("확인", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//선택된 아이템을 저장할 파일을열고 키워드를 이용하여 저장해준다.
					dataManager.initPrefs("SEAT_NOTIFY_CHECKED_ITEMS");
					dataManager.initEditor();
					for(int i = 0; i<dataManager.getSeatCampusManager().sizeOfseatCampusInfo(); i++)
						dataManager.getEditor().putBoolean("ITEM_"+i, dataManager.getCheckedItems()[i]);
					//적용
					dataManager.getEditor().commit();
				}
			})
			.setNegativeButton("취소", null)
			.show();
			return true;
		}
		return false;
	}
 
	// 쓰레드의 결과를 받아서 출력을 담당하는 핸들러
	Handler mAfterDown = new Handler() {
		public void handleMessage(Message msg) {
			mProgress.dismiss();
			
			//시간을 보여준다.
			viewManager.getSeat_time().setText(dataManager.getSeatCampusManager().getDownTime());
			//각 캠퍼스가 뿌려질 메뉴
			dataManager.setSeatListAdapter(new SeatListAdapter(SeatActivity.this, dataManager.getSeatCampusManager()));
			viewManager.getSeat_list().setAdapter(dataManager.getSeatListAdapter());
			viewManager.getSeat_list().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			//각 리스트에 대한 이벤트를 정의해준다.
			viewManager.getSeat_list().setOnItemClickListener(new SeatListClickEvent(SeatActivity.this, dataManager.getSeatCampusManager()));

			//아이템 초기화를위한 버튼에 이벤트를 달아준다.
			viewManager.getSeat_dont_notify().setOnClickListener(new SeatDontNotifyBtnEvent(SeatActivity.this, dataManager));
			
			//아이템선택을위한 배열을 생성하고
			dataManager.setCheckedItems(new boolean[dataManager.getSeatCampusManager().sizeOfseatCampusInfo()]);
			
		}
	};
}
