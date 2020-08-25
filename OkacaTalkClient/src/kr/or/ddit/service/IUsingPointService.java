package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.UsingPointVO;

public interface IUsingPointService extends Remote {

	public UsingPointVO selectUsingPointVO(int o_index, String mem_mail) throws RemoteException;
	
	public List<UsingPointVO> getUsingPointVO(String mem_mail) throws RemoteException;
	
	public int insertUsingPointVO(UsingPointVO vo) throws RemoteException;
	
	public int deleteUsingPointVO(int o_index, String mem_mail) throws RemoteException;
	
//	public int updateUsingPointVO(UsingPointVO vo) throws RemoteException;
	
	public int updateUsingPointList(UsingPointVO vo) throws RemoteException;
	
}
