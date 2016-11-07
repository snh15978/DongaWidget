package univ.donga.dw.cover;

import univ.donga.dw.R;
import univ.donga.dw.cover.info.DataManager;
import univ.donga.dw.main.MainActivity;
import univ.donga.dw.service.CampusManagerService;
import univ.donga.dw.thread.ServerConnectThread;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

public class CoverActivity extends Activity {
	private DataManager dataManager;
    public static ProgressDialog mProgress = null;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
        dataManager = new DataManager(this);
        
        //어플을 키면 서비스가 시작된다.
		startService(new Intent(this, CampusManagerService.class));
		
		

			Intent intent = new Intent(CoverActivity.this, MainActivity.class);
			//세가지 의 소켓정보를 넘겨준다.
			intent.putExtra("CARTE", dataManager.getCarteSocketInfo());
			intent.putExtra("FREEBOARD", dataManager.getFreeBoardSocketInfo());
			intent.putExtra("TAXI", dataManager.getTaxiSocketInfo());
			startActivity(intent);
			finish();
		
		
		/*//다이얼로그를 띄운다.
		mProgress = ProgressDialog.show(this, "Wait", "서버에 연결중...");
		//서버에 연결하기위한 스레드
		dataManager.setServerConnectThread(new ServerConnectThread(this, dataManager, connectHandler));
		dataManager.getServerConnectThread().start();*/
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
	{
    	//뒤로가기키 무효화
		return true;
	}
    // 쓰레드의 결과를 받아서 출력을 담당하는 핸들러
 	Handler connectHandler = new Handler() {
 		public void handleMessage(Message msg) {
 			//프로그래스를 닫아준다.
 			mProgress.dismiss();	
 			//정상적으로소켓이 연결되면
 			if (msg.what == 0) {
 				Intent intent = new Intent(CoverActivity.this, MainActivity.class);
 				//세가지 의 소켓정보를 넘겨준다.
 				intent.putExtra("CARTE", dataManager.getCarteSocketInfo());
 				intent.putExtra("FREEBOARD", dataManager.getFreeBoardSocketInfo());
 				intent.putExtra("TAXI", dataManager.getTaxiSocketInfo());
 				startActivity(intent);
 				finish();
 				//애니매이션효과
 				overridePendingTransition(R.anim.cover_main_ani1, R.anim.cover_main_ani2);
 			}
 			else if(msg.what == 1)
 			{
 				Toast.makeText(CoverActivity.this, "인터넷 연결을 확인하시고 어플리케이션을 재시작해주세요", Toast.LENGTH_SHORT).show();
 				finish();
 			}
 		}
 	};
}