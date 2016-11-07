package univ.donga.dw.seat.info;

import univ.donga.dw.info.seat.SeatCampusManager;
import univ.donga.dw.seat.SeatListAdapter;
import univ.donga.dw.service.CampusManagerService;
import univ.donga.dw.thread.SeatDownThread;
import android.content.Context;
import android.content.SharedPreferences;

public class DataManager {

	private Context context;
	private SeatListAdapter seatListAdapter;
	private SeatCampusManager seatCampusManager;
	private SeatDownThread seatDownThread;
	//�����۷����� ���� �ʵ�
	private SharedPreferences prefs;
	private SharedPreferences.Editor editor;
	//������ ���� ���õ� �������� ������ �迭.
	private boolean[] checkedItems;
	public DataManager(Context context)
	{
		this.context = context;
		seatCampusManager = CampusManagerService.seatCampusManager;
	}

	public void setSeatDownThread(SeatDownThread seatDownThread) {
		this.seatDownThread = seatDownThread;
	}
	public SeatDownThread getSeatDownThread() {
		return seatDownThread;
	}
	public SeatCampusManager getSeatCampusManager() {
		return seatCampusManager;
	}
	public void setSeatListAdapter(SeatListAdapter seatListAdapter) {
		this.seatListAdapter = seatListAdapter;
	}
	public SeatListAdapter getSeatListAdapter() {
		return seatListAdapter;
	}

	public void initPrefs(String name)
	{
		prefs = context.getSharedPreferences(name, 0);
	}
	public void initEditor()
	{
		editor = prefs.edit();
	}
	public SharedPreferences getPrefs()
	{
		return prefs;
	}
	public SharedPreferences.Editor getEditor()
	{
		return editor;
	}
	
	public void setCheckedItems(boolean[] checkedItems) {
		this.checkedItems = checkedItems;
	}

	public boolean[] getCheckedItems() {
		return checkedItems;
	}
	
}
