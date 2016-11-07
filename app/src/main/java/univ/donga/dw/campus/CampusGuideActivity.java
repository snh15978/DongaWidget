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
		Log.e("error", "탭 실행");
		mTabHost.setup(); // xml의 tabhost를 액티비티에서 생성
		
		TabHost.TabSpec spec;
		spec = mTabHost.newTabSpec("sr"); // 첫번째 탭 생성
		spec.setContent(R.id.tab1); // 탭 클릭했을 때 화면 구성
		spec.setIndicator("승학캠퍼스"); //이름 설정
		mTabHost.addTab(spec); //TabHost에 추가
		mTabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.TRANSPARENT);
		
		ImageView img1 = (ImageView)findViewById(R.id.view1);
		img1.setImageResource(R.drawable.seunghak);
		
		spec = mTabHost.newTabSpec("st");
		spec.setContent(R.id.tab2);
		spec.setIndicator("부민캠퍼스");
		mTabHost.addTab(spec); 
		mTabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.TRANSPARENT);
		
		ImageView img2 = (ImageView)findViewById(R.id.view2);
		Drawable drawable = getResources().getDrawable(R.drawable.bumin);
		img2.setImageDrawable(drawable);
		
		spec = mTabHost.newTabSpec("dt");
		spec.setContent(R.id.tab3);
		spec.setIndicator("구덕캠퍼스"); 
		mTabHost.addTab(spec);
		mTabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.TRANSPARENT);
		
		ImageView img3 = (ImageView)findViewById(R.id.view3);
		Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.gudeok);
		img3.setImageBitmap(bm);
		
		mTabHost.setCurrentTab(0); //처음 시작할 때 나타날 탭을 첫번째 탭으로 설정
	}
}