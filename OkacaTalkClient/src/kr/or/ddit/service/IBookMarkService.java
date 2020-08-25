package kr.or.ddit.service;

import java.rmi.Remote;
import java.util.List;

import kr.or.ddit.vo.BookMarkVO;

public interface IBookMarkService extends Remote {

	public BookMarkVO selectBF(BookMarkVO vo);
	
	public List<BookMarkVO> getBF(String mem_mail);
	
	public Object insertBF(BookMarkVO vo);
	
	public int deleteBF(BookMarkVO vo);
	
//	public int updateBF(BookMarkVO vo);
	
}
