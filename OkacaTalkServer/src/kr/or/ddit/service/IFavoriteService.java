package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.FavoriteListVO;
import kr.or.ddit.vo.FavoriteVO;

public interface IFavoriteService extends Remote {

	public FavoriteVO selectFavoriteVO(int pl_index, String mem_mail) throws RemoteException;
	
	public List<FavoriteListVO> getFavoriteVO(String mem_mail) throws RemoteException;
	
	public int insertFavoriteVO(FavoriteVO vo) throws RemoteException;
	
	public int deleteFavoriteVO(FavoriteVO vo) throws RemoteException;
	
	public int updateFavoriteVO(FavoriteVO vo) throws RemoteException;
	
}
