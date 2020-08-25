package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.QnACmtVO;

public interface IQnACmt {

	public QnACmtVO selectQnACmtVO(int qa_index);
	
	public List<QnACmtVO> getQnACmtVO(String mem_mail);
	
	public int insertQnACmtVO(QnACmtVO vo);
	
	public int deleteQnACmtVO(int qac_index);
	
	public int updateQnACmtVO(QnACmtVO vo);
	
}
