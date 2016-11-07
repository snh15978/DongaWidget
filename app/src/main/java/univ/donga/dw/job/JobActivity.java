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
		
		//알람체크여부에 따라서 버튼을 체크해준다.
		viewManager.getAuto_job_alarm_togglebnt().setChecked(dataManager.getAuto_job_alarm());
		//알람체크가 되있을경우 체크된 과를 적어준다.
		if(dataManager.getAuto_job_alarm() == true)
			viewManager.getAuto_job_alarm_select_txt().setText(
					viewManager.getAuto_job_alarm_select_txt().getText().toString() +
					"("+ dataManager.getJobCollegeManager().getengineeringCollegeList(dataManager.getSelect_departement()).getName()+")");
		
		viewManager.getAuto_job_alarm_togglebnt().setOnClickListener(new AutoJobAlarmToggleBtnEvent(viewManager, dataManager, which));
		
		
		// 웹뷰에 각종 설정을 한다.
		WebSettings set = viewManager.getJob_web().getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		set.setLoadWithOverviewMode(true);
		set.setUseWideViewPort(true);
		viewManager.getJob_web().setWebViewClient(new MyWebClient(this));
		//각과 게시판을 WebView로 보여준다.
		//차후에는 직접 게시판을 파싱받아 보여준다.
		viewManager.getJob_web().loadUrl(dataManager.getJobCollegeManager().getengineeringCollegeList(which).getUrl());
	}
	
	//웹뷰 뒤로가기 처리
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK) && viewManager.getJob_web().canGoBack()) {
			viewManager.getJob_web().goBack();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}
