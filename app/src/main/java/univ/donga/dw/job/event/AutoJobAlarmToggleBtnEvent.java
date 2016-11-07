package univ.donga.dw.job.event;

import univ.donga.dw.job.info.DataManager;
import univ.donga.dw.job.info.ViewManager;
import android.view.View;
import android.widget.ToggleButton;
public class AutoJobAlarmToggleBtnEvent implements ToggleButton.OnClickListener{

	private ViewManager viewManager;
	private DataManager dataManager;
	private int which;
	
	public AutoJobAlarmToggleBtnEvent(ViewManager viewManager, DataManager dataManager, int which)
	{
		this.viewManager = viewManager;
		this.dataManager = dataManager;
		this.which = which;
	}
	public void onClick(View v) {
		dataManager.setAuto_job_alarm(viewManager.getAuto_job_alarm_togglebnt().isChecked());
		//선택이 해제되면 저장된 학과를 삭제시킨다.
		if(viewManager.getAuto_job_alarm_togglebnt().isChecked() == false)
		{
			dataManager.setSelect_departement(-1);
			viewManager.getAuto_job_alarm_select_txt().setText("주기적으로 게시판을 체크하여 최신정보 알림을 해줍니다.");
		}
		//선택된경우 학과를 저장한다.
		else
		{
			dataManager.setSelect_departement(which);
			viewManager.getAuto_job_alarm_select_txt().setText(
					viewManager.getAuto_job_alarm_select_txt().getText().toString() +"("+ dataManager.getJobCollegeManager().getengineeringCollegeList(which).getName()+")");
		}
	}
}
