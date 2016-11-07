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
	//����ڰ� ���信�� �����۾��� �ϰ� �Ϸ��Ͽ����� ȣ��Ǵ� �޼ҵ�
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		//getElementsByTagName()���ο� ���ϴ��±׿� ���±��� ��ġ�� ����Ͽ� �ش��±� ���θ� ������ �´�.
		//web.loadUrl("javascript:window.HtmlViewer.showHTML(document.getElementsByTagName('html')[0].innerHTML);");
		return true;
	}
	//������� ���۰��� ������� ������ �������� �ε��� ������ ȣ��Ǵ� �޼ҵ�
	@Override
	public void onPageFinished(WebView view, String url) {
		//�����ϰ� ���尡�� Ƚ���� ������´�.
		if(bookCnt == -1)
		{
			web.loadUrl("javascript:window.HtmlViewer.downCountOfPosibleExtend(document.getElementsByTagName('font')[2].innerHTML);");
			web.loadUrl("javascript:window.HtmlViewer.downBookCnt(document.getElementsByTagName('td')[0].innerHTML);");
		}
		//���� �����ϵ��� ������ŭ �о�´�.
		else if(bookCnt > 0)
		{
			for(int i = 0; i<bookCnt; i++)
			{
				//��Ϲ�ȣ
				web.loadUrl("javascript:window.HtmlViewer.downBookNumber(document.getElementsByTagName('tr')[" +(3+(6*i)+0)+ "].innerHTML);");
				//����
				web.loadUrl("javascript:window.HtmlViewer.downBookName(document.getElementsByTagName('tr')[" +(3+(6*i)+1)+ "].innerHTML);");
				//�ݳ�������
				web.loadUrl("javascript:window.HtmlViewer.downBookReturnDay(document.getElementsByTagName('tr')[" +(3+(6*i)+3)+ "].innerHTML);");
				//���尡��Ȯ��
				web.loadUrl("javascript:window.HtmlViewer.downBookPosibleExtend(document.getElementsByTagName('tr')[" +(3+(6*i)+5)+ "].innerHTML);");
			}
			//�������� �˸���. �ǹ� ���� �����̴�.
			web.loadUrl("javascript:window.HtmlViewer.endOfParsing(document.getElementsByTagName('tr')[0].innerHTML);");
			//�ٽ� ������ ī���͸� ���� �غ� �Ѵ�.
			bookCnt = -1;
		}
		//�����ư�� ������ ���� �κ��̴�.
		else
		{
			//�����ϱ� ��ư�� ��������� �ǹ� ���� �����̴�.
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
