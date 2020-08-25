package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.ConfigurationImpl;
import kr.or.ddit.dao.FAQImpl;
import kr.or.ddit.vo.FAQVO;

public class FAQService extends UnicastRemoteObject implements IFAQService {

	FAQImpl faqDao; 
	private static FAQService service; 
	
	private FAQService() throws RemoteException{
		faqDao = FAQImpl.getInstance();
	}
	
	public static FAQService getInstance() throws RemoteException {
		if(service== null) {
			service = new FAQService();
		}
		return service;
	}
	
	@Override
	public FAQVO selectFAQVO(int faq_index) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FAQVO> getFAQVO() throws RemoteException {
		// TODO Auto-generated method stub
		return faqDao.getFAQVO();
	}

	@Override
	public int insertFAQVO(FAQVO vo) throws RemoteException  {
		// TODO Auto-generated method stub
		return faqDao.insertFAQVO(vo);
	}

	@Override
	public int deleteFAQVO(int faq_index) throws RemoteException  {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateFAQVO(FAQVO vo) throws RemoteException  {
		// TODO Auto-generated method stub
		return 0;
	}

}
