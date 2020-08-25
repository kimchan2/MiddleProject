package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ShoppingCartVO;

public interface IShoppingCartService extends Remote {

	public ShoppingCartVO selectShoppingCartVO(int sc_index) throws RemoteException;
	
	public List<ShoppingCartVO> getShoppingCartVO(String mem_mail) throws RemoteException;
	
	public int insertShoppingCartVO(ShoppingCartVO vo) throws RemoteException;
	
	public int deleteShoppingCartVO(int sc_index) throws RemoteException;
	
	public int updateShoppingCartVO(ShoppingCartVO vo) throws RemoteException;
	
}
