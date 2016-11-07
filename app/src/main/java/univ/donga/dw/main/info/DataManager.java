package univ.donga.dw.main.info;

import java.util.ArrayList;

import univ.donga.dw.info.job.CollegeManager;
import univ.donga.dw.info.socket.CarteSocketInfo;
import univ.donga.dw.info.socket.FreeBoardSocketInfo;
import univ.donga.dw.info.socket.TaxiSocketInfo;
import univ.donga.dw.main.MainListItem;
import univ.donga.dw.service.CampusManagerService;
import android.content.Context;

public class DataManager {
	private ArrayList<MainListItem> listArr;
	public final static int LOGIN_RESULT = 0;
	
	private CarteSocketInfo carteSocketInfo;
	private FreeBoardSocketInfo freeBoardSocketInfo;
	private TaxiSocketInfo taxiSocketInfo;
	
	public DataManager(Context context)
	{
		listArr = new ArrayList<MainListItem>();
	}
	public ArrayList<MainListItem> getListArr()
	{
		return listArr;
	}
	public Boolean isAuto_login() {
		return CampusManagerService.auto_login;
	}
	public void setAuto_login(boolean auto_login) {
		CampusManagerService.auto_login = auto_login;
	}
	public CarteSocketInfo getCarteSocketInfo() {
		return carteSocketInfo;
	}
	public void setCarteSocketInfo(CarteSocketInfo carteSocketInfo) {
		this.carteSocketInfo = carteSocketInfo;
	}
	public FreeBoardSocketInfo getFreeBoardSocketInfo() {
		return freeBoardSocketInfo;
	}
	public void setFreeBoardSocketInfo(FreeBoardSocketInfo freeBoardSocketInfo) {
		this.freeBoardSocketInfo = freeBoardSocketInfo;
	}
	public TaxiSocketInfo getTaxiSocketInfo() {
		return taxiSocketInfo;
	}
	public void setTaxiSocketInfo(TaxiSocketInfo taxiSocketInfo) {
		this.taxiSocketInfo = taxiSocketInfo;
	}
	public CollegeManager getJobCollegeManager()
	{
		return CampusManagerService.jobCollegeManager;
	}
	
}
