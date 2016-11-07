package univ.donga.dw.main.event;

import univ.donga.dw.R;
import univ.donga.dw.board.BoardActivity;
import univ.donga.dw.book.BookActivity;
import univ.donga.dw.campus.CampusGuideActivity;
import univ.donga.dw.card.StudentCardActivity;
import univ.donga.dw.carte.CarteActivity;
import univ.donga.dw.main.info.DataManager;
import univ.donga.dw.main.info.ViewManager;
import univ.donga.dw.seat.SeatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainListClickEvent implements ListView.OnItemClickListener{

	private Context context;
	private ViewManager viewManager;
	private DataManager dataManager;
	public MainListClickEvent(Context context, ViewManager viewManager, DataManager dataManager)
	{
		this.context = context;
		this.viewManager = viewManager;
		this.dataManager = dataManager;
	}
	public void onItemClick(AdapterView<?> parentView, View childView, int position, long id) {
		switch(position)
		{
		case 0:
			Intent intent = new Intent(context, BoardActivity.class);
			intent.putExtra("WHICH", 1000);
			context.startActivity(intent);
			break;
		case 1:
			//context.startActivity(new Intent(context, TestingActivity.class));
			
			
			new AlertDialog.Builder(context)
			.setTitle("학과를 선택해 주세요")
			.setItems(dataManager.getJobCollegeManager().getEngineeringCollegeNames(), 
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(context, BoardActivity.class);
							intent.putExtra("WHICH", which);
							context.startActivity(intent);							
						}
					})
			.setNegativeButton("닫기", null)
			.show();
			
			//context.startActivity(new Intent(context, TestingActivity.class));
			break;
		case 2:
			intent = new Intent(context, BoardActivity.class);
			intent.putExtra("WHICH", 1001);
			context.startActivity(intent);
			break;
		case 3:
			context.startActivity(new Intent(context, CarteActivity.class));
			break;
		case 4:
			//로그인이 되어있는 경우에만 사용할수있다.
			if(viewManager.getLogout_btn().getVisibility() == View.VISIBLE)
				context.startActivity(new Intent(context, BookActivity.class));
			else
				Toast.makeText(context, "로그인하셔야 사용할수 있습니다.", Toast.LENGTH_SHORT).show();
			break;
		case 5:
			context.startActivity(new Intent(context, SeatActivity.class));
			break;
		case 6:
			context.startActivity(new Intent(context, StudentCardActivity.class));
			break;
		case 7:
			Uri uri = Uri.parse("mailto:cookierun@donga.ac.kr");
			Intent it = new Intent(Intent.ACTION_SENDTO, uri);
			it.putExtra(Intent.EXTRA_SUBJECT, "동아대학교 앱 문의");
			it.putExtra(Intent.EXTRA_TEXT, "앱에 대한 건의사항이나 오류를 제보해주세요. \n\n사용중 발생한 문제 또는 건의사항 : \n\n보내주신 내용은 가능한 업데이트에 반영할 수 있도록 노력하겠습니다.");   
			context.startActivity(it);
			break;
		case 8:
			context.startActivity(new Intent(context, CampusGuideActivity.class));
			break;
			
		/*case 6:
			Intent intent = new Intent(context, TaxiTalkActivity.class);
			intent.putExtra("TAXI", dataManager.getTaxiSocketInfo());
			context.startActivity(intent);
			break;*/
		}
	}

}
