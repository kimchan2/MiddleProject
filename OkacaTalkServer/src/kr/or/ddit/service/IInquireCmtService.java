package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.InquireCmtVO;

public interface IInquireCmtService extends Remote {

	public InquireCmtVO selectInquireCmtVO(int iqc_index, int iq_idex) throws RemoteException;
	
	public List<InquireCmtVO> getInquireCmtVO(int iq_idex) throws RemoteException;
	
	public int insertInquireCmtVO(InquireCmtVO vo) throws RemoteException;
	
	public int deleteInquireCmtVO(int iqc_index, int iq_idex) throws RemoteException;
	
	public int updateInquireCmtVO(InquireCmtVO vo) throws RemoteException;
	
}
