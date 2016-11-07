package univ.donga.dw.info.job.parse;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.HTMLElements;
import net.htmlparser.jericho.Source;
import univ.donga.dw.info.job.JobBoardInfo;

//전자공학과
public class Dais extends BaseParser{
	
	public static String listUrl = "http://dais.donga.ac.kr/board/LIST.ASP?name=univee13&key=&word=&b_seq=srchflag=srchflag=srchflag=srchflag=&page=1";
	public static String boardUrlFront = "http://dais.donga.ac.kr/board/read.asp?name=univee13&b_seq=";
	public static String boardUrlLast = "&page=1&srchflag=&key=&word=";
	private URL mUrl;
	private InputStream mInputStream;
	private Source mSource;
	private Element table;
	private int seq = 1; // 글번호
	private int lastPage = -1; // 마지막 페이지 번호
	
	private ArrayList<JobBoardInfo> jobBoardInfos;
		       
	// 게시판 글 리스트 얻어옴, pageNumber = 게시판 페이지 번호
    public ArrayList<JobBoardInfo> getList(int pageNumber){
    	listUrl = "http://dais.donga.ac.kr/board/LIST.ASP?name=univee13&key=&word=&b_seq=srchflag=srchflag=srchflag=srchflag=&page="+pageNumber;
    	try {
        	mUrl = new URL(listUrl);
        	mInputStream = mUrl.openStream();
        	mSource = new Source(new InputStreamReader(mInputStream, "EUC-KR"));
        	table = (Element) mSource.getAllElements(HTMLElementName.TABLE).get(7);
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        jobBoardInfos = new ArrayList<JobBoardInfo>();
		int trSize = table.getAllElements(HTMLElementName.TR).size();
		for(int i=1; i<trSize; i++){
			Element tr = (Element)table.getAllElements(HTMLElementName.TR).get(i);
			Element tdForNumber = (Element)tr.getAllElements(HTMLElementName.TD).get(0);
			int number = Integer.parseInt(tdForNumber.getAllElements(HTMLElementName.P).get(0).getContent().toString().replaceAll("&nbsp;", ""));			
			Element tdForTitle = (Element)tr.getAllElements(HTMLElementName.TD).get(2);
			String title = tdForTitle.getAllElements(HTMLElementName.A).get(0).getContent().toString().replaceAll("&nbsp;", "").replaceAll("<b>", "").replaceAll("<font color=red>", "").replaceAll("<font color=blue>", "").replaceAll("<B>", "");
			Element tdForDate = (Element)tr.getAllElements(HTMLElementName.TD).get(4);
			String date = tdForDate.getAllElements(HTMLElementName.P).get(0).getContent().toString().replaceAll("&nbsp;", "");
			
			String urlTemp = tdForTitle.getAllElements(HTMLElementName.P).get(0).getContent().toString();
			
			jobBoardInfos.add(new JobBoardInfo(number, title, date, "about:blank"));
		}
       // jobBoardInfos.add(new JobBoardInfo(0, "", ""+table.getAllElements().get(0).getContent().toString(), boardUrlFront+""+boardUrlLast));
    	
    	return jobBoardInfos;
    }
    
    // 게시판 마지막 페이지번호 얻음
    public int getLastPageNumber(){
    	
    	try {
        	mUrl = new URL(listUrl);
        	mInputStream = mUrl.openStream();
        	mSource = new Source(new InputStreamReader(mInputStream, "EUC-KR"));
        	
        	// 마지막 페이지 번호 구함
        	Element tableForLastPage = (Element) mSource.getAllElements(HTMLElementName.TABLE).get(4);
        	int start = tableForLastPage.getAllElements().get(0).getContent().toString().lastIndexOf("page=");
        	int end = tableForLastPage.getAllElements().get(0).getContent().toString().lastIndexOf("</a>]");        	
        	String st = tableForLastPage.getAllElements().get(0).getContent().toString().substring(start+5,end);
        	String st2 = st.substring(0, st.lastIndexOf(">"));
        	lastPage = Integer.parseInt(st2);
		} catch (Exception e) {
			// TODO: handle exception
		}

    	
    	return lastPage;
    }
    
}