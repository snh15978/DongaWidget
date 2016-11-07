package univ.donga.dw.info.job;

import java.util.ArrayList;

import univ.donga.dw.info.job.engineering.Archi;
import univ.donga.dw.info.job.engineering.Civil;
import univ.donga.dw.info.job.engineering.Computer;
import univ.donga.dw.info.job.engineering.Dache;
import univ.donga.dw.info.job.engineering.Dsso;
import univ.donga.dw.info.job.engineering.Ee;
import univ.donga.dw.info.job.engineering.Electrical;
import univ.donga.dw.info.job.engineering.EngineeringCollege;
import univ.donga.dw.info.job.engineering.Envu;
import univ.donga.dw.info.job.engineering.Ie;
import univ.donga.dw.info.job.engineering.La;
import univ.donga.dw.info.job.engineering.Mech;
import univ.donga.dw.info.job.engineering.Metal;
import univ.donga.dw.info.job.engineering.Omp;
import univ.donga.dw.info.job.engineering.Urban;

public class CollegeManager {
	//�ܰ��뺰 ����Ʈ
	private ArrayList<EngineeringCollege> engineeringCollegeList;
	
	public CollegeManager()
	{
		engineeringCollegeList = new ArrayList<EngineeringCollege>();
		
		//�������� 14�� �кε� ����
		engineeringCollegeList.add(new Archi("�����а�", ""));
		engineeringCollegeList.add(new Archi("���������а�", ""));
		engineeringCollegeList.add(new Archi("���������а�", ""));
		engineeringCollegeList.add(new Archi("�濵�����а�", ""));
		engineeringCollegeList.add(new Archi("�����а�", ""));
		engineeringCollegeList.add(new Archi("�����а�", ""));
		engineeringCollegeList.add(new Archi("���ð�ȹ�а�", ""));
		engineeringCollegeList.add(new Archi("��ȸ�����а�", ""));
		engineeringCollegeList.add(new Archi("����濵���а�", ""));
		engineeringCollegeList.add(new Archi("������а�", ""));
		engineeringCollegeList.add(new Archi("�ż�����а�", ""));
		engineeringCollegeList.add(new Archi("����������ڰ��а�", ""));
		engineeringCollegeList.add(new Archi("������а�", ""));
		engineeringCollegeList.add(new Archi("�Ͼ��Ϲ��а�", ""));
		engineeringCollegeList.add(new Archi("�߾� �߹� �а�", ""));
		engineeringCollegeList.add(new Archi("������а�", ""));
		engineeringCollegeList.add(new Archi("���ڰ��а�", ""));
		engineeringCollegeList.add(new Archi("��ǻ�Ͱ��а�", ""));
		engineeringCollegeList.add(new Archi("�мǵ������а�", ""));
		engineeringCollegeList.add(new Archi("��������ȭ�а�", ""));
		engineeringCollegeList.add(new Archi("ȭ�а��а�", ""));
		engineeringCollegeList.add(new Archi("ȯ����а�", ""));		
		
		
	}
	public boolean addengineeringCollegeList(EngineeringCollege object) {
		return engineeringCollegeList.add(object);
	}

	public void clearengineeringCollegeList() {
		engineeringCollegeList.clear();
	}

	public EngineeringCollege getengineeringCollegeList(int index) {
		return engineeringCollegeList.get(index);
	}

	public void removeengineeringCollegeList(int index) {
		engineeringCollegeList.remove(index);
	}
	
	public String[] getEngineeringCollegeNames()
	{
		String[] buf = new String[engineeringCollegeList.size()];
		for(int i = 0; i<engineeringCollegeList.size(); i++)
			buf[i] = new String(engineeringCollegeList.get(i).getName());
		return buf;
	}
	
}
