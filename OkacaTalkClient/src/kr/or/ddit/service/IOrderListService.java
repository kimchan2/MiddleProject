package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.OrderListVO;

public interface IOrderListService extends Remote {

	public OrderListVO selectOrderListVO(int o_index) throws RemoteException;
	
	public List<OrderListVO> getOrderListVO(int o_index) throws RemoteException;
	
	public int insertOrderListVO(OrderListVO vo) throws RemoteException;
	
	public int deleteOrderListVO(int o_index) throws RemoteException;
	
	public int updateOrderListVO(OrderListVO vo) throws RemoteException;
	
	public List<OrderListVO> SelectOrderList(String mem_mail) throws RemoteException;
}
