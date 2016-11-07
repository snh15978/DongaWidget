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
			setTitle("�����а�");
			parser = new Arch();
			break;
		case 1:
			setTitle("���������а�");
			parser = new Dongatourism();
			break;
		case 2:
			setTitle("���������а�");
			parser = new Trade();
			break;
		case 3:
			setTitle("�濵�����а�");
			parser = new Mis();
			break;
		case 4:
			setTitle("�����а�");
			parser = new Finance();
			break;
		case 5:
			setTitle("�����а�");
			parser = new Mech();
			break;
		case 6:
			setTitle("���ð�ȹ�а�");
			parser = new Urban();
			break;
		case 7:
			setTitle("��ȸ�����а�");
			parser = new Sosowel();
			break;
		case 8:
			setTitle("����濵���а�");
			parser = new Ie();
			break;
		case 9:
			setTitle("������а�");
			parser = new Biotech();
			break;
		case 10:
			setTitle("�ż�����а�");
			parser = new Metal();
			break;
		case 11:
			setTitle("����������ڰ��а�");
			parser = new Omp();
			break;
		case 12:
			setTitle("������а�");			
			parser = new A812();
			break;
		case 13:
			setTitle("�Ͼ��Ϲ��а�");
			parser = new Japan();
			break;
		case 14:
			setTitle("�߾� �߹� �а�");
			parser = new China();
			break;
		case 15:
			setTitle("������а�");
			parser = new Electrical();
			break;
		case 16:
			setTitle("���ڰ��а�");
			parser = new Dais();
			break;
		case 17:
			setTitle("��ǻ�Ͱ��а�");
			parser = new Computer();
			break;
		case 18:
			setTitle("�мǵ������а�");
			parser = new Dauf();
			break;			
		case 19:
			setTitle("��������ȭ�а�");
			parser = new French();
			break;
		case 20:
			setTitle("ȭ�а��а�");
			parser = new Dache();
			break;
		case 21:
			setTitle("ȯ����а�");
			parser = new Envu();
			break;
			
		case 1000:
			setTitle("��������");			
			parser = new Notice();
			break;

		default:
			Toast.makeText(this, "�߸��� ���� �Դϴ�.", Toast.LENGTH_SHORT).show();
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
				toastShow("�� �̻� ǥ���� ������ �����ϴ�.");
			}
			
//			for(int i=0; i<boardInfos.size(); i++){
//	        	int number = boardInfos.get(i).getNumber(); // ��������ȣ
//	        	String title = boardInfos.get(i).getTitle(); // ����
//	        	String date = boardInfos.get(i).getDate(); // ��¥
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

	private String[] mStrings = {"�б� ��������","�б� ��������","�б� ��������","�б� ��������","�б� ��������","�б� ��������","�б� ��������","�б� ��������","�б� ��������","�б� ��������","�б� ��������","�б� ��������","�б� ��������"};
	
		
	
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
