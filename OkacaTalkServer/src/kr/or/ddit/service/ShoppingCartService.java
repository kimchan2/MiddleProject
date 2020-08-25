package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.SavePointImpl;
import kr.or.ddit.dao.ShoppingCartImpl;
import kr.or.ddit.vo.ShoppingCartVO;

public class ShoppingCartService extends UnicastRemoteObject implements IShoppingCartService {

	ShoppingCartImpl scDao; 
	private static ShoppingCartService service; 
	
	private ShoppingCartService() throws RemoteException{
		scDao = ShoppingCartImpl.getInstance();
	}
	
	public static ShoppingCartService getInstance() throws RemoteException {
		if(service== null) {
			service = new ShoppingCartService();
		}
		return service;
	}
	
	@Override
	public ShoppingCartVO selectShoppingCartVO(int sc_index) {
		// TODO Auto-generated method stub
		return scDao.selectShoppingCartVO(sc_index);
	}

	@Override
	public List<ShoppingCartVO> getShoppingCartVO(String mem_mail) {
		// TODO Auto-generated method stub
		return scDao.getShoppingCartVO(mem_mail);
	}

	@Override
	public int insertShoppingCartVO(ShoppingCartVO vo) {
		return scDao.insertShoppingCartVO(vo);
	}

	@Override
	public int deleteShoppingCartVO(int sc_index) {
		// TODO Auto-generated method stub
		return scDao.deleteShoppingCartVO(sc_index);
	}

	@Override
	public int updateShoppingCartVO(ShoppingCartVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
