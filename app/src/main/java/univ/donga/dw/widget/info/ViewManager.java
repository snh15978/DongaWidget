package univ.donga.dw.widget.info;

import univ.donga.dw.R;
import univ.donga.dw.R.id;
import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewManager {
	private LinearLayout mainLayout;
	private Button make_btn;
	public ViewManager(Context context)
	{
		// ���������� �Ѹ� ���� ���̾ƿ��� ������ �´�.
		mainLayout = (LinearLayout)((Activity)context).findViewById(R.id.widget_carte_config_layout);
		make_btn = (Button)((Activity)context).findViewById(R.id.widget_carte_config_make_btn);
	}
	public Button getMake_btn() {
		return make_btn;
	}
	public LinearLayout getMainLayout() {
		return mainLayout;
	}
}
