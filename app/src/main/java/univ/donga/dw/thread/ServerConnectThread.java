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
			//3���� ��Ʈ������ �������Ѵ�.
			InetSocketAddress carteSocketAddress = new InetSocketAddress(dataManager.getServerIP(), dataManager.getCarteSocketInfo().getCartePort());
			InetSocketAddress freeBoardSocketAddress = new InetSocketAddress(dataManager.getServerIP(), dataManager.getFreeBoardSocketInfo().getFreeBoardPort());
			InetSocketAddress taxiSocketAddress = new InetSocketAddress(dataManager.getServerIP(), dataManager.getTaxiSocketInfo().getTaxiPort());
			
			//3�ʰ� ��������� ����
			dataManager.getCarteSocketInfo().getSocket().connect(carteSocketAddress, 3000);
			dataManager.getFreeBoardSocketInfo().getSocket().connect(freeBoardSocketAddress, 3000);
			dataManager.getTaxiSocketInfo().getSocket().connect(taxiSocketAddress, 3000);
			
			//�� ��Ʈ�� ���� ��ǲ��Ʈ���� �ƿ�ǲ ��Ʈ���� �����ش�.
			if (dataManager.getCarteSocketInfo().getSocket().isConnected() == true)// ������ ���������� ����Ǹ�
			{
				// �Է����� ��Ʈ��
				dataManager.getCarteSocketInfo().setInstream(
						new BufferedInputStream(
								dataManager.getCarteSocketInfo().getSocket().getInputStream()));
				// �������� ��Ʈ��
				dataManager.getCarteSocketInfo().setOutstream(
						new BufferedOutputStream(
								dataManager.getCarteSocketInfo().getSocket().getOutputStream()));
			}
			if (dataManager.getFreeBoardSocketInfo().getSocket().isConnected() == true)// ������ ���������� ����Ǹ�
			{
				// �Է����� ��Ʈ��
				dataManager.getFreeBoardSocketInfo().setInstream(
						new BufferedInputStream(
								dataManager.getFreeBoardSocketInfo().getSocket().getInputStream()));
				// �������� ��Ʈ��
				dataManager.getFreeBoardSocketInfo().setOutstream(
						new BufferedOutputStream(
								dataManager.getFreeBoardSocketInfo().getSocket().getOutputStream()));
			}
			if (dataManager.getTaxiSocketInfo().getSocket().isConnected() == true)// ������ ���������� ����Ǹ�
			{
				// �Է����� ��Ʈ��
				dataManager.getTaxiSocketInfo().setInstream(
						new BufferedInputStream(
								dataManager.getTaxiSocketInfo().getSocket().getInputStream()));
				// �������� ��Ʈ��
				dataManager.getTaxiSocketInfo().setOutstream(
						new BufferedOutputStream(
								dataManager.getTaxiSocketInfo().getSocket().getOutputStream()));
			}
			
			//������ ���������� ���� �Ǿ����� ���ξ�Ƽ��Ƽ�� �˷��ش�.
			Message msg = Message.obtain(connectHandler, 0, 0, 0);
			connectHandler.sendMessage(msg);
		} catch (Exception e) //���Ͽ��ῡ �����ϸ�
		{
			Log.e("error", e.getMessage());
			Message msg = Message.obtain(connectHandler, 1, 0, 0);
			connectHandler.sendMessage(msg);
		}
	}
}
