package univ.donga.dw.widget.event;

import android.widget.CheckBox;
import android.widget.CompoundButton;

public class WidgetCarteConfigCheckEvent implements CheckBox.OnCheckedChangeListener{
	
	private boolean[][] checkBox;
	private int i, j;
	public WidgetCarteConfigCheckEvent(boolean[][] checkBox, int i, int j)
	{
		this.checkBox = checkBox;
		this.i = i; this.j = j;
	}
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		checkBox[i][j] = isChecked;
	}
}
