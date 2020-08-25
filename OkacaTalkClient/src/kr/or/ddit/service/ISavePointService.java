package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.SavePointVO;

public interface ISavePointService extends Remote {

	public SavePointVO selectSavePointVO(String mem_mail, int o_index)throws RemoteException;
	
	public List<SavePointVO> getSavePointVO(String mem_mail) throws RemoteException;
	
	public int insertSavePointVO(SavePointVO vo) throws RemoteException;
	
	public int deleteSavePointVO(String mem_mail, int o_index) throws RemoteException;
	
	public int updateSavePointVO(SavePointVO vo) throws RemoteException;
	
}
