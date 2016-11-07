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
		Toast.makeText(context, "통지를 하지 않도록 설정하였습니다.", Toast.LENGTH_SHORT).show();
		//통지를 하지 않기 선택을 할경우 초기화시켜준다.
		for(int i = 0; i<dataManager.getSeatCampusManager().sizeOfseatCampusInfo(); i++)
			dataManager.getCheckedItems()[i] = false;
		//선택된 아이템을 저장할 파일을열고 키워드를 이용하여 저장해준다.
		dataManager.initPrefs("SEAT_NOTIFY_CHECKED_ITEMS");
		dataManager.initEditor();
		for(int i = 0; i<16; i++)
			dataManager.getEditor().putBoolean("ITEM_"+i, dataManager.getCheckedItems()[i]);
		//적용
		dataManager.getEditor().commit();
	}
}
