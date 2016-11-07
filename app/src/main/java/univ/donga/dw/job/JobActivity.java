package univ.donga.dw.job;

import univ.donga.dw.R;
import univ.donga.dw.job.event.AutoJobAlarmToggleBtnEvent;
import univ.donga.dw.job.info.DataManager;
import univ.donga.dw.job.info.MyWebClient;
import univ.donga.dw.job.info.ViewManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;

public class JobActivity extends Activity {

	private ViewManager viewManager;
	private DataManager dataManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_job);
		
		Intent intent = getIntent();
		int which = intent.getIntExtra("WHICH", -1);
		
		viewManager = new ViewManager(this);
		dataManager = new DataManager(this);
		
		//�˶�üũ���ο� ���� ��ư�� üũ���ش�.
		viewManager.getAuto_job_alarm_togglebnt().setChecked(dataManager.getAuto_job_alarm());
		//�˶�üũ�� ��������� üũ�� ���� �����ش�.
		if(dataManager.getAuto_job_alarm() == true)
			viewManager.getAuto_job_alarm_select_txt().setText(
					viewManager.getAuto_job_alarm_select_txt().getText().toString() +
					"("+ dataManager.getJobCollegeManager().getengineeringCollegeList(dataManager.getSelect_departement()).getName()+")");
		
		viewManager.getAuto_job_alarm_togglebnt().setOnClickListener(new AutoJobAlarmToggleBtnEvent(viewManager, dataManager, which));
		
		
		// ���信 ���� ������ �Ѵ�.
		WebSettings set = viewManager.getJob_web().getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		set.setLoadWithOverviewMode(true);
		set.setUseWideViewPort(true);
		viewManager.getJob_web().setWebViewClient(new MyWebClient(this));
		//���� �Խ����� WebView�� �����ش�.
		//���Ŀ��� ���� �Խ����� �Ľ̹޾� �����ش�.
		viewManager.getJob_web().loadUrl(dataManager.getJobCollegeManager().getengineeringCollegeList(which).getUrl());
	}
	
	//���� �ڷΰ��� ó��
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK) && viewManager.getJob_web().canGoBack()) {
			viewManager.getJob_web().goBack();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}
