package univ.donga.dw.info.job;

public class JobBoardInfo {
	
	private int number; // �۹�ȣ
	private String title; // ����
	private String date; // �ۿø���¥
	private String url; // �ش�� url
	
	public JobBoardInfo(int number, String title, String date, String url) {
		// TODO Auto-generated constructor stub
		this.number = number;
		this.title = title;
		this.date = date;
	}

	

	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
	

}
