package univ.donga.dw.seat.event;

import univ.donga.dw.info.seat.SeatCampusManager;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
public class SeatListClickEvent implements ListView.OnItemClickListener{

	private Context context;
	private SeatCampusManager seatCampusManager;
	public SeatListClickEvent(Context context, SeatCampusManager seatCampusManager) {
		this.context = context;
		this.seatCampusManager = seatCampusManager;
	}
	public void onItemClick(AdapterView<?> parentView, View childView, int position, long id) {
		int no;
		//웹뷰에 뿌려질 위치를 정한다.
		if(position < 5)
			no = position + 1;
		else
			no = position + 6;
		WebView webView = new WebView(context);
		WebSettings set = webView.getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		set.setLoadWithOverviewMode(true);
		set.setUseWideViewPort(true);
		webView.loadUrl("http://168.115.76.58/seat/roomview5.asp?room_no="+no);
		
		new AlertDialog.Builder(context)
		.setTitle("상세보기")
		.setView(webView)
		.setPositiveButton("확인", null)
		.show();
	}
}
