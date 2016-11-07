package univ.donga.dw.carte.info;

import univ.donga.dw.R;
import android.app.Activity;
import android.content.Context;
import android.widget.GridView;
import android.widget.TextView;

public class ViewManager {
	private TextView title1;
	private GridView grid1;
	private TextView title2;
	private GridView grid2;
	public ViewManager(Context context)
	{
		//파싱해온 자료를 화면에 뿌린다.
		title1 = (TextView)((Activity)context).findViewById(R.id.carte_title1);
		grid1 = (GridView)((Activity)context).findViewById(R.id.carte_grid1);
		title2 = (TextView)((Activity)context).findViewById(R.id.carte_title2);
		grid2 = (GridView)((Activity)context).findViewById(R.id.carte_grid2);
	}
	public TextView getTitle1() {
		return title1;
	}
	public GridView getGrid1() {
		return grid1;
	}
	public TextView getTitle2() {
		return title2;
	}
	public GridView getGrid2() {
		return grid2;
	}
}
