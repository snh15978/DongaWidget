package univ.donga.dw.main;

import java.util.ArrayList;

import univ.donga.dw.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<MainListItem> listArr;
	private LayoutInflater mInflater;
	public MainListAdapter(Context context, ArrayList<MainListItem> listArr)
	{
		this.context = context;
		this.listArr = listArr;
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public int getCount() {
		return listArr.size();
	}

	public MainListItem getItem(int position) {
		return listArr.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null)
			convertView = mInflater.inflate(R.layout.custom_main_list, parent, false);
		TextView txt = (TextView)convertView.findViewById(R.id.custom_main_list_txt);
		ImageView img = (ImageView)convertView.findViewById(R.id.custom_main_list_img);
		txt.setText(listArr.get(position).getText());
		txt.setTextColor(Color.BLACK);
		img.setImageResource(listArr.get(position).getIconRes());
		return convertView;
	}

}
