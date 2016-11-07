package univ.donga.dw.info.job.engineering;

public abstract class EngineeringCollege {
	//�� ���� �̸��� �Խ����� �����ϴ� url�� ����ȴ�.
	private String name;
	private String url;
	//�ٿ�޴� �����尡 ����ȴ�.
	private Thread downThread;
	public EngineeringCollege(String name, String url)
	{
		this.name = name;
		this.url = url;
	}
	//���ο� �Խñ��� �ö�ð�� ������ �޼ҵ�(�����ǵǼ� ���ȴ�)
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
