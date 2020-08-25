package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.OrderDetailListVO;

public interface IOrderDetailListService extends Remote {
	
	public List<OrderDetailListVO> selectOrderDetailList(String mem_mail) throws RemoteException;
}
