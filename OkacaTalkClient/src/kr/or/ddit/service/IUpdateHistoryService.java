package kr.or.ddit.service;

import java.rmi.Remote;
import java.util.List;

import kr.or.ddit.vo.UpdateHistoryVO;

public interface IUpdateHistoryService extends Remote {

	public UpdateHistoryVO selectUpdateHistoryVO(int uh_index);
	
	public List<UpdateHistoryVO> getUpdateHistoryVO();
	
	public int insertUpdateHistoryVO(UpdateHistoryVO vo);
	
	public int deleteUpdateHistoryVO(int uh_index);
	
	public int updateUpdateHistoryVO(UpdateHistoryVO vo);
	
}
