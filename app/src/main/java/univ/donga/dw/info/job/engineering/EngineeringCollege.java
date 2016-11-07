package univ.donga.dw.info.job.engineering;

public abstract class EngineeringCollege {
	//각 과별 이름과 게시판이 존재하는 url이 저장된다.
	private String name;
	private String url;
	//다운받는 쓰레드가 저장된다.
	private Thread downThread;
	public EngineeringCollege(String name, String url)
	{
		this.name = name;
		this.url = url;
	}
	//새로운 게시글이 올라올경우 조사할 메소드(재정의되서 사용된다)
	public abstract void downNewMessage();
	public String getName() {
		return name;
	}
	public String getUrl() {
		return url;
	}
	public Thread getDownThread() {
		return downThread;
	}
	public void setDownThread(Thread downThread) {
		this.downThread = downThread;
	}
}
