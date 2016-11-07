package univ.donga.dw.campus;

import univ.donga.dw.R;
import android.app.TabActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TabHost;

public class CampusGuideActivity extends TabActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campus);

		TabHost mTabHost = getTabHost();
		Log.e("error", "�� ����");
		mTabHost.setup(); // xml�� tabhost�� ��Ƽ��Ƽ���� ����
		
		TabHost.TabSpec spec;
		spec = mTabHost.newTabSpec("sr"); // ù��° �� ����
		spec.setContent(R.id.tab1); // �� Ŭ������ �� ȭ�� ����
		spec.setIndicator("����ķ�۽�"); //�̸� ����
		mTabHost.addTab(spec); //TabHost�� �߰�
		mTabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.TRANSPARENT);
		
		ImageView img1 = (ImageView)findViewById(R.id.view1);
		img1.setImageResource(R.drawable.seunghak);
		
		spec = mTabHost.newTabSpec("st");
		spec.setContent(R.id.tab2);
		spec.setIndicator("�ι�ķ�۽�");
		mTabHost.addTab(spec); 
		mTabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.TRANSPARENT);
		
		ImageView img2 = (ImageView)findViewById(R.id.view2);
		Drawable drawable = getResources().getDrawable(R.drawable.bumin);
		img2.setImageDrawable(drawable);
		
		spec = mTabHost.newTabSpec("dt");
		spec.setContent(R.id.tab3);
		spec.setIndicator("����ķ�۽�"); 
		mTabHost.addTab(spec);
		mTabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.TRANSPARENT);
		
		ImageView img3 = (ImageView)findViewById(R.id.view3);
		Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.gudeok);
		img3.setImageBitmap(bm);
		
		mTabHost.setCurrentTab(0); //ó�� ������ �� ��Ÿ�� ���� ù��° ������ ����
	}
}