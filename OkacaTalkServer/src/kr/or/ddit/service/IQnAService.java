package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.QnAVO;

public interface IQnAService extends Remote {

	public QnAVO selectQnAVO(int qa_index) throws RemoteException;
	
	public List<QnAVO> getQnAVO(HashMap<String, Object> map) throws RemoteException;
	
	public List<QnAVO> getQnaVOAll(int cnt) throws RemoteException;
	
	public int pageCount(String mem_mail) throws RemoteException;
	
	public int adminPageCount() throws RemoteException;
	
	public int insertQnAVO(QnAVO vo) throws RemoteException;
	
	public int deleteQnAVO(int qa_index) throws RemoteException;
	
	public int updateQnAVO(QnAVO vo) throws RemoteException;
	
}
