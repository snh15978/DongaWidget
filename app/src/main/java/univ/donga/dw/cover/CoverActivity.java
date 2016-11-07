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
        
        //������ Ű�� ���񽺰� ���۵ȴ�.
		startService(new Intent(this, CampusManagerService.class));
		
		

			Intent intent = new Intent(CoverActivity.this, MainActivity.class);
			//������ �� ���������� �Ѱ��ش�.
			intent.putExtra("CARTE", dataManager.getCarteSocketInfo());
			intent.putExtra("FREEBOARD", dataManager.getFreeBoardSocketInfo());
			intent.putExtra("TAXI", dataManager.getTaxiSocketInfo());
			startActivity(intent);
			finish();
		
		
		/*//���̾�α׸� ����.
		mProgress = ProgressDialog.show(this, "Wait", "������ ������...");
		//������ �����ϱ����� ������
		dataManager.setServerConnectThread(new ServerConnectThread(this, dataManager, connectHandler));
		dataManager.getServerConnectThread().start();*/
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
	{
    	//�ڷΰ���Ű ��ȿȭ
		return true;
	}
    // �������� ����� �޾Ƽ� ����� ����ϴ� �ڵ鷯
 	Handler connectHandler = new Handler() {
 		public void handleMessage(Message msg) {
 			//���α׷����� �ݾ��ش�.
 			mProgress.dismiss();	
 			//���������μ����� ����Ǹ�
 			if (msg.what == 0) {
 				Intent intent = new Intent(CoverActivity.this, MainActivity.class);
 				//������ �� ���������� �Ѱ��ش�.
 				intent.putExtra("CARTE", dataManager.getCarteSocketInfo());
 				intent.putExtra("FREEBOARD", dataManager.getFreeBoardSocketInfo());
 				intent.putExtra("TAXI", dataManager.getTaxiSocketInfo());
 				startActivity(intent);
 				finish();
 				//�ִϸ��̼�ȿ��
 				overridePendingTransition(R.anim.cover_main_ani1, R.anim.cover_main_ani2);
 			}
 			else if(msg.what == 1)
 			{
 				Toast.makeText(CoverActivity.this, "���ͳ� ������ Ȯ���Ͻð� ���ø����̼��� ��������ּ���", Toast.LENGTH_SHORT).show();
 				finish();
 			}
 		}
 	};
}