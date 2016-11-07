package univ.donga.dw.job.info;

import univ.donga.dw.R;
import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ViewManager {
	private Context context;
	private ToggleButton auto_job_alarm_togglebnt;
	private TextView auto_job_alarm_select_txt;
	private WebView job_web;
	public ViewManager(Context context)
	{
		Activity activity = (Activity)context;
		this.context = context;
		auto_job_alarm_togglebnt = (ToggleButton)activity.findViewById(R.id.auto_job_alarm_togglebnt);
		auto_job_alarm_select_txt = (TextView)activity.findViewById(R.id.auto_job_alarm_select_txt);
		job_web = (WebView)activity.findViewById(R.id.job_web);
	}
	public ToggleButton getAuto_job_alarm_togglebnt() {
		return auto_job_alarm_togglebnt;
	}
	public TextView getAuto_job_alarm_select_txt() {
		return auto_job_alarm_select_txt;
	}
	public void setAuto_job_alarm_select_txt(TextView auto_job_alarm_select_txt) {
		this.auto_job_alarm_select_txt = auto_job_alarm_select_txt;
	}
	public WebView getJob_web() {
		return job_web;
	}
}
