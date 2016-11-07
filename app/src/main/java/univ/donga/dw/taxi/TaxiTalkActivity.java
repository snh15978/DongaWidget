/**
 * ȭ�鿡 �޽����� ǥ���Ϸ���
 * addMessage(String msg, int side)�� ȣ��
 * String msg : ����� �޽���
 * int side : ����� ��ġ(SIDE_LEFT �Ǵ� SIDE_RIGHT)
 * 
 * 
 * 
 */
package univ.donga.dw.taxi;

import univ.donga.dw.R;
import univ.donga.dw.carte.CarteActivity;
import univ.donga.dw.carte.CarteActivityMenuAdapter;
import univ.donga.dw.carte.event.CarteActivityGridMenuEvent;
import univ.donga.dw.info.socket.TaxiSocketInfo;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class TaxiTalkActivity extends Activity implements OnClickListener{
	
	public static final int SIDE_LEFT = 0;
	public static final int SIDE_RIGHT = 1;
	public static String message;
	
	private ScrollView scrollView;
	private LinearLayout linearLayout;
	private EditText editText;
	private Button sendBtn;	
	
	private TaxiSocketInfo taxiSocketInfo;
	private RecvThread recvThread;
	
	private byte[] send_message = new byte[256];
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi);
        
        Intent intent = getIntent();
        taxiSocketInfo = (TaxiSocketInfo)intent.getParcelableExtra("TAXI");
        
        scrollView = (ScrollView)findViewById(R.id.taxiTalkScroll);
        linearLayout = (LinearLayout)findViewById(R.id.taxiTalkLayout);
        editText = (EditText)findViewById(R.id.taxiTalkeditText);
        sendBtn = (Button)findViewById(R.id.taxiTalkBtn);
        sendBtn.setOnClickListener(this);
        
        recvThread = new RecvThread(this, taxiSocketInfo, recvHandler);
        recvThread.start();
    }
    
    protected void onDestroy() {
		super.onDestroy();
		String msg;
		//������ �޼��� ����
		send_message = new byte[256];
		try {
			msg = "quit" + '\0';
			send_message = msg.getBytes();
			// ������ ������.
			taxiSocketInfo.getOutstream().write(send_message);
			// �ڹٻ󿡼� ���ۺ��� ���������� C���� ������ ���� write���� �ѵ� ���۸� ���� C���� ������ ũ�⸸ŭ��
			// �о �ݺ��Ѵ�.
			taxiSocketInfo.getOutstream().flush();
		} catch (Exception ex) {
			Log.e("error", ex.getMessage());
		}
		//���ú꽺���带 �����Ų��.
		recvThread.setEnd(true);
	}
    
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.taxiTalkBtn:
			String msg = editText.getText().toString();
			addMessage(msg, SIDE_RIGHT);
			editText.setText("");
			break;
		}
	}
	
	public void addMessage(String msg, int side){
		LinearLayout ll = new LinearLayout(this);
		ll.setLayoutParams(new LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setPadding(10, 10, 10, 10);
		
		TextView tv = new TextView(this);
		tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tv.setBackgroundResource(R.drawable.rounded_corner_yellow);
		tv.setText(getTenCharPerLineString(msg));
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
		tv.setTextColor(Color.BLACK);
		tv.setPadding(10, 10, 10, 10);
		
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f);
		LinearLayout ll2 = new LinearLayout(this);		
		ll2.setLayoutParams(param);
		ll2.setOrientation(LinearLayout.HORIZONTAL);
		
				
		switch (side) {
		case SIDE_LEFT:
			ll.addView(tv);
			linearLayout.addView(ll);
			break;
		case SIDE_RIGHT:
			ll.addView(ll2);
			ll.addView(tv);
			linearLayout.addView(ll);
			break;
		}
		
		scrollView.post(new Runnable() {			
			public void run() {
				// TODO Auto-generated method stub
				scrollView.fullScroll(View.FOCUS_DOWN);
			}
		});
		
		//������ �޼��� ����
		send_message = new byte[256];
		try {
			msg = msg.trim()+'\0';
			send_message = msg.getBytes();
			// ������ ������.
			taxiSocketInfo.getOutstream().write(send_message);
			//�ڹٻ󿡼� ���ۺ��� ���������� C���� ������ ���� write���� �ѵ� ���۸� ���� C���� ������ ũ�⸸ŭ�� �о �ݺ��Ѵ�.
			taxiSocketInfo.getOutstream().flush();
		} catch (Exception ex) {
			Log.e("error", ex.getMessage());
		}
	}	
	
	public String getTenCharPerLineString(String text){

	    String tenCharPerLineString = "";
	    while (text.length() > 12) {

	        String buffer = text.substring(0, 12);
	        tenCharPerLineString = tenCharPerLineString + buffer + "\n";
	        text = text.substring(12);
	    }

	    tenCharPerLineString = tenCharPerLineString + text.substring(0);
	    return tenCharPerLineString;
	}
	
	 //�������� ����� �޾Ƽ� ����� ����ϴ� �ڵ鷯
	Handler recvHandler = new Handler() {
		public void handleMessage(Message msg) {
			addMessage(message, SIDE_LEFT);
		}
	};
	
}