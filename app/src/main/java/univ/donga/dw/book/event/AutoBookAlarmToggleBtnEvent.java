package univ.donga.dw.book.event;

import univ.donga.dw.book.info.DataManager;
import univ.donga.dw.book.info.ViewManager;
import android.view.View;
import android.widget.ToggleButton;
public class AutoBookAlarmToggleBtnEvent implements ToggleButton.OnClickListener{

	private ViewManager viewManager;
	private DataManager dataManager;
	
	public AutoBookAlarmToggleBtnEvent(ViewManager viewManager, DataManager dataManager)
	{
		this.viewManager = viewManager;
		this.dataManager = dataManager;
	}
	public void onClick(View v) {
		dataManager.setAuto_book_alarm(viewManager.getAuto_book_alarm_togglebnt().isChecked());
	}
}
