package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dao.InquireCmtImpl;
import kr.or.ddit.dao.InquireImpl;
import kr.or.ddit.vo.InquireJoinVO;
import kr.or.ddit.vo.InquireVO;

public class InquireService extends UnicastRemoteObject implements IInquireService {

	InquireImpl iqDao; 
	private static InquireService service; 
	
	private InquireService() throws RemoteException{
		iqDao = InquireImpl.getInstance();
	}
	
	public static InquireService getInstance() throws RemoteException {
		if(service== null) {
			service = new InquireService();
		}
		return service;
	}
	
	@Override
	public InquireVO selectInquireVO(int iqcindex, int pl_idex)throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InquireVO> getInquireVO(int pl_idex)throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertInquireVO(InquireVO vo) throws RemoteException{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteInquireVO(int iq_index)throws RemoteException {
		// TODO Auto-generated method stub
		return iqDao.deleteInquireVO(iq_index);
	}

	@Override
	public int updateInquireVO(InquireVO vo)throws RemoteException {
		// TODO Auto-generated method stub
		return iqDao.updateInquireVO(vo);
	}

	@Override
	public List<InquireJoinVO> selectInquireVO2(String mem_mail) throws RemoteException {
		// TODO Auto-generated method stub
		return iqDao.selectInquireVO2(mem_mail);
	}

}
