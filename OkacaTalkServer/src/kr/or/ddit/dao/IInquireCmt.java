package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.InquireCmtVO;

public interface IInquireCmt {

	public InquireCmtVO selectInquireCmtVO(int iqc_index, int iq_idex);
	
	public List<InquireCmtVO> getInquireCmtVO(int iq_idex);
	
	public int insertInquireCmtVO(InquireCmtVO vo);
	
	public int deleteInquireCmtVO(int iqc_index, int iq_idex);
	
	public int updateInquireCmtVO(InquireCmtVO vo);
	
}
