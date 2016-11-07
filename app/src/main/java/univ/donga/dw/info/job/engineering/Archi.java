package univ.donga.dw.info.job.engineering;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import android.util.Log;

//건축공학과
public class Archi extends EngineeringCollege implements Runnable{
	
	public Archi(String name, String url) {
		super(name, url);
	}

	public void downNewMessage() {
		try {
			URL nURL;
			nURL = new URL(super.getUrl());
			InputStream html = nURL.openStream();
			Source source = new Source(new InputStreamReader(html, "EUC-KR"));
			Element tr = null;
			Element td = null;
			
			tr = (Element) source.getAllElements(HTMLElementName.TR).get(0);
			td = (Element) tr.getAllElements(HTMLElementName.TD).get(0);
			String imsi = td.getContent().toString().trim();
			
		}
		catch (MalformedURLException e) {
			Log.e("error", e.getMessage());
		} catch (IOException e) {
			Log.e("error", e.getMessage());
		}
	}

	public void run() {
		downNewMessage();
	}
}
