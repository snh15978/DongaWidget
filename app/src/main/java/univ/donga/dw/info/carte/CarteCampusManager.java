package univ.donga.dw.info.carte;

import java.util.ArrayList;

import univ.donga.dw.info.book.BookListManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CarteCampusManager{
	//�Ĵ�ǥ�� ������ ����Ʈ
	private ArrayList<CarteCampusInfo> carteCampusInfo;
	public CarteCampusManager()
	{
		carteCampusInfo = new ArrayList<CarteCampusInfo>();
		carteCampusInfo.add(new CarteCampusInfo("����ķ�۽�"));
		carteCampusInfo.add(new CarteCampusInfo("����/�ι�ķ�۽�"));
		
	}
	public CarteCampusInfo getCarteCampusInfo(int i)
	{
		return carteCampusInfo.get(i);
	}
	public void removeCarteCampusInfo(int i)
	{
		carteCampusInfo.remove(i);
	}
	public void addCarteCampusInfo(CarteCampusInfo carteCampusInfo)
	{
		this.carteCampusInfo.add(carteCampusInfo);
	}
	public void clearCarteCampusInfo()
	{
		carteCampusInfo.clear();
	}
}
