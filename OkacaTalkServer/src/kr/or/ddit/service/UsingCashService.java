package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.UpdateHistoryImpl;
import kr.or.ddit.dao.UsingCashImpl;
import kr.or.ddit.vo.UsingCashVO;

public class UsingCashService extends UnicastRemoteObject implements IUsingCashService {

	UsingCashImpl ucDao; 
	private static UsingCashService service; 
	
	private UsingCashService() throws RemoteException{
		ucDao = UsingCashImpl.getInstance();
	}
	
	public static UsingCashService getInstance() throws RemoteException {
		if(service== null) {
			service = new UsingCashService();
		}
		return service;
	}
	
	@Override
	public UsingCashVO selectUsingCashVO(int o_index, String mem_mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsingCashVO> getUsingCashVO(String mem_mail) {
		return ucDao.getUsingCashVO(mem_mail);
	}

	@Override
	public int insertUsingCashVO(UsingCashVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUsingCashVO(int o_index, String mem_mail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object insertUsingCashListVO(UsingCashVO vo) throws RemoteException {
		return ucDao.insertUsingCashListVO(vo);
	}

}
