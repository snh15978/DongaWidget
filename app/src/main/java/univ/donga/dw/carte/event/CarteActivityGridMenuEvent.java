package univ.donga.dw.carte.event;

import univ.donga.dw.carte.CarteDialogActivity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class CarteActivityGridMenuEvent implements OnItemClickListener {

	private String[] locations;
	private String[] menus;
	private Context context;
	public CarteActivityGridMenuEvent(Context context, String[] locations, String[] menus)
	{
		this.locations = locations;
		this.menus = menus;
		this.context = context;
	}
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		//메뉴를 해당하는 메뉴를 띄워준다.
		/*
		Dialog dlg = new Dialog(context);
		TextView text = new TextView(context);
		text.setText(menus[position]);
		dlg.setContentView(text);
		dlg.setTitle(locations[position]);
		dlg.show();
		*/
		
		Intent intent = new Intent(context, CarteDialogActivity.class);
		intent.putExtra("location", locations[position]);
		intent.putExtra("menu", menus[position]);		
		context.startActivity(intent);
	}
}
