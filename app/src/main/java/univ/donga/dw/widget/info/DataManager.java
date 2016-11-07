package univ.donga.dw.widget.info;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import univ.donga.dw.carte_receiver.WidgetCarteReceiverTwoFour;

public class DataManager {
	//프레퍼런스에서 사용하기위한 변수
	private String CARTE;
	//캠퍼스이름을 넣을 필드
	private String[] campusName = {"승학 캠퍼스", "구덕 캠퍼스 / 부민 캠퍼스"};
	//캠퍼스내의 식당위치를 넣을 필드
	private String[][] location = {{"교수회관", "학생회관", "공 대", "도서관", "한림생활관"}, {"학생회관", "교직원", "예술대", "부민캠퍼스", "부민(교직원)", "강의동학생회관"}};
	private boolean[][] checkBox = new boolean[2][6];
	//인플레이터를 위한 필드
	private LayoutInflater mInflater;
	//위젯의 ID저장
	private int mId;
	public DataManager(Context context, String CARTE)
	{
		this.CARTE = CARTE;
		// 인플래이터를 가지고오고
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//ID를 조사해둔다.
		Intent intent = ((Activity)context).getIntent();
		mId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		
		//체크박스초기화
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
