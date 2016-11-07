package univ.donga.dw.service.book_info;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BookListDownWebClient extends WebViewClient{
	private WebView web;
	private int bookCnt;
	public BookListDownWebClient(WebView web)
	{
		this.web = web;
		this.bookCnt = -1;
	}
	@Override
	public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
		handler.proceed();
	}
	//사용자가 웹뷰에서 무슨작업을 하고 완료하였을때 호출되는 메소드
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		//getElementsByTagName()내부에 원하는태그와 그태그의 위치를 기록하여 해당태그 내부를 가지고 온다.
		//web.loadUrl("javascript:window.HtmlViewer.showHTML(document.getElementsByTagName('html')[0].innerHTML);");
		return true;
	}
	//사용자의 동작과는 상관없이 웹뷰의 페이지가 로딩이 끝나면 호출되는 메소드
	@Override
	public void onPageFinished(WebView view, String url) {
		//대출목록과 연장가능 횟수를 가지고온다.
		if(bookCnt == -1)
		{
			web.loadUrl("javascript:window.HtmlViewer.downCountOfPosibleExtend(document.getElementsByTagName('font')[2].innerHTML);");
			web.loadUrl("javascript:window.HtmlViewer.downBookCnt(document.getElementsByTagName('td')[0].innerHTML);");
		}
		//실제 대출목록들을 갯수만큼 읽어온다.
		else if(bookCnt > 0)
		{
			for(int i = 0; i<bookCnt; i++)
			{
				//등록번호
				web.loadUrl("javascript:window.HtmlViewer.downBookNumber(document.getElementsByTagName('tr')[" +(3+(6*i)+0)+ "].innerHTML);");
				//서명
				web.loadUrl("javascript:window.HtmlViewer.downBookName(document.getElementsByTagName('tr')[" +(3+(6*i)+1)+ "].innerHTML);");
				//반납예정일
				web.loadUrl("javascript:window.HtmlViewer.downBookReturnDay(document.getElementsByTagName('tr')[" +(3+(6*i)+3)+ "].innerHTML);");
				//연장가능확인
				web.loadUrl("javascript:window.HtmlViewer.downBookPosibleExtend(document.getElementsByTagName('tr')[" +(3+(6*i)+5)+ "].innerHTML);");
			}
			//끝났음을 알린다. 의미 없는 내용이다.
			web.loadUrl("javascript:window.HtmlViewer.endOfParsing(document.getElementsByTagName('tr')[0].innerHTML);");
			//다시 대출목록 카운터를 받을 준비를 한다.
			bookCnt = -1;
		}
		//연장버튼을 누르면 들어가는 부분이다.
		else
		{
			//연장하기 버튼을 눌렀을경우 의미 없는 내용이다.
			web.loadUrl("javascript:window.HtmlViewer.extendButtn(document.getElementsByTagName('tr')[0].innerHTML);");
			bookCnt = -1;
		}
	}
	public int getBookCnt() {
		return bookCnt;
	}
	public void setBookCnt(int bookCnt) {
		this.bookCnt = bookCnt;
	}
	
}
