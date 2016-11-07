package univ.donga.dw.carte.info;

import univ.donga.dw.info.carte.CarteCampusManager;
import univ.donga.dw.service.CampusManagerService;
import univ.donga.dw.thread.CarteDownThread;
import android.content.Context;

public class DataManager {

	private CarteDownThread mThread;
	//캠퍼스마다 식당정보를 저장할 클래스
	private CarteCampusManager carteCampusManager;
	public DataManager(Context context)
	{
		carteCampusManager = CampusManagerService.carteCampusManager;
	}
	public CarteDownThread getmThread() {
		return mThread;
	}
	public void setmThread(CarteDownThread mThread) {
		this.mThread = mThread;
	}
	public CarteCampusManager getCarteCampusManager() {
		return carteCampusManager;
	}
}
