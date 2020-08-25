package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.OrderDetailImpl;
import kr.or.ddit.dao.OrderListImpl;
import kr.or.ddit.vo.OrderListVO;

public class OrderListService extends UnicastRemoteObject implements IOrderListService {

	OrderListImpl olDao; 
	private static OrderListService service; 
	
	private OrderListService() throws RemoteException{
		olDao = OrderListImpl.getInstance();
	}
	
	public static OrderListService getInstance() throws RemoteException {
		if(service== null) {
			service = new OrderListService();
		}
		return service;
	}
	
	@Override
	public OrderListVO selectOrderListVO(int o_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderListVO> getOrderListVO(int o_index) {
		// TODO Auto-generated method stub
		return olDao.getOrderListVO(o_index);
	}

	@Override
	public int insertOrderListVO(OrderListVO vo) {
		return olDao.insertOrderListVO(vo);
	}

	@Override
	public int deleteOrderListVO(int o_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOrderListVO(OrderListVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderListVO> SelectOrderList(String mem_mail) throws RemoteException {
		return olDao.SelectOrderList(mem_mail);
	}

}
