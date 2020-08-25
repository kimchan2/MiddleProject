package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.NoticeImpl;
import kr.or.ddit.dao.OrderDetailImpl;
import kr.or.ddit.vo.OrderDetailVO;

public class OrderDetailService  extends UnicastRemoteObject implements IOrderDetailService {

	OrderDetailImpl odDao; 
	private static OrderDetailService service; 
	
	private OrderDetailService() throws RemoteException{
		odDao = OrderDetailImpl.getInstance();
	}
	
	public static OrderDetailService getInstance() throws RemoteException {
		if(service== null) {
			service = new OrderDetailService();
		}
		return service;
	}
	
	@Override
	public OrderDetailVO selectOrderDetailVO(int o_index, int od_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetailVO> getOrderDetailVO(int o_index) {
		// TODO Auto-generated method stub
		return odDao.getOrderDetailVO(o_index);
	}

	@Override
	public Object insertOrderDetailVO(OrderDetailVO vo) {
		return odDao.insertOrderDetailVO(vo);
	}

	@Override
	public int deleteOrderDetailVO(int o_index, int od_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOrderDetailVO(OrderDetailVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
