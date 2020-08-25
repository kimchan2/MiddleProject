package kr.or.ddit.dao;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ShoppingCartVO;

public interface IShoppingCart {

	public ShoppingCartVO selectShoppingCartVO(int sc_index);
	
	public List<ShoppingCartVO> getShoppingCartVO(String mem_mail);
	
	public int insertShoppingCartVO(ShoppingCartVO vo);
	
	public int deleteShoppingCartVO(int sc_index);
	
	public int updateShoppingCartVO(ShoppingCartVO vo);
	
}
