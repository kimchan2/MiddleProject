package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.FAQImpl;
import kr.or.ddit.dao.FavoriteImpl;
import kr.or.ddit.vo.FavoriteListVO;
import kr.or.ddit.vo.FavoriteVO;

public class FavoriteService extends UnicastRemoteObject implements IFavoriteService {

	FavoriteImpl fsDao; 
	private static FavoriteService service; 
	
	private FavoriteService() throws RemoteException{
		fsDao = FavoriteImpl.getInstance();
	}
	
	public static FavoriteService getInstance() throws RemoteException {
		if(service== null) {
			service = new FavoriteService();
		}
		return service;
	}
	
	@Override
	public FavoriteVO selectFavoriteVO(int pl_index, String mem_mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FavoriteListVO> getFavoriteVO(String mem_mail) {
		return fsDao.getFavoriteVO(mem_mail);
	}

	@Override
	public int insertFavoriteVO(FavoriteVO vo) {
		return fsDao.insertFavoriteVO(vo);
	}

	@Override
	public int deleteFavoriteVO(FavoriteVO vo) {
		// TODO Auto-generated method stub
		return fsDao.deleteFavoriteVO(vo);
	}

	@Override
	public int updateFavoriteVO(FavoriteVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
