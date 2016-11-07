package univ.donga.dw.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import univ.donga.dw.info.carte.CarteCampusInfo;
import univ.donga.dw.info.seat.SeatCampusInfo;
import univ.donga.dw.info.seat.SeatCampusManager;
import android.os.Handler;
import android.util.Log;


public class SeatDownThread extends Thread {
	private String url;
	private Source source;
    private Handler mAfterDown;
    private String imsi = "";
	private SeatCampusManager seatCampusManager;
	private int cnt = 0;
	private int year, month, day;
	public SeatDownThread(Handler mAfterDown, SeatCampusManager seatCampusManager)
	{
		this.mAfterDown = mAfterDown;
		this.seatCampusManager = seatCampusManager;
		//초기화
		seatCampusManager.clearSeatCampusInfo();
		// 현제 날짜를 가지고 오기 위한 클래스
		Calendar ca = Calendar.getInstance();
		year = ca.get(Calendar.YEAR);// 현제 년 가지고오기
		month = ca.get(Calendar.MONTH) + 1; // 현제 월 가져오기
		day = ca.get(Calendar.DAY_OF_MONTH); // 현재 일 가져오기
		// 파싱하고자 하는 URL을 기록한다.
		url = "http://168.115.76.58/seat/domian5.asp";
		
	}
	public void run()
	{
		openCampus(url);
		//다운로드가 다 되었음을 메인액티비티에 알려준다.
		if(mAfterDown != null)
			mAfterDown.sendEmptyMessage(0);
	}
	public void openCampus(String url)
	{
		try {
			URL nURL;
			nURL = new URL(url);
			InputStream html = nURL.openStream();
			source = new Source(new InputStreamReader(html, "EUC-KR"));
			Element table = null;
			Element tr = null;
			Element td = null;
			SeatCampusInfo seatCampus;
			
			//2번쨰 테이블을 가지고와서
			table = (Element) source.getAllElements(HTMLElementName.TABLE).get(1);
			//다운받은 시간을 가지고 온다.
			tr = (Element) table.getAllElements(HTMLElementName.TR).get(0);
			td = (Element) tr.getAllElements(HTMLElementName.TD).get(0);
			seatCampusManager.setDownTime(extractTime(tr));
			
			//3번째 TR부터시작하여 가지고온다.
			for (int i = 2; i < 2 + 16; i++) {
				seatCampus = new SeatCampusInfo();
				//3번째 TR부터시작하여 가지고온다.
				tr = (Element) table.getAllElements(HTMLElementName.TR).get(i);
				//1~5번째 TD에서 정보를 빼서 저장한다.(열람실명, 전체좌석수, 사용좌석수, 잔여좌석수, 이용률)
				td = (Element) tr.getAllElements(HTMLElementName.TD).get(0);
				seatCampus.setName(extractName(td));
				td = (Element) tr.getAllElements(HTMLElementName.TD).get(1);
				seatCampus.setTotalSeat(extractTotalSeat(td));
				td = (Element) tr.getAllElements(HTMLElementName.TD).get(2);
				seatCampus.setUseSeat(extractUseSeat(td));
				td = (Element) tr.getAllElements(HTMLElementName.TD).get(3);
				seatCampus.setRemainSeat(extractRemainSeat(td));
				td = (Element) tr.getAllElements(HTMLElementName.TD).get(4);
				seatCampus.setUseRate(extractUseRate(td));
				seatCampusManager.addSeatCampusInfo(seatCampus);
			}
		}
		catch (MalformedURLException e) {
			Log.e("error", e.getMessage());
		} catch (IOException e) {
			Log.e("error", e.getMessage());
		}
	}
	public String extractTime(Element td)
	{
		String str = td.getAllElements(HTMLElementName.FONT).get(0).getContent().toString().trim();
		str = str.replace("&nbsp;", "");
		return str;
	}
	public String extractName(Element td)
	{
		String str = td.getAllElements(HTMLElementName.FONT).get(0).getAllElements(HTMLElementName.A).get(0).getContent().toString().trim();
		str = str.replace("&nbsp;", "");
		return str;
	}
	public String extractTotalSeat(Element td)
	{
		String str = td.getAllElements(HTMLElementName.FONT).get(0).getContent().toString().trim();
		str = str.replace("&nbsp;", "");
		return str;
	}
	public String extractUseSeat(Element td)
	{
		String str = td.getAllElements(HTMLElementName.FONT).get(0).getContent().toString().trim();
		str = str.replace("&nbsp;", "");
		return str;
	}
	public String extractRemainSeat(Element td)
	{
		String str = td.getAllElements(HTMLElementName.FONT).get(0).getContent().toString().trim();
		str = str.replace("&nbsp;", "");
		return str;
	}
	public String extractUseRate(Element td)
	{
		String str = td.getAllElements(HTMLElementName.FONT).get(0).getContent().toString().trim();
		str = str.replace("&nbsp;", "");
		return str;
	}
}
