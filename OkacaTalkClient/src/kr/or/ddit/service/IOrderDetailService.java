package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.OrderDetailVO;

public interface IOrderDetailService extends Remote {

	public OrderDetailVO selectOrderDetailVO(int o_index, int od_index) throws RemoteException;
	
	public List<OrderDetailVO> getOrderDetailVO(int o_index) throws RemoteException;
	
	public Object insertOrderDetailVO(OrderDetailVO vo) throws RemoteException;
	
	public int deleteOrderDetailVO(int o_index, int od_index) throws RemoteException;
	
	public int updateOrderDetailVO(OrderDetailVO vo) throws RemoteException;
	
}
