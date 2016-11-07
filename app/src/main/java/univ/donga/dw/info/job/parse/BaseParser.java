package univ.donga.dw.info.job.parse;

import java.util.ArrayList;

import univ.donga.dw.info.job.JobBoardInfo;


public abstract class BaseParser {
	
	
	public abstract ArrayList<JobBoardInfo> getList(int pageNumber);
	public abstract int getLastPageNumber();
	
}
