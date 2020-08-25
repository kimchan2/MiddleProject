package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.NoticeVO;

public interface INotice {

	public NoticeVO selectNoticeVO(int ntc_index);
	
	public List<NoticeVO> getNoticeVO();
	
	public int insertNoticeVO(NoticeVO vo);
	
	public int deleteNoticeVO(int ntc_index);
	
	public int updateNoticeVO(NoticeVO vo);
	
}
