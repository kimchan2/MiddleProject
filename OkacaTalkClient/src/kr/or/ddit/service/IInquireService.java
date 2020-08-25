package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.InquireJoinVO;
import kr.or.ddit.vo.InquireVO;

public interface IInquireService extends Remote {

	public InquireVO selectInquireVO(int iqcindex, int pl_idex) throws RemoteException;
	
	public List<InquireVO> getInquireVO(int pl_idex) throws RemoteException;
	
	public int insertInquireVO(InquireVO vo) throws RemoteException;
	
	public int deleteInquireVO(int iq_index) throws RemoteException;
	
	public int updateInquireVO(InquireVO vo) throws RemoteException;
	
	public List<InquireJoinVO> selectInquireVO2(String mem_mail) throws RemoteException;
	
}
