package univ.donga.dw.widget.event;

import univ.donga.dw.R;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class WidgetCarteConfigMenuClickEvent implements LinearLayout.OnClickListener{

	private LinearLayout layout;
	private CheckBox imsiCheck;
	
	public WidgetCarteConfigMenuClickEvent(LinearLayout layout)
	{
		this.layout = layout;
	}
	public void onClick(View v)
	{
		imsiCheck = (CheckBox)layout.findViewById(R.id.checkbox);
		imsiCheck.setChecked(!imsiCheck.isChecked());
	}
}
