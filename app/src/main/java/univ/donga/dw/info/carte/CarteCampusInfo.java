package univ.donga.dw.info.carte;

import android.util.Log;
public class CarteCampusInfo {
	//캠퍼스의 이름 넣을 필드
	private String campusName;
	//캠퍼스내의 식당위치를 넣을 필드
	private String[] location;
	//캠퍼스내의 각식당의 메뉴를 넣을 필드
	private String[] menu;
	public CarteCampusInfo(String campusName)
	{
		this.campusName = campusName;
		location = new String[6];
		menu = new String[6];
	}
	public String getCampusName() {
		return campusName;
	}
	public String getLocation(int i) {
		return location[i];
	}
	public String[] getLocations() {
		return location;
	}
	public void setLocation(String location, int i) {
		this.location[i] = location;
	}
	public String getMenu(int i) {
		return menu[i];
	}
	public String[] getMenus() {
		return menu;
	}
	public void setMenu(String menu, int i) {
		this.menu[i] = menu;
	}
}
