package univ.donga.dw.cover.info;

import univ.donga.dw.info.socket.CarteSocketInfo;
import univ.donga.dw.info.socket.FreeBoardSocketInfo;
import univ.donga.dw.info.socket.TaxiSocketInfo;
import univ.donga.dw.thread.ServerConnectThread;
import android.content.Context;

public class DataManager {
	private Context context; 
	//IP주소
	private final String serverIP;
	private CarteSocketInfo carteSocketInfo;
	private FreeBoardSocketInfo freeBoardSocketInfo;
	private TaxiSocketInfo taxiSocketInfo;
	private ServerConnectThread serverConnectThread;
	public DataManager(Context contnext)
	{
		this.context = context;
		serverIP = "192.168.1.10";
		//3개에 소켓에 대한 정보를 가지고 있다.
		carteSocketInfo = new CarteSocketInfo();
		freeBoardSocketInfo = new FreeBoardSocketInfo();
		taxiSocketInfo = new TaxiSocketInfo();
	}
	public String getServerIP() {
		return serverIP;
	}

	public CarteSocketInfo getCarteSocketInfo() {
		return carteSocketInfo;
	}

	public FreeBoardSocketInfo getFreeBoardSocketInfo() {
		return freeBoardSocketInfo;
	}

	public TaxiSocketInfo getTaxiSocketInfo() {
		return taxiSocketInfo;
	}
	public ServerConnectThread getServerConnectThread() {
		return serverConnectThread;
	}
	public void setServerConnectThread(ServerConnectThread serverConnectThread) {
		this.serverConnectThread = serverConnectThread;
	}
	
}
