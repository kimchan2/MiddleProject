package kr.or.ddit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.InquireJoinVO;
import kr.or.ddit.vo.InquireVO;

public interface IInquire {

	public InquireVO selectInquireVO(int iqcindex, int pl_idex);
	
	public List<InquireJoinVO> selectInquireVO2(String mem_mail);
	
	public List<InquireVO> getInquireVO(int pl_idex);
	
	public int insertInquireVO(InquireVO vo);
	
	public int deleteInquireVO(int iq_index);
	
	public int updateInquireVO(InquireVO vo);
	
}
