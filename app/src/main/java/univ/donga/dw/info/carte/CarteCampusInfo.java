package univ.donga.dw.info.carte;

import android.util.Log;
public class CarteCampusInfo {
	//ķ�۽��� �̸� ���� �ʵ�
	private String campusName;
	//ķ�۽����� �Ĵ���ġ�� ���� �ʵ�
	private String[] location;
	//ķ�۽����� ���Ĵ��� �޴��� ���� �ʵ�
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
