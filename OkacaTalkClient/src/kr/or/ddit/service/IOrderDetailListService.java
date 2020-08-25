package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.OrderDetailListVO;
import kr.or.ddit.vo.OrderDetailVO;
import kr.or.ddit.vo.OrderListVO;

public interface IOrderDetailListService extends Remote {
	
	public List<OrderDetailListVO> selectOrderDetailList(String mem_mail) throws RemoteException;
	
	public int insertOrderDetail(OrderDetailVO vo) throws RemoteException;
}
