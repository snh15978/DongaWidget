package univ.donga.dw.info.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TaxiSocketInfo implements Parcelable  {

	//�����Ͽ� �ڱ��ڽ������� �ʵ�
	static TaxiSocketInfo obj;
	
	private final int taxiPort = 6579; //�ý���ġ�� ���� ��Ʈ
	private BufferedOutputStream outstream;
	private BufferedInputStream instream;
	private Socket socket;
	public TaxiSocketInfo()
	{
		socket = new Socket();
		//�ڱ� �ڽ��� �ִ´� ���߿� �ѱ拚 ����.
		obj = this;
	}
	public BufferedOutputStream getOutstream() {
		return outstream;
	}
	public void setOutstream(BufferedOutputStream outstream) {
		this.outstream = outstream;
	}
	public BufferedInputStream getInstream() {
		return instream;
	}
	public void setInstream(BufferedInputStream instream) {
		this.instream = instream;
	}
	public Socket getSocket() {
		return socket;
	}
	public int getTaxiPort() {
		return taxiPort;
	}
	
	// Parcelable�� ���� �޼ҵ� ������
	public int describeContents() {
	// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flag) {
		// TODO Auto-generated method stub

	}

	public static final Parcelable.Creator<TaxiSocketInfo> CREATOR = new Creator<TaxiSocketInfo>() {
		public TaxiSocketInfo createFromParcel(Parcel source) {
			return obj;
		}

		@Override
		public TaxiSocketInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new TaxiSocketInfo[size];
		}
	};
}
