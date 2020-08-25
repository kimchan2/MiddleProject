package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.QnACmtVO;

public interface IQnACmtService extends Remote {

	public QnACmtVO selectQnACmtVO(int qa_index) throws RemoteException;
	
	public List<QnACmtVO> getQnACmtVO(String mem_mail) throws RemoteException;
	
	public int insertQnACmtVO(QnACmtVO vo) throws RemoteException;
	
	public int deleteQnACmtVO(int qac_index) throws RemoteException;
	
	public int updateQnACmtVO(QnACmtVO vo) throws RemoteException;
	
}
