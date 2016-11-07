package univ.donga.dw.seat;

import java.util.StringTokenizer;

import univ.donga.dw.info.seat.SeatCampusManager;
import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class SeatListAdapter extends BaseAdapter{

	private Context context;
	private SeatCampusManager seatCampusManager;
	//텍스트에 색을입히기 위해 위치를 기록해두는 변수들
	private int start1, end1;
	private int start2, end2;
	private int start3, end3;
	public SeatListAdapter(Context context, SeatCampusManager seatCampusManager)
	{
		this.context = context;
		this.seatCampusManager = seatCampusManager;
	}
	public int getCount() {
		return seatCampusManager.sizeOfseatCampusInfo();
	}

	public Object getItem(int position) {
		return seatCampusManager.getSeatCampusInfo(position);
	}

	public long getItemId(int position) {
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		//화면에 보이는것 만크만 인플레이터하고
		String str;
		TextView textView = new TextView(context);
		
		str = seatCampusManager.getSeatCampusInfo(position).getName();
		start1 = str.length();
		str = str + " " + seatCampusManager.getSeatCampusInfo(position).getTotalSeat();
		end1 = str.length();
		start2 = str.length();
		str = str + " " + seatCampusManager.getSeatCampusInfo(position).getUseSeat();
		end2 = str.length();
		start3 = str.length();
		str = str + " " + seatCampusManager.getSeatCampusInfo(position).getRemainSeat();
		end3 = str.length();
		str = str + " " + seatCampusManager.getSeatCampusInfo(position).getUseRate();
		
		// 위에서 읽어온정보를 이용하여 텍스트들에 색을 입히는 작업을 ㅎ나다.
		SpannableStringBuilder sp = null;
		sp = new SpannableStringBuilder(str);
		sp.setSpan(new ForegroundColorSpan(Color.RED), start1, end1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		sp.setSpan(new ForegroundColorSpan(Color.GREEN), start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		sp.setSpan(new ForegroundColorSpan(Color.BLUE), start3, end3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView.setText(sp);
		textView.setFocusable(false);
		
		return textView;
	}
}
