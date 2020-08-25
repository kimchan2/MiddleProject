package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.BookMarkVO;

public interface IBookMark {

	public BookMarkVO selectBF(BookMarkVO vo);
	
	public List<BookMarkVO> getBF(String mem_mail);
	
	public Object insertBF(BookMarkVO vo);
	
	public int deleteBF(BookMarkVO vo);
	
//	public int updateBF(BookMarkVO vo);
	
}
