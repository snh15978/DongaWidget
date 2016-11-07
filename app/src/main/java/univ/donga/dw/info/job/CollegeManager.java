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
	//단과대별 리스트
	private ArrayList<EngineeringCollege> engineeringCollegeList;
	
	public CollegeManager()
	{
		engineeringCollegeList = new ArrayList<EngineeringCollege>();
		
		//공과대학 14개 학부들 생성
		engineeringCollegeList.add(new Archi("건축학과", ""));
		engineeringCollegeList.add(new Archi("국제관광학과", ""));
		engineeringCollegeList.add(new Archi("국제무역학과", ""));
		engineeringCollegeList.add(new Archi("경영정보학과", ""));
		engineeringCollegeList.add(new Archi("금융학과", ""));
		engineeringCollegeList.add(new Archi("기계공학과", ""));
		engineeringCollegeList.add(new Archi("도시계획학과", ""));
		engineeringCollegeList.add(new Archi("사회복지학과", ""));
		engineeringCollegeList.add(new Archi("산업경영공학과", ""));
		engineeringCollegeList.add(new Archi("생명공학과", ""));
		engineeringCollegeList.add(new Archi("신소재공학과", ""));
		engineeringCollegeList.add(new Archi("유기재료고분자공학과", ""));
		engineeringCollegeList.add(new Archi("영어영문학과", ""));
		engineeringCollegeList.add(new Archi("일어일문학과", ""));
		engineeringCollegeList.add(new Archi("중어 중문 학과", ""));
		engineeringCollegeList.add(new Archi("전기공학과", ""));
		engineeringCollegeList.add(new Archi("전자공학과", ""));
		engineeringCollegeList.add(new Archi("컴퓨터공학과", ""));
		engineeringCollegeList.add(new Archi("패션디자인학과", ""));
		engineeringCollegeList.add(new Archi("프랑스문화학과", ""));
		engineeringCollegeList.add(new Archi("화학공학과", ""));
		engineeringCollegeList.add(new Archi("환경공학과", ""));		
		
		
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
