package univ.donga.dw.info.job;

public class JobBoardInfo {
	
	private int number; // 글번호
	private String title; // 제목
	private String date; // 글올린날짜
	private String url; // 해당글 url
	
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
