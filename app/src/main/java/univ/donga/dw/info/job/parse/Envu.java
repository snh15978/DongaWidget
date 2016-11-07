package univ.donga.dw.info.job.parse;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import univ.donga.dw.info.job.JobBoardInfo;

//환경공학과
public class Envu extends BaseParser{
	
	public static String listUrl = "http://cms.donga.ac.kr/servlet/kr.co.k2web.jwizard.contents.board.boardUser.servlet.userMainServlet?handle=2&board_id=2&client_id=envu&site_id=envu&curPage=1&search=&column=&catecolumn=&catesearch=&b_type=01&list_type=01&board_seq=&command=list&warning_yn=N&category_id=0&category_depth=";
	public static String boardUrlFront = "http://cms.donga.ac.kr/servlet/kr.co.k2web.jwizard.contents.board.boardUser.servlet.userMainServlet?handle=2&board_id=2&client_id=envu&site_id=envu&curPage=1&search=&column=&catecolumn=&catesearch=&b_type=01&list_type=01&board_seq=";
	public static String boardUrlLast = "&command=view&warning_yn=N&category_id=0&category_depth=";
	private URL mUrl;
	private InputStream mInputStream;
	private Source mSource;
	private Element table;
	private int seq = 1; // 글번호
	private int lastPage = -1; // 마지막 페이지 번호
	
	private ArrayList<JobBoardInfo> jobBoardInfos;
		       
	// 게시판 글 리스트 얻어옴, pageNumber = 게시판 페이지 번호
    public ArrayList<JobBoardInfo> getList(int pageNumber){
    	listUrl = "http://cms.donga.ac.kr/servlet/kr.co.k2web.jwizard.contents.board.boardUser.servlet.userMainServlet?handle=2&board_id=2&client_id=envu&site_id=envu&curPage="+pageNumber+"&search=&column=&catecolumn=&catesearch=&b_type=01&list_type=01&board_seq=&command=list&warning_yn=N&category_id=0&category_depth=";
    	try {
        	mUrl = new URL(listUrl);
        	mInputStream = mUrl.openStream();
        	mSource = new Source(new InputStreamReader(mInputStream, "EUC-KR"));
        	table = (Element) mSource.getAllElements(HTMLElementName.TABLE).get(4);
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        jobBoardInfos = new ArrayList<JobBoardInfo>();
		int trSize = table.getAllElements(HTMLElementName.TR).size();
		for(int i=1; i<trSize; i++){
			Element tr = (Element)table.getAllElements(HTMLElementName.TR).get(i);
			Element tdForNumber = (Element)tr.getAllElements(HTMLElementName.TD).get(0);
			int number = Integer.parseInt(tdForNumber.getAllElements().get(0).getContent().toString().replaceAll("&nbsp;", ""));					
			Element tdForTitle = (Element)tr.getAllElements(HTMLElementName.TD).get(1);
			String title = tdForTitle.getAllElements(HTMLElementName.A).get(0).getContent().toString().replaceAll("&nbsp;", "");
			Element tdForDate = (Element)tr.getAllElements(HTMLElementName.TD).get(2);
			String date = tdForDate.getAllElements().get(0).getContent().toString().replaceAll("&nbsp;", "");					
			jobBoardInfos.add(new JobBoardInfo(number, title, date, boardUrlFront+number+boardUrlLast));
		}    	
    	
    	return jobBoardInfos;
    }
    
    // 게시판 마지막 페이지번호 얻음
    public int getLastPageNumber(){
    	
    	try {
        	mUrl = new URL(listUrl);
        	mInputStream = mUrl.openStream();
        	mSource = new Source(new InputStreamReader(mInputStream, "EUC-KR"));
        	
        	// 마지막 페이지 번호 구함
        	Element tableForLastPage = (Element) mSource.getAllElements(HTMLElementName.TABLE).get(5);
        	int start = tableForLastPage.getAllElements().get(0).getContent().toString().lastIndexOf("('");
        	int end = tableForLastPage.getAllElements().get(0).getContent().toString().lastIndexOf("')");
        	String st = tableForLastPage.getAllElements().get(0).getContent().toString().substring(start+2,end);
        	lastPage = Integer.parseInt(st);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	return lastPage;
    }
    
}