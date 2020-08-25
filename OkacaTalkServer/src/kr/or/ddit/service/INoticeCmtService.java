package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.NoticeCmtVO;

public interface INoticeCmtService extends Remote {

	public NoticeCmtVO selectNoticeCmtVO(int ntc_index) throws RemoteException;
	
	public List<NoticeCmtVO> getNoticeCmtVO() throws RemoteException;
	
	public int insertNoticeCmtVO(NoticeCmtVO vo) throws RemoteException;
	
	public int deleteNoticeCmtVO(int ntc_index) throws RemoteException;
	
	public int updateNoticeCmtVO(NoticeCmtVO vo) throws RemoteException;
	
}
