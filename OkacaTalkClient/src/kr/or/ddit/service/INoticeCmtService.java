package kr.or.ddit.service;

import java.rmi.Remote;
import java.util.List;

import kr.or.ddit.vo.NoticeCmtVO;

public interface INoticeCmtService extends Remote {

	public NoticeCmtVO selectNoticeCmtVO(int ntc_index);
	
	public List<NoticeCmtVO> getNoticeCmtVO();
	
	public int insertNoticeCmtVO(NoticeCmtVO vo);
	
	public int deleteNoticeCmtVO(int ntc_index);
	
	public int updateNoticeCmtVO(NoticeCmtVO vo);
	
}
