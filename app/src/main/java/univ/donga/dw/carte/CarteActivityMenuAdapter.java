package univ.donga.dw.carte;

import univ.donga.dw.R;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CarteActivityMenuAdapter extends BaseAdapter {

	private Context context;
	private String[] str;
	public CarteActivityMenuAdapter(Context context, String[] str)
	{
		this.context = context;
		this.str = str;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return str[position];
	}
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView;
		if (convertView == null) //초기 생성
		{
			textView = new TextView(context);
			textView.setGravity(Gravity.CENTER);
			textView.setPadding(5, 10, 5, 10);
			textView.setTextColor(Color.BLACK);
			textView.setBackgroundResource(R.drawable.bgblue_round); 
			textView.setTextSize(15);			
		}
		else
			textView = (TextView)convertView;
		textView.setText(str[position]);
		return textView;
		// TODO Auto-generated method stub
	}

}
