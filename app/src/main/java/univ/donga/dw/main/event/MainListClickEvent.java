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
			.setTitle("�а��� ������ �ּ���")
			.setItems(dataManager.getJobCollegeManager().getEngineeringCollegeNames(), 
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(context, BoardActivity.class);
							intent.putExtra("WHICH", which);
							context.startActivity(intent);							
						}
					})
			.setNegativeButton("�ݱ�", null)
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
			//�α����� �Ǿ��ִ� ��쿡�� ����Ҽ��ִ�.
			if(viewManager.getLogout_btn().getVisibility() == View.VISIBLE)
				context.startActivity(new Intent(context, BookActivity.class));
			else
				Toast.makeText(context, "�α����ϼž� ����Ҽ� �ֽ��ϴ�.", Toast.LENGTH_SHORT).show();
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
			it.putExtra(Intent.EXTRA_SUBJECT, "���ƴ��б� �� ����");
			it.putExtra(Intent.EXTRA_TEXT, "�ۿ� ���� ���ǻ����̳� ������ �������ּ���. \n\n����� �߻��� ���� �Ǵ� ���ǻ��� : \n\n�����ֽ� ������ ������ ������Ʈ�� �ݿ��� �� �ֵ��� ����ϰڽ��ϴ�.");   
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
