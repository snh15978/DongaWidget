package univ.donga.dw.taxi;

import univ.donga.dw.info.socket.TaxiSocketInfo;
import android.os.Handler;
import android.util.Log;

public class RecvThread extends Thread{
	private boolean end;
	private TaxiTalkActivity taxiTalkActivity;
	private TaxiSocketInfo taxiSocketInfo;
	private Handler recvHandler;
	private byte[] receive_message = new byte[256]; 
	
	public RecvThread(TaxiTalkActivity taxiTalkActivity, TaxiSocketInfo taxiSocketInfo, Handler recvHandler) {
		// TODO 자동 생성된 생성자 스텁
		this.taxiTalkActivity = taxiTalkActivity;
		end = false;
		this.taxiSocketInfo = taxiSocketInfo;
		this.recvHandler = recvHandler;
	}

	public void run()
	{
		try {
			while (!end) {
				taxiSocketInfo.getInstream().read(receive_message);// 서버로 부터 받고
				TaxiTalkActivity.message = new String(receive_message);
				Log.e("error", TaxiTalkActivity.message);
				receive_message = new byte[256];
				recvHandler.sendEmptyMessage(0);
			}
		} catch (Exception ex) {
			Log.e("error", ex.getMessage());
		}
	}
	public void setEnd(boolean end)
	{
		this.end = end;
	}

}
