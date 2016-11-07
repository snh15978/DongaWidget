package univ.donga.dw.book;

import univ.donga.dw.R;
import univ.donga.dw.book.info.DataManager;
import univ.donga.dw.book.info.ViewManager;
import univ.donga.dw.info.book.BookInfo;
import univ.donga.dw.info.book.BookListManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookListAdapter extends BaseAdapter{
	
	private Context context;
	private ViewManager viewManager;
	private DataManager dataManager;
	private LayoutInflater mInflater;
	private BookListManager bookListManager;
	public BookListAdapter(Context context, ViewManager viewManager, DataManager dataManager)
	{
		this.context = context;
		this.viewManager = viewManager;
		this.dataManager = dataManager;
		mInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		this.bookListManager = dataManager.getBookListManager();
	}
	public int getCount() {
			return bookListManager.getBookListSize();
	}

	public BookInfo getItem(int position) {
		return bookListManager.getBookList(position);
	}

	public long getItemId(int position) {
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		TextView book_info = null;
		TextView book_return_day = null;
		Button extend_btn = null;
		if (convertView == null) 
			convertView = mInflater.inflate(R.layout.custom_book_list, parent, false);

		// å ������ �ִ´�.
		book_info = (TextView) convertView.findViewById(R.id.book_info);
		book_info.setText(bookListManager.getBookList(pos).getBookName());
		book_info.setSelected(true);
		//�ݳ����� �ִ´�.
		book_return_day = (TextView) convertView.findViewById(R.id.book_return_day);
		book_return_day.setText("�ݳ���:"+bookListManager.getBookList(pos).getReturnYear()+"-"
				+bookListManager.getBookList(pos).getReturnMonth()+"-"
				+bookListManager.getBookList(pos).getReturnDay());

			
		extend_btn = (Button) convertView.findViewById(R.id.extend_btn);
		//������ �Ұ����Ѱ��
		if(bookListManager.getBookList(pos).isPosibleExtend() == false)
		{
			extend_btn.setClickable(false);
			extend_btn.setText("����Ұ�");
		}
		//������ �����Ѱ��
		else
		{
			extend_btn.setText("�����ϱ�");
			extend_btn.setOnClickListener(new Button.OnClickListener(){
				public void onClick(View v) {
					//����Ƚ���� ���°�쳪 ������ �Ұ����Ѱ�� �������� �ʴ´�.
					if(bookListManager.getCountOfPosibleExtend() == 0)
						Toast.makeText(context, "����Ƚ�� �ʰ��� ����Ұ��� �մϴ�.", Toast.LENGTH_SHORT).show();
					//������ �����Ѱ�� �����Ѵ�.
					else
					{	
						//-2�� ����־ Ŭ������ �����Ų��.
						dataManager.getBookListDownWebClient().setBookCnt(-2);
						viewManager.getBook_web().loadUrl("http://dalis.donga.ac.kr/m/mLnRenewPrss.csp?BARCODENO="+bookListManager.getBookList(pos).getBookNumber());
						//�ٽ� å�� ������ �ε��Ѵ�.
						viewManager.getBook_web().loadUrl(dataManager.getBOOK_LIST_URL());
					}
				}
			});
		}
		return convertView;
	}
}
