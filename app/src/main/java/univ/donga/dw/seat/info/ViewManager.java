package univ.donga.dw.seat.info;

import univ.donga.dw.R;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ViewManager {

	private Context context;
	
	private TextView seat_time;
	private ListView seat_list;
	
	private Button seat_dont_notify;
	
	private LinearLayout seatMenuCustomView;
	//시작날짜와 끝날짜를 정하는 커스텀메뉴
	private DatePicker start_day;
	private DatePicker end_day;
	//통지간격을 설정할 커스텀메뉴
	private EditText time_term;

	public ViewManager(Context context)
	{
		Activity activity = (Activity)context;
		this.context = context;
		seat_list = (ListView)activity.findViewById(R.id.seat_list);
		seat_time = (TextView)activity.findViewById(R.id.seat_time);
		seat_dont_notify = (Button)activity.findViewById(R.id.seat_dont_notify);
	}
	public TextView getSeat_time() {
		return seat_time;
	}
	public ListView getSeat_list() {
		return seat_list;
	}
	public void initSeatMenuStartEndCustomView()
	{
		seatMenuCustomView = (LinearLayout)View.inflate(context, R.layout.seat_menu_start_end_day, null);
		start_day = (DatePicker)seatMenuCustomView.findViewById(R.id.start_day);
		end_day = (DatePicker)seatMenuCustomView.findViewById(R.id.end_day);
	}
	public void initSeatMenuTimeTermCustomView()
	{
		seatMenuCustomView = (LinearLayout)View.inflate(context, R.layout.seat_menu_time_term, null);
		time_term = (EditText)seatMenuCustomView.findViewById(R.id.time_term);
	}
	public LinearLayout getSeatMenuCustomView() {
		return seatMenuCustomView;
	}
	public DatePicker getStart_day() {
		return start_day;
	}
	public DatePicker getEnd_day() {
		return end_day;
	}
	public EditText getTime_term() {
		return time_term;
	}
	public Button getSeat_dont_notify() {
		return seat_dont_notify;
	}
}
