package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.NoticeCmtVO;

public interface INoticeCmt {

	public NoticeCmtVO selectNoticeCmtVO(int ntc_index);
	
	public List<NoticeCmtVO> getNoticeCmtVO();
	
	public int insertNoticeCmtVO(NoticeCmtVO vo);
	
	public int deleteNoticeCmtVO(int ntc_index);
	
	public int updateNoticeCmtVO(NoticeCmtVO vo);
	
}
