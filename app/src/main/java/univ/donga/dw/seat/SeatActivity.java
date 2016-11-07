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
	//�����带 �����ϰ� �ִµ��� ������ ���α׷���
    public static ProgressDialog mProgress = null;
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seat);
		viewManager = new ViewManager(this);
		dataManager = new DataManager(this);
		
		//������ �ڸ��� �о���� ������
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
			//Ŀ���Һ� �ʱ�ȭ
			viewManager.initSeatMenuStartEndCustomView();
			//�����۷������� ������ �о ������ ����Ǿ��ִ� ��¥���� ������´�.
			dataManager.initPrefs("SEAT_NOTIFY_START_AND_END");
			// ���۳�¥�� �����Ҽ��ִ� ��������Ŀ�� �̺�Ʈ�� �޾��ش�.
			year = dataManager.getPrefs().getInt("START_YEAR", viewManager.getStart_day().getYear());
			month = dataManager.getPrefs().getInt("START_MONTH", viewManager.getStart_day().getMonth());
			day = dataManager.getPrefs().getInt("START_DAY", viewManager.getStart_day().getDayOfMonth());
			viewManager.getStart_day().init(year, month-1, day, null);
			// �� ��¥�� �����Ҽ� �ִ� ��������Ŀ�� �̺�Ʈ�� �޾��ش�.
			year = dataManager.getPrefs().getInt("END_YEAR", viewManager.getEnd_day().getYear());
			month = dataManager.getPrefs().getInt("END_MONTH", viewManager.getEnd_day().getMonth());
			day = dataManager.getPrefs().getInt("END_DAY", viewManager.getEnd_day().getDayOfMonth());
			viewManager.getEnd_day().init(year, month-1, day, null);
			
			new AlertDialog.Builder(this)
			.setTitle("�Ⱓ�� �����ϼ���")
			.setView(viewManager.getSeatMenuCustomView())
			.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//����ó��
					if(viewManager.getStart_day().getYear() < viewManager.getEnd_day().getYear())
						Toast.makeText(SeatActivity.this, "���� ���۳�¥�� ���ᳯ�ں��� ������ �����ϴ�.", Toast.LENGTH_SHORT).show();
					if(viewManager.getStart_day().getMonth()+1 < viewManager.getEnd_day().getMonth()+1)
						Toast.makeText(SeatActivity.this, "���� ���۳�¥�� ���ᳯ�ں��� ������ �����ϴ�.", Toast.LENGTH_SHORT).show();
					if(viewManager.getStart_day().getDayOfMonth() < viewManager.getEnd_day().getDayOfMonth())
						Toast.makeText(SeatActivity.this, "���� ���۳�¥�� ���ᳯ�ں��� ������ �����ϴ�.", Toast.LENGTH_SHORT).show();

					//���۳������ ������ ���������� Ű���带 �̿��Ͽ� �������ش�.
					dataManager.initPrefs("SEAT_NOTIFY_START_AND_END");
					dataManager.initEditor();
					
					dataManager.getEditor().putInt("START_YEAR", viewManager.getStart_day().getYear());
					dataManager.getEditor().putInt("START_MONTH", viewManager.getStart_day().getMonth()+1);
					dataManager.getEditor().putInt("START_DAY", viewManager.getStart_day().getDayOfMonth());
					
					dataManager.getEditor().putInt("END_YEAR", viewManager.getEnd_day().getYear());
					dataManager.getEditor().putInt("END_MONTH", viewManager.getEnd_day().getMonth()+1);
					dataManager.getEditor().putInt("END_DAY", viewManager.getEnd_day().getDayOfMonth());
					//����
					dataManager.getEditor().commit();
				}
			})
			.setNegativeButton("���", null)
			.show();
			return true;
		case R.id.seat_mnue_time_term:
			int term;
			//Ŀ���Һ� �ʱ�ȭ
			viewManager.initSeatMenuTimeTermCustomView();
			//�����۷������� ������ �о� ������ �ð����� �о�´�.
			dataManager.initPrefs("SEAT_NOTIFY_TIME_TERM");
			term = dataManager.getPrefs().getInt("TIME_TERM", 5);
			//������ ����Ǿ��ִ� ������ �ʱ�ȭ
			viewManager.getTime_term().setText(String.valueOf(term));
			
			new AlertDialog.Builder(this)
			.setTitle("���������� �Է����ּ���")
			.setView(viewManager.getSeatMenuCustomView())
			.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//���� ������ ������ ������ ���� Ű���鸣 �̿��Ͽ� �����Ѵ�.
					dataManager.initPrefs("SEAT_NOTIFY_TIME_TERM");
					dataManager.initEditor();
					dataManager.getEditor().putInt("TIME_TERM", Integer.parseInt(viewManager.getTime_term().getText().toString().trim()));
					//����
					dataManager.getEditor().commit();
				}
			})
			.setNegativeButton("���", null)
			.show();
			return true;
		case R.id.seat_mnue_select_item:
			//�����۷������� ������ �о ������ ����Ǿ��ִ� üũ����� ������´�.
			dataManager.initPrefs("SEAT_NOTIFY_CHECKED_ITEMS");
			String[] items = new String[dataManager.getSeatCampusManager().sizeOfseatCampusInfo()];
			for(int i = 0; i<dataManager.getSeatCampusManager().sizeOfseatCampusInfo(); i++)
			{
				items[i] = dataManager.getSeatCampusManager().getSeatCampusInfo(i).getName();
				dataManager.getCheckedItems()[i] = dataManager.getPrefs().getBoolean("ITEM_"+i, false);
			}
			new AlertDialog.Builder(this)
			.setTitle("������ �������� �������ּ���")
			.setMultiChoiceItems(items, dataManager.getCheckedItems(), new DialogInterface.OnMultiChoiceClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					//���ÿ��� �����Ѵ�.
					dataManager.getCheckedItems()[which] = isChecked;
					
				}
			})
			.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//���õ� �������� ������ ���������� Ű���带 �̿��Ͽ� �������ش�.
					dataManager.initPrefs("SEAT_NOTIFY_CHECKED_ITEMS");
					dataManager.initEditor();
					for(int i = 0; i<dataManager.getSeatCampusManager().sizeOfseatCampusInfo(); i++)
						dataManager.getEditor().putBoolean("ITEM_"+i, dataManager.getCheckedItems()[i]);
					//����
					dataManager.getEditor().commit();
				}
			})
			.setNegativeButton("���", null)
			.show();
			return true;
		}
		return false;
	}
 
	// �������� ����� �޾Ƽ� ����� ����ϴ� �ڵ鷯
	Handler mAfterDown = new Handler() {
		public void handleMessage(Message msg) {
			mProgress.dismiss();
			
			//�ð��� �����ش�.
			viewManager.getSeat_time().setText(dataManager.getSeatCampusManager().getDownTime());
			//�� ķ�۽��� �ѷ��� �޴�
			dataManager.setSeatListAdapter(new SeatListAdapter(SeatActivity.this, dataManager.getSeatCampusManager()));
			viewManager.getSeat_list().setAdapter(dataManager.getSeatListAdapter());
			viewManager.getSeat_list().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			//�� ����Ʈ�� ���� �̺�Ʈ�� �������ش�.
			viewManager.getSeat_list().setOnItemClickListener(new SeatListClickEvent(SeatActivity.this, dataManager.getSeatCampusManager()));

			//������ �ʱ�ȭ������ ��ư�� �̺�Ʈ�� �޾��ش�.
			viewManager.getSeat_dont_notify().setOnClickListener(new SeatDontNotifyBtnEvent(SeatActivity.this, dataManager));
			
			//�����ۼ��������� �迭�� �����ϰ�
			dataManager.setCheckedItems(new boolean[dataManager.getSeatCampusManager().sizeOfseatCampusInfo()]);
			
		}
	};
}
