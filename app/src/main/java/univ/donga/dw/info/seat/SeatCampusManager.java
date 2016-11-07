package univ.donga.dw.info.seat;

import java.util.ArrayList;
import android.os.Parcel;
import android.os.Parcelable;

public class SeatCampusManager{
	//좌석정보를 저장하고있는 리스트
	String downTime;
	ArrayList<SeatCampusInfo> seatCampusInfo;
	public SeatCampusManager()
	{
		seatCampusInfo = new ArrayList<SeatCampusInfo>();
	}
	public String getDownTime() {
		return downTime;
	}
	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}
	public SeatCampusInfo getSeatCampusInfo(int i)
	{
		return seatCampusInfo.get(i);
	}
	public void addSeatCampusInfo(SeatCampusInfo seatCampusInfo)
	{
		this.seatCampusInfo.add(seatCampusInfo);
	}
	public void removeSeatCampusInfo(int i)
	{
		seatCampusInfo.remove(i);
	}
	public void clearSeatCampusInfo()
	{
		seatCampusInfo.clear();
	}
	public int sizeOfseatCampusInfo()
	{
		return seatCampusInfo.size();
	}
}
