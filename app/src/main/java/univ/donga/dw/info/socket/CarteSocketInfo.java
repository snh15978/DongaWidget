package univ.donga.dw.info.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

import univ.donga.dw.info.book.BookListManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CarteSocketInfo implements Parcelable {

	//생성하여 자기자신을넣을 필드
	static CarteSocketInfo obj;
	private final int cartePort = 6577; //식단표 별점을 위한 포트
	private BufferedOutputStream outstream;
	private BufferedInputStream instream;
	private Socket socket;
	
	public CarteSocketInfo()
	{
		socket = new Socket();
		//자기 자신을 넣는다 나중에 넘길떄 쓴다.
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
	public int getCartePort() {
		return cartePort;
	}
	
	// Parcelable를 위한 메소드 재정의
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flag) {
		// TODO Auto-generated method stub

	}

	public static final Parcelable.Creator<CarteSocketInfo> CREATOR = new Creator<CarteSocketInfo>() {
		public CarteSocketInfo createFromParcel(Parcel source) {
			return obj;
		}

		@Override
		public CarteSocketInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new CarteSocketInfo[size];
		}
	};
}
