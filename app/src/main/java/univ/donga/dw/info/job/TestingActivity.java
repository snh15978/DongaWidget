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
        int lastPageNumber = test.getLastPageNumber(); // ������ ��������ȣ. ������ ������ؾߵǴ����� �̰ɷ� �Ǵ�. �İ����� �߱ⶫ�� ������������ 27�� ����
        // getList() �ϰ� getLastPageNumber()�� ���� �����
        Button button = (Button)findViewById(R.id.btn);
        button.setText(""+lastPageNumber);
        
        boardInfos = test.getList(1); // 1�� ������ �����´ٴ¸� 2���ϸ� 2�������� ����Ʈ ������
        for(int i=0; i<boardInfos.size(); i++){
        	int number = boardInfos.get(i).getNumber(); // ��������ȣ
        	String title = boardInfos.get(i).getTitle(); // ����
        	String date = boardInfos.get(i).getDate(); // ��¥
        	String url = boardInfos.get(i).getUrl();     // url
        	
        	
        	// �Ʒ��� �׳� ȭ�鿡 �ѷ������� ������
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