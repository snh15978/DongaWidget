package univ.donga.dw.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import univ.donga.dw.market.BoardItemInfo;
import android.os.Handler;
import android.util.Log;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class MarketDownThread extends Thread {
	private int pageNum;
	private String url;
	private Source source;
	private Handler mAfterDown;
	
	public MarketDownThread(int pageNum) {
		url="http://www.donga.ac.kr/WebApp/BOARD/BASIC/List.asp?PG=" + pageNum + "&BIDX=25&CAT=&ORD=&KEY=&KWD=";
	}
	public void run()
	{
		openMarket(url);
		//�ٿ�ε尡 �� �Ǿ����� ���ξ�Ƽ��Ƽ�� �˷��ش�.
		if(mAfterDown != null)
			mAfterDown.sendEmptyMessage(0);
	}
	public void openMarket(String url) {
		try {
			URL nURL;
			nURL = new URL(url);
			InputStream html = nURL.openStream();
			source = new Source(new InputStreamReader(html, "EUC-KR"));
			Element table = null;
			Element tr = null;
			Element td = null;
			Element font = null;
			Element a = null;
			table = (Element) source.getAllElements(HTMLElementName.TABLE).get(1);
			
			for(int i = 0; i<10; i++)
			{
				BoardItemInfo s = new BoardItemInfo();
				tr = table.getAllElements(HTMLElementName.TR).get(2+i);
				//�� ��ȣ
				td = tr.getAllElements(HTMLElementName.TD).get(1);
				font = td.getAllElements(HTMLElementName.FONT).get(0);
				s.setTextNum(font.getContent().toString().trim());
				//�� ����
				td = tr.getAllElements(HTMLElementName.TD).get(3);
				td.getContent().toString().trim().indexOf("NUM="); //�� �ּ� ��ȣ
				s.setLink("http://www.donga.ac.kr/WebApp/BOARD/BASIC/Read.asp?BIDX=25&CAT=&PG=" + pageNum + "&ORD=&KEY=&NUM=" + td.getContent().toString().substring(60, 67) + "&KWD=");
				a = td.getAllElements(HTMLElementName.A).get(0); //�� ����
				s.setTitle(a.getContent().toString().trim());
				//�� ��¥
				td = tr.getAllElements(HTMLElementName.TD).get(5);
				font = td.getAllElements(HTMLElementName.FONT).get(0);
				s.setDate(font.getContent().toString().trim());
			}
			
		}catch(IOException e) {
			Log.e("error", e.getMessage());
		}
	}
	
}
