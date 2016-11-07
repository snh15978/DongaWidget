package univ.donga.dw.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import univ.donga.dw.info.carte.CarteCampusManager;
import android.os.Handler;
import android.util.Log;

public class CarteDownThread extends Thread {
	private String url1;
	private String url2;
	private String url3;
	private Source source;
    private Handler mAfterDown;
    private String imsi = "";
	private CarteCampusManager campus;
	private int cnt = 0;
	private int year, month, day;
	public CarteDownThread(Handler mAfterDown, CarteCampusManager campus)
	{
		this.mAfterDown = mAfterDown;
		this.campus = campus;
		// ���� ��¥�� ������ ���� ���� Ŭ����
		Calendar ca = Calendar.getInstance();
		year = ca.get(Calendar.YEAR); // ���� �� ���������
		month = ca.get(Calendar.MONTH) + 1; // ���� �� ��������
		day = ca.get(Calendar.DAY_OF_MONTH); // ���� �� ��������
		// �Ľ��ϰ��� �ϴ� URL�� ����Ѵ�.
		url1 = "http://m.donga.ac.kr/SUB003/SUB_003001.asp?PID=003001&RDIV=1&seldate="
				+ year + "-" + month + "-" + day;
		url2 = "http://m.donga.ac.kr/SUB003/SUB_003001.asp?PID=003001&RDIV=2&seldate="
				+ year + "-" + month + "-" + day;
		url3 = "http://hanlim.donga.ac.kr/SubPage/SUB001002/SUB001002007.asp?seldate="
				+ year + "-" + month + "-" + day;
	}
	public void run()
	{
		openCampus(url1, "����ȸ��", 8, 0);
		openCampus(url2, "�л�ȸ��", 12, 1);
		openCampus(url3, year + "-" + month + "-" + day, 1, 2);
		//�ٿ�ε尡 �� �Ǿ����� ���ξ�Ƽ��Ƽ�� �˷��ش�.
		if(mAfterDown != null)
			mAfterDown.sendEmptyMessage(0);
	}
	void openCampus(String url, String location, int limit, int campusNumber)
	{
		try {
			URL nURL;
			nURL = new URL(url);
			InputStream html = nURL.openStream();
			source = new Source(new InputStreamReader(html, "EUC-KR"));
			Element ul = null;
			Element li = null;
			Element table = null;
			Element tr = null;
			Element td = null;
			
			while(true)
			{
				//����ķ�۽��� ����, �ι�ķ�۽� ó��
				if (campusNumber != 2) {
					ul = (Element) source.getAllElements(HTMLElementName.UL)
							.get(cnt);
					if (ul.getContent().toString().trim().indexOf(location) > 0)
						break;
					++cnt;
				}
				//�Ѹ���Ȱ�� ó��
				else
				{
					imsi = "";
					table = (Element) source.getAllElements(HTMLElementName.TABLE)
							.get(cnt);
					if (table.getContent().toString().trim().indexOf(location) > 0
							&& table.getContent().toString().trim()
									.indexOf("<input") <= 0
							&& table.getContent().toString().trim()
									.indexOf("<table") <= 0)
						break;
					++cnt;
				}
			}
			for(int i = cnt; i<cnt+limit; i++)
			{
				//����ķ�۽��� ����, �ι�ķ�۽� ó��
				if (campusNumber != 2) {
					// i��° ul�� ������ �´�.
					ul = (Element) source.getAllElements(HTMLElementName.UL)
							.get(i);
					// 0��° tr�� ������ �ͼ�
					li = (Element) ul.getAllElements(HTMLElementName.LI).get(0);
					imsi = li.getContent().toString().trim();
					//�ʿ���� ���� �����ϴ� �۾�
					imsi = imsi.replace("<br>\n<br>", "");
					imsi = imsi.replace("&nbsp;", "");
					imsi = imsi.replace("<p>", "");
					imsi = imsi.replace("</p>", "");
					imsi = imsi.replace("<font color=#FF6600>", "");
					imsi = imsi.replace("</font>", "");
					imsi = imsi.replace("<br>", "");
					imsi = imsi.replace("| ", "");
					//Ŭ������ ���� �Է�.
					if((i-cnt)%2 == 0)
						campus.getCarteCampusInfo(campusNumber).setLocation(imsi, (i-cnt)/2);
					else
						campus.getCarteCampusInfo(campusNumber).setMenu(imsi, (i-cnt-1)/2);
				}
				//�Ѹ���Ȱ�� ó��
				else
				{
					//�л�ȸ���� ���̺�ȭ�Ǿ��ֵ�.
					table = (Element) source.getAllElements(HTMLElementName.TABLE).get(cnt);
					for(int j = 1; j<4; j++)
					{
						tr = (Element)table.getAllElements(HTMLElementName.TR).get(j);
						td = (Element)tr.getAllElements(HTMLElementName.TD).get(0);
						imsi = imsi + td.getContent().toString().trim() + "\n";
						
						td = (Element)tr.getAllElements(HTMLElementName.TD).get(1);
						imsi = imsi + td.getContent().toString().trim() + "\n";
					}
					imsi = imsi.replace("<br>", "\n");
					imsi = imsi.replace("<b>", "");
					imsi = imsi.replace("</b>", "");
					campus.getCarteCampusInfo(campusNumber-2).setLocation("�Ѹ���Ȱ��", 4);
					campus.getCarteCampusInfo(campusNumber-2).setMenu(imsi, 4);
				}
			}
		}
		catch (MalformedURLException e) {
			Log.e("error", e.getMessage());
		} catch (IOException e) {
			Log.e("error", e.getMessage());
		}
	}
}
