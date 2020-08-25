package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.HideFriendImpl;
import kr.or.ddit.dao.InquireCmtImpl;
import kr.or.ddit.vo.InquireCmtVO;

public class InquireCmtService extends UnicastRemoteObject implements IInquireCmtService {

	InquireCmtImpl iqcDao; 
	private static InquireCmtService service; 
	
	private InquireCmtService() throws RemoteException{
		iqcDao = InquireCmtImpl.getInstance();
	}
	
	public static InquireCmtService getInstance() throws RemoteException {
		if(service== null) {
			service = new InquireCmtService();
		}
		return service;
	}
	
	@Override
	public InquireCmtVO selectInquireCmtVO(int iqc_index, int iq_idex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InquireCmtVO> getInquireCmtVO(int iq_idex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertInquireCmtVO(InquireCmtVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteInquireCmtVO(int iqc_index, int iq_idex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateInquireCmtVO(InquireCmtVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
