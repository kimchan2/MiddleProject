package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.UpdateHistoryVO;

public interface IUpdateHistoryService extends Remote {

	public UpdateHistoryVO selectUpdateHistoryVO(int uh_index) throws RemoteException;
	
	public List<UpdateHistoryVO> getUpdateHistoryVO() throws RemoteException;
	
	public int insertUpdateHistoryVO(UpdateHistoryVO vo) throws RemoteException;
	
	public int deleteUpdateHistoryVO(int uh_index) throws RemoteException;
	
	public int updateUpdateHistoryVO(UpdateHistoryVO vo) throws RemoteException;
	
}
