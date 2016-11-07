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
		//������ �����Ǹ� ����� �а��� ������Ų��.
		if(viewManager.getAuto_job_alarm_togglebnt().isChecked() == false)
		{
			dataManager.setSelect_departement(-1);
			viewManager.getAuto_job_alarm_select_txt().setText("�ֱ������� �Խ����� üũ�Ͽ� �ֽ����� �˸��� ���ݴϴ�.");
		}
		//���õȰ�� �а��� �����Ѵ�.
		else
		{
			dataManager.setSelect_departement(which);
			viewManager.getAuto_job_alarm_select_txt().setText(
					viewManager.getAuto_job_alarm_select_txt().getText().toString() +"("+ dataManager.getJobCollegeManager().getengineeringCollegeList(which).getName()+")");
		}
	}
}
