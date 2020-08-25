package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.UsingCashVO;

public interface IUsingCashService extends Remote {

	public UsingCashVO selectUsingCashVO(int o_index, String mem_mail) throws RemoteException;
	
	public List<UsingCashVO> getUsingCashVO(String mem_mail) throws RemoteException;
	
	public int insertUsingCashVO(UsingCashVO vo) throws RemoteException;
	
	public int deleteUsingCashVO(int o_index, String mem_mail) throws RemoteException;
	
//	public int updateUsingCashVO(UsingCashVO vo);
	
	public Object insertUsingCashListVO(UsingCashVO vo) throws RemoteException;
	
}
