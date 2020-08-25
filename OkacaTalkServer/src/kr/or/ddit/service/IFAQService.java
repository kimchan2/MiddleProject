package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.FAQVO;

public interface IFAQService extends Remote {

	public FAQVO selectFAQVO(int faq_index) throws RemoteException;
	
	public List<FAQVO> getFAQVO() throws RemoteException;
	
	public int insertFAQVO(FAQVO vo) throws RemoteException;
	
	public int deleteFAQVO(int faq_index) throws RemoteException;
	
	public int updateFAQVO(FAQVO vo) throws RemoteException;
	
}
