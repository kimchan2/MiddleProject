package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.OrderDetailListImpl;
import kr.or.ddit.vo.OrderDetailListVO;

public class OrderDetailListService extends UnicastRemoteObject implements IOrderDetailListService{

	OrderDetailListImpl odlDao; 
	private static OrderDetailListService service; 
	
	private OrderDetailListService() throws RemoteException{
		odlDao = OrderDetailListImpl.getInstance();
	}
	
	public static OrderDetailListService getInstance() throws RemoteException {
		if(service== null) {
			service = new OrderDetailListService();
		}
		return service;
	}
	
	@Override
	public List<OrderDetailListVO> selectOrderDetailList(String mem_mail) throws RemoteException {
		return odlDao.selectOrderDetailList(mem_mail);
	}

}
