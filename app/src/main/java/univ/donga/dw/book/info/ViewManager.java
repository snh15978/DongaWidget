package univ.donga.dw.book.info;

import univ.donga.dw.R;
import univ.donga.dw.service.CampusManagerService;
import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.ToggleButton;

public class ViewManager {
	private Context context;
	
	private WebView book_web;
	private ListView book_list;
	private ToggleButton auto_book_alarm_togglebnt;
	
	public ViewManager(Context context)
	{
		Activity activity = (Activity)context;
		this.context = context;
		book_web = new WebView(context);
		book_list = (ListView)((Activity)context).findViewById(R.id.book_list);
		auto_book_alarm_togglebnt = (ToggleButton)((Activity)context).findViewById(R.id.auto_book_alarm_togglebnt);
	}
	public WebView getBook_web() {
		return book_web;
	}
	public ListView getBook_list() {
		return book_list;
	}
	public ToggleButton getAuto_book_alarm_togglebnt() {
		return auto_book_alarm_togglebnt;
	}
}
