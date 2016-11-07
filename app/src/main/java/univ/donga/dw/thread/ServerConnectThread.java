package univ.donga.dw.thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.InetSocketAddress;

import univ.donga.dw.cover.info.DataManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ServerConnectThread extends Thread {
	private Context context;
	private DataManager dataManager;
	private Handler connectHandler;
	public ServerConnectThread(Context context, DataManager dataManager, Handler connectHandler)
	{
		this.context = context;
		this.dataManager = dataManager;
		this.connectHandler = connectHandler;
	}
	public void run()
	{
		try
		{
			//3개의 포트에대해 오픈을한다.
			InetSocketAddress carteSocketAddress = new InetSocketAddress(dataManager.getServerIP(), dataManager.getCarteSocketInfo().getCartePort());
			InetSocketAddress freeBoardSocketAddress = new InetSocketAddress(dataManager.getServerIP(), dataManager.getFreeBoardSocketInfo().getFreeBoardPort());
			InetSocketAddress taxiSocketAddress = new InetSocketAddress(dataManager.getServerIP(), dataManager.getTaxiSocketInfo().getTaxiPort());
			
			//3초간 응답없으면 실패
			dataManager.getCarteSocketInfo().getSocket().connect(carteSocketAddress, 3000);
			dataManager.getFreeBoardSocketInfo().getSocket().connect(freeBoardSocketAddress, 3000);
			dataManager.getTaxiSocketInfo().getSocket().connect(taxiSocketAddress, 3000);
			
			//각 포트에 대해 인풋스트림과 아웃풋 스트림을 열어준다.
			if (dataManager.getCarteSocketInfo().getSocket().isConnected() == true)// 서버와 성공적으로 연결되면
			{
				// 입력위한 스트림
				dataManager.getCarteSocketInfo().setInstream(
						new BufferedInputStream(
								dataManager.getCarteSocketInfo().getSocket().getInputStream()));
				// 쓰기위한 스트림
				dataManager.getCarteSocketInfo().setOutstream(
						new BufferedOutputStream(
								dataManager.getCarteSocketInfo().getSocket().getOutputStream()));
			}
			if (dataManager.getFreeBoardSocketInfo().getSocket().isConnected() == true)// 서버와 성공적으로 연결되면
			{
				// 입력위한 스트림
				dataManager.getFreeBoardSocketInfo().setInstream(
						new BufferedInputStream(
								dataManager.getFreeBoardSocketInfo().getSocket().getInputStream()));
				// 쓰기위한 스트림
				dataManager.getFreeBoardSocketInfo().setOutstream(
						new BufferedOutputStream(
								dataManager.getFreeBoardSocketInfo().getSocket().getOutputStream()));
			}
			if (dataManager.getTaxiSocketInfo().getSocket().isConnected() == true)// 서버와 성공적으로 연결되면
			{
				// 입력위한 스트림
				dataManager.getTaxiSocketInfo().setInstream(
						new BufferedInputStream(
								dataManager.getTaxiSocketInfo().getSocket().getInputStream()));
				// 쓰기위한 스트림
				dataManager.getTaxiSocketInfo().setOutstream(
						new BufferedOutputStream(
								dataManager.getTaxiSocketInfo().getSocket().getOutputStream()));
			}
			
			//소켓이 성공적으로 연결 되었으면 메인액티비티에 알려준다.
			Message msg = Message.obtain(connectHandler, 0, 0, 0);
			connectHandler.sendMessage(msg);
		} catch (Exception e) //소켓연결에 실패하면
		{
			Log.e("error", e.getMessage());
			Message msg = Message.obtain(connectHandler, 1, 0, 0);
			connectHandler.sendMessage(msg);
		}
	}
}
