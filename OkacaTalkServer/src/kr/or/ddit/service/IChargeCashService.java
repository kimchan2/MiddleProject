package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ChargeCashVO;

public interface IChargeCashService extends Remote  {

	public List<ChargeCashVO> selectChargeCash(String mem_mail) throws RemoteException;
	
	public List<ChargeCashVO> getChargeCash(String mem_mail) throws RemoteException;
	
	public int insertChargeCash(ChargeCashVO ccvo) throws RemoteException;
	
	public int deleteChargeCash(String mem_mail) throws RemoteException;
	
	public int updateChargeCash(ChargeCashVO vo) throws RemoteException;
	
}
