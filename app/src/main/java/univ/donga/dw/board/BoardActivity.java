package univ.donga.dw.board;

import java.util.ArrayList;
import java.util.Collections;

import univ.donga.dw.R;
import univ.donga.dw.board.PullToRefreshListView.OnRefreshListener;
import univ.donga.dw.info.job.JobBoardInfo;
import univ.donga.dw.info.job.parse.A812;
import univ.donga.dw.info.job.parse.Arch;
import univ.donga.dw.info.job.parse.BaseParser;
import univ.donga.dw.info.job.parse.Biotech;
import univ.donga.dw.info.job.parse.China;
import univ.donga.dw.info.job.parse.Computer;
import univ.donga.dw.info.job.parse.Dache;
import univ.donga.dw.info.job.parse.Dais;
import univ.donga.dw.info.job.parse.Dauf;
import univ.donga.dw.info.job.parse.Dongatourism;
import univ.donga.dw.info.job.parse.Electrical;
import univ.donga.dw.info.job.parse.Envu;
import univ.donga.dw.info.job.parse.Finance;
import univ.donga.dw.info.job.parse.French;
import univ.donga.dw.info.job.parse.Ie;
import univ.donga.dw.info.job.parse.Japan;
import univ.donga.dw.info.job.parse.Mech;
import univ.donga.dw.info.job.parse.Metal;
import univ.donga.dw.info.job.parse.Mis;
import univ.donga.dw.info.job.parse.Notice;
import univ.donga.dw.info.job.parse.Omp;
import univ.donga.dw.info.job.parse.Sosowel;
import univ.donga.dw.info.job.parse.Trade;
import univ.donga.dw.info.job.parse.Urban;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class BoardActivity extends ListActivity {

//	private List<String> mListItems;
	BaseParser parser;
	ArrayList<JobBoardInfo> boardInfos;
	int nowPageNumber = 1;
	int lastPageNumber = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pulltorefresh);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		

		// Set a listener to be invoked when the list should be refreshed.
		((PullToRefreshListView) getListView())
				.setOnRefreshListener(new OnRefreshListener() {
					public void onRefresh() {
						// Do work to refresh the list here.
						new GetDataTask().execute();
					}
				});

//		mListItems = new ArrayList<String>();
		//mListItems.addAll(Arrays.asList(mStrings));

