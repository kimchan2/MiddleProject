package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.BookMarkVO;

public interface IBookMarkService extends Remote {

	public BookMarkVO selectBF(BookMarkVO vo) throws RemoteException;
	
	public List<BookMarkVO> getBF(String mem_mail) throws RemoteException;
	
	public Object insertBF(BookMarkVO vo) throws RemoteException;
	
	public int deleteBF(BookMarkVO vo) throws RemoteException;
	
//	public int updateBF(BookMarkVO vo);
	
}
