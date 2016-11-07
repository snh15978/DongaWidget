package univ.donga.dw.book.info;

import java.util.StringTokenizer;

import univ.donga.dw.book.BookActivity;
import univ.donga.dw.info.book.BookInfo;
import android.content.Context;
import android.os.Handler;

public class BookListDownJavaScriptInterface {
	private Context context;
	private DataManager dataManager;
	private String countOfPosibleExtend;
	private String bookCnt;
	private BookInfo bookInfo;
	private Handler mAfterDown;
	public BookListDownJavaScriptInterface(Context context, DataManager dataManager, Handler mAfterDown)
	{
		this.context = context;	
		this.dataManager = dataManager;
		bookCnt = "";
		this.mAfterDown = mAfterDown;
	}

	// ���尡��Ƚ����?
	public void downCountOfPosibleExtend(String html) {
		this.countOfPosibleExtend = html;
	}
	//������ å�� ����ΰ�?
	public void downBookCnt(String html)
	{
		StringTokenizer stTok = new StringTokenizer(html, " :  /");
		stTok.nextToken();
		//��� ���Ǵ��� �����صд�.
		this.bookCnt = stTok.nextToken();
		mAfterDown.sendEmptyMessage(0);
	}
	// ������ å��ȣ�� ������´�.
	public void downBookNumber(String html)
	{
		bookInfo = new BookInfo();
		StringTokenizer stTok = new StringTokenizer(html, ">");
		stTok.nextToken();
		stTok.nextToken();
		stTok.nextToken();
		//å�̸� ����
		bookInfo.setBookNumber(stTok.nextToken().replace(" <br", ""));
	}
	//������ å�̸��� ������´�.
	public void downBookName(String html)
	{
		StringTokenizer stTok = new StringTokenizer(html, ">");
		stTok.nextToken();
		stTok.nextToken();
		stTok.nextToken();
		stTok.nextToken();
		//å ��Ϲ�ȣ����
		bookInfo.setBookName(stTok.nextToken().replace("</a",	""));
	}
	
	// ������ �ݳ���¥�� ������´�.
	public void downBookReturnDay(String html)
	{

		StringTokenizer stTok = new StringTokenizer(html, ">");
		stTok.nextToken();
		stTok.nextToken();
		stTok.nextToken();
		String buf = stTok.nextToken().replace("</td", "");
		stTok = new StringTokenizer(buf, ".");
		//�ݳ���¥ ����
		bookInfo.setReturnYear(Integer.parseInt(stTok.nextToken()));
		bookInfo.setReturnMonth(Integer.parseInt(stTok.nextToken()));
		bookInfo.setReturnDay(Integer.parseInt(stTok.nextToken()));
	}

	// ���尡�� ������ ������´�.
	public void downBookPosibleExtend(String html) {
		//����Ұ����ϸ� �Ұ���ǥ�ø� �Ѵ�.
		if(html.indexOf("����Ұ�") > 0)
			bookInfo.setPosibleExtend(false);
		else
			bookInfo.setPosibleExtend(true);
		//����Ʈ�� �߰��Ѵ�.
		dataManager.getBookListManager().addBookList(bookInfo);
	}
	//�Ľ��� �������� �˸���.
	public void endOfParsing(String html)
	{
		mAfterDown.sendEmptyMessage(1);
	}
	//�����ϱ� ��ư��
	public void extendButtn(String html)
	{
		BookActivity.mProgress.dismiss();
	}
	
	public String getBookCnt()
	{
		return bookCnt;
	}
	public BookInfo getBookInfo()
	{
		return bookInfo;
	}

	public String getCountOfPosibleExtend() {
		return countOfPosibleExtend;
	}

	public void setCountOfPosibleExtend(String countOfPosibleExtend) {
		this.countOfPosibleExtend = countOfPosibleExtend;
	}
	
}
