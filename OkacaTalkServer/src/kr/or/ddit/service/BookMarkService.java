package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.BlockFriendImpl;
import kr.or.ddit.dao.BookMarkImpl;
import kr.or.ddit.vo.BookMarkVO;

public class BookMarkService extends UnicastRemoteObject implements IBookMarkService {

	BookMarkImpl bmDao; 
	private static BookMarkService service; 
	
	private BookMarkService() throws RemoteException{
		bmDao = BookMarkImpl.getInstance();
	}
	
	public static BookMarkService getInstance() throws RemoteException {
		if(service== null) {
			service = new BookMarkService();
		}
		return service;
	}
	
	@Override
	public BookMarkVO selectBF(BookMarkVO vo) {
		return bmDao.selectBF(vo);
	}

	@Override
	public List<BookMarkVO> getBF(String mem_mail) {
		return bmDao.getBF(mem_mail);
	}

	@Override
	public Object insertBF(BookMarkVO vo) {
		return bmDao.insertBF(vo);
	}

	@Override
	public int deleteBF(BookMarkVO vo) {
		return bmDao.deleteBF(vo);
	}

}
