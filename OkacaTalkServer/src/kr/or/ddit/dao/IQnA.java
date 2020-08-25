package kr.or.ddit.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.QnAVO;

public interface IQnA {

	public QnAVO selectQnAVO(int qa_index);
	
	public List<QnAVO> getQnAVO(HashMap<String, Object> map);
	
	public List<QnAVO> getQnaVOAll(int cnt);
	
	public int pageCount(String mem_mail);
	
	public int adminPageCount();
	
	public int insertQnAVO(QnAVO vo);
	
	public int deleteQnAVO(int qa_index);
	
	public int updateQnAVO(QnAVO vo);
	
}
