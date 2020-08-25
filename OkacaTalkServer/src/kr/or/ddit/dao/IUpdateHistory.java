package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.UpdateHistoryVO;

public interface IUpdateHistory {

	public UpdateHistoryVO selectUpdateHistoryVO(int uh_index);
	
	public List<UpdateHistoryVO> getUpdateHistoryVO();
	
	public int insertUpdateHistoryVO(UpdateHistoryVO vo);
	
	public int deleteUpdateHistoryVO(int uh_index);
	
	public int updateUpdateHistoryVO(UpdateHistoryVO vo);
	
}
