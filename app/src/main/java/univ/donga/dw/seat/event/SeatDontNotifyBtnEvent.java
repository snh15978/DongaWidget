package univ.donga.dw.seat.event;

import univ.donga.dw.seat.SeatActivity;
import univ.donga.dw.seat.info.DataManager;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class SeatDontNotifyBtnEvent implements Button.OnClickListener{
	private Context context;
	private DataManager dataManager;
	public SeatDontNotifyBtnEvent(Context context, DataManager dataManager)
	{
		this.context = context;
		this.dataManager = dataManager;
	}
	@Override
	public void onClick(View v) {
		Toast.makeText(context, "������ ���� �ʵ��� �����Ͽ����ϴ�.", Toast.LENGTH_SHORT).show();
		//������ ���� �ʱ� ������ �Ұ�� �ʱ�ȭ�����ش�.
		for(int i = 0; i<dataManager.getSeatCampusManager().sizeOfseatCampusInfo(); i++)
			dataManager.getCheckedItems()[i] = false;
		//���õ� �������� ������ ���������� Ű���带 �̿��Ͽ� �������ش�.
		dataManager.initPrefs("SEAT_NOTIFY_CHECKED_ITEMS");
		dataManager.initEditor();
		for(int i = 0; i<16; i++)
			dataManager.getEditor().putBoolean("ITEM_"+i, dataManager.getCheckedItems()[i]);
		//����
		dataManager.getEditor().commit();
	}
}