//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, mListItems);
//		setListAdapter(adapter);
		
		Intent intent = getIntent();
		int which = intent.getIntExtra("WHICH", -1);
		switch (which) {
		case 0:
			setTitle("건축학과");
			parser = new Arch();
			break;
		case 1:
			setTitle("국제관광학과");
			parser = new Dongatourism();
			break;
		case 2:
			setTitle("국제무역학과");
			parser = new Trade();
			break;
		case 3:
			setTitle("경영정보학과");
			parser = new Mis();
			break;
		case 4:
			setTitle("금융학과");
			parser = new Finance();
			break;
		case 5:
			setTitle("기계공학과");
			parser = new Mech();
			break;
		case 6:
			setTitle("도시계획학과");
			parser = new Urban();
			break;
		case 7:
			setTitle("사회복지학과");
			parser = new Sosowel();
			break;
		case 8:
			setTitle("산업경영공학과");
			parser = new Ie();
			break;
		case 9:
			setTitle("생명공학과");
			parser = new Biotech();
			break;
		case 10:
			setTitle("신소재공학과");
			parser = new Metal();
			break;
		case 11:
			setTitle("유기재료고분자공학과");
			parser = new Omp();
			break;
		case 12:
			setTitle("영어영문학과");			
			parser = new A812();
			break;
		case 13:
			setTitle("일어일문학과");
			parser = new Japan();
			break;
		case 14:
			setTitle("중어 중문 학과");
			parser = new China();
			break;
		case 15:
			setTitle("전기공학과");
			parser = new Electrical();
			break;
		case 16:
			setTitle("전자공학과");
			parser = new Dais();
			break;
		case 17:
			setTitle("컴퓨터공학과");
			parser = new Computer();
			break;
		case 18:
			setTitle("패션디자인학과");
			parser = new Dauf();
			break;			
		case 19:
			setTitle("프랑스문화학과");
			parser = new French();
			break;
		case 20:
			setTitle("화학공학과");
			parser = new Dache();
			break;
		case 21:
			setTitle("환경공학과");
			parser = new Envu();
			break;
			
		case 1000:
			setTitle("공지사항");			
			parser = new Notice();
			break;

		default:
			Toast.makeText(this, "잘못된 접근 입니다.", Toast.LENGTH_SHORT).show();
			finish();
			break;
		}
		        
        lastPageNumber = parser.getLastPageNumber();
        boardInfos = parser.getList(nowPageNumber++);
        Collections.reverse(boardInfos);
        
        BoardInfoAdapter m_adapter = new BoardInfoAdapter(this, R.layout.row, boardInfos); 
        setListAdapter(m_adapter);
        
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {
			//mListItems.add(0, "Added new item after refresh...");
			
			if(nowPageNumber <= lastPageNumber){
				ArrayList<JobBoardInfo> temp = parser.getList(nowPageNumber++);
				Collections.reverse(boardInfos);
				boardInfos.addAll(temp);				
				Collections.reverse(boardInfos);
			} else{
				toastShow("더 이상 표시할 내용이 없습니다.");
			}
			
//			for(int i=0; i<boardInfos.size(); i++){
//	        	int number = boardInfos.get(i).getNumber(); // 페이지번호
//	        	String title = boardInfos.get(i).getTitle(); // 제목
//	        	String date = boardInfos.get(i).getDate(); // 날짜
//	        	String url = boardInfos.get(i).getUrl();     // url
//	        	boardInfos.add(new JobBoardInfo(number, title, date, url));
//	        	//mListItems.add(0, number+" "+title+" "+date);	        	
//			}
			//pagenow++;
			//
			
			//boardInfos.add(new JobBoardInfo(1, "asd", "asdsad","asdasd"));			
			
			// Call onRefreshComplete when the list has been refreshed.
			((PullToRefreshListView) getListView()).onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	private String[] mStrings = {"학교 공지사항","학교 공지사항","학교 공지사항","학교 공지사항","학교 공지사항","학교 공지사항","학교 공지사항","학교 공지사항","학교 공지사항","학교 공지사항","학교 공지사항","학교 공지사항","학교 공지사항"};
	
		
	
	 private class BoardInfoAdapter extends ArrayAdapter<JobBoardInfo> {
		 
	        private ArrayList<JobBoardInfo> items;
	 
	        public BoardInfoAdapter(Context context, int textViewResourceId, ArrayList<JobBoardInfo> items) {
	                super(context, textViewResourceId, items);
	                this.items = items;
	        }
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	                View v = convertView;
	                if (v == null) {
	                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	                    v = vi.inflate(R.layout.row, null);
	                }
	                JobBoardInfo info = items.get(position);
	                if (info != null) {
	                        TextView tt = (TextView) v.findViewById(R.id.toptext);
	                        TextView bt = (TextView) v.findViewById(R.id.bottomtext);
	                        if (tt != null){
	                            tt.setText(info.getNumber()+" : "+info.getTitle());                            
	                        }
	                        if(bt != null){
	                                bt.setText(info.getDate());
	                        }
	                }
	                return v;
	        }
	
		
	 }
	 
	 private void toastShow(String str){
		 Toast.makeText(this, str , Toast.LENGTH_SHORT).show();
	 }
	 
	 @Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			// TODO Auto-generated method stub
			super.onListItemClick(l, v, position, id);
			//Toast.makeText(this, ""+id+"/"+position, Toast.LENGTH_SHORT).show();						
			String url = boardInfos.get((int)id).getUrl();
			Toast.makeText(this, boardInfos.get((int)id).getTitle(), Toast.LENGTH_SHORT).show();
			//Uri uri = Uri.parse(url);
			//Intent intent  = new Intent(Intent.ACTION_VIEW, uri);
			//startActivity(intent);			
		}
	
}
