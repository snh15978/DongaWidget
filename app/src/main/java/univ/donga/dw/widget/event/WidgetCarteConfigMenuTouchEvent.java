package univ.donga.dw.widget.event;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class WidgetCarteConfigMenuTouchEvent implements LinearLayout.OnTouchListener{

	private LinearLayout layout;
	private CheckBox imsiCheck;
	
	public WidgetCarteConfigMenuTouchEvent(LinearLayout layout)
	{
		this.layout = layout;
	}
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP)
			layout.setBackgroundColor(Color.WHITE);
		else if(event.getAction() == MotionEvent.ACTION_MOVE)
			layout.setBackgroundColor(Color.CYAN);
		else if(event.getAction() == MotionEvent.ACTION_DOWN)
			layout.setBackgroundColor(Color.CYAN);
		else
			layout.setBackgroundColor(Color.WHITE);
		return false;
	}
}
