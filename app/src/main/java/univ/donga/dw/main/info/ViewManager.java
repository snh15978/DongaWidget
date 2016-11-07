package univ.donga.dw.main.info;

import univ.donga.dw.R;
import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

public class ViewManager {
	private GridView mainList;
	private Button login_btn;
	private Button logout_btn;
	public ViewManager(Context context)
	{
		mainList = (GridView)((Activity)context).findViewById(R.id.gridView);
		login_btn = (Button)((Activity)context).findViewById(R.id.login_btn);
		logout_btn = (Button)((Activity)context).findViewById(R.id.logout_btn);
	}
	public GridView getMainList()
	{
		return mainList;
	}
	public Button getLogin_btn() {
		return login_btn;
	}
	public Button getLogout_btn() {
		return logout_btn;
	}
}
