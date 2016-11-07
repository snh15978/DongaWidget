package univ.donga.dw.info.job;

import java.util.ArrayList;

import univ.donga.dw.R;
import univ.donga.dw.info.job.parse.Computer;
import univ.donga.dw.info.job.parse.Test;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestingActivity extends Activity {
	
	TextView mContent;
	Button mButton;
	LinearLayout ll;
	ArrayList<JobBoardInfo> boardInfos;
	Test test;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        
        ll = (LinearLayout)findViewById(R.id.testLayout);
        test = new Test();        
        int lastPageNumber = test.getLastPageNumber(); // 마지막 페이지번호. 페이지 몇까지해야되는지는 이걸로 판단. 컴공꺼로 했기땜에 마지막페이지 27로 나옴
        // getList() 하고 getLastPageNumber()만 갖다 쓰면됨
        Button button = (Button)findViewById(R.id.btn);
        button.setText(""+lastPageNumber);
        
        boardInfos = test.getList(1); // 1번 페이지 가져온다는말 2로하면 2번페이지 리스트 가져옴
        for(int i=0; i<boardInfos.size(); i++){
        	int number = boardInfos.get(i).getNumber(); // 페이지번호
        	String title = boardInfos.get(i).getTitle(); // 제목
        	String date = boardInfos.get(i).getDate(); // 날짜
        	String url = boardInfos.get(i).getUrl();     // url
        	
        	
        	// 아래는 그냥 화면에 뿌려볼려고 적은것
        	TextView tv = new TextView(this);
        	tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        	tv.setText(number+":" + title +" "+date);
        	tv.setId(number);
        	tv.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(test.boardUrlFront+v.getId()+test.boardUrlLast));
					startActivity(intent);
				}
			});
        	ll.addView(tv);
        	
        }
        
        
    }
    
    
    
    
}