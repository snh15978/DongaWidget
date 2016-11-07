package univ.donga.dw.job.info;

import univ.donga.dw.info.job.CollegeManager;
import univ.donga.dw.service.CampusManagerService;
import android.app.Activity;
import android.content.Context;

public class DataManager {

	private Context context;
	
	public DataManager(Context context)
	{
		Activity activity = (Activity)context;
		this.context = context;
	}
	public boolean getAuto_job_alarm()
	{
		return CampusManagerService.auto_job_alarm;
	}
	public int getSelect_departement()
	{
		return CampusManagerService.select_departement;
	}
	public void setSelect_departement(int index)
	{
		CampusManagerService.select_departement = index;
	}
	public void setAuto_job_alarm(boolean auto_job_alarm)
	{
		CampusManagerService.auto_job_alarm = auto_job_alarm;
	}
	public CollegeManager getJobCollegeManager()
	{
		return CampusManagerService.jobCollegeManager;
	}
}
