package univ.donga.dw.widget.info;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import univ.donga.dw.carte_receiver.WidgetCarteReceiverTwoFour;

public class DataManager {
	//�����۷������� ����ϱ����� ����
	private String CARTE;
	//ķ�۽��̸��� ���� �ʵ�
	private String[] campusName = {"���� ķ�۽�", "���� ķ�۽� / �ι� ķ�۽�"};
	//ķ�۽����� �Ĵ���ġ�� ���� �ʵ�
	private String[][] location = {{"����ȸ��", "�л�ȸ��", "�� ��", "������", "�Ѹ���Ȱ��"}, {"�л�ȸ��", "������", "������", "�ι�ķ�۽�", "�ι�(������)", "���ǵ��л�ȸ��"}};
	private boolean[][] checkBox = new boolean[2][6];
	//���÷����͸� ���� �ʵ�
	private LayoutInflater mInflater;
	//������ ID����
	private int mId;
	public DataManager(Context context, String CARTE)
	{
		this.CARTE = CARTE;
		// ���÷����͸� ���������
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//ID�� �����صд�.
		Intent intent = ((Activity)context).getIntent();
		mId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		
		//üũ�ڽ��ʱ�ȭ
		for(int i = 0; i<2; i++)
			for(int j = 0; j<6; j++)
				checkBox[i][j] = false;
	}
	public LayoutInflater getmInflater() {
		return mInflater;
	}
	public int getmId() {
		return mId;
	}
	public String getCARTE() {
		return CARTE;
	}
	public String[] getCampusName() {
		return campusName;
	}
	public String[][] getLocation() {
		return location;
	}
	public boolean[][] getCheckBox() {
		return checkBox;
	}
	
	
}
