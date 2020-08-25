package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.UsingCashImpl;
import kr.or.ddit.dao.UsingPointImpl;
import kr.or.ddit.vo.UsingPointVO;

public class UsingPointService extends UnicastRemoteObject implements IUsingPointService {

	UsingPointImpl upDao; 
	private static UsingPointService service; 
	
	private UsingPointService() throws RemoteException{
		upDao = UsingPointImpl.getInstance();
	}
	
	public static UsingPointService getInstance() throws RemoteException {
		if(service== null) {
			service = new UsingPointService();
		}
		return service;
	}
	
	@Override
	public UsingPointVO selectUsingPointVO(int o_index, String mem_mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsingPointVO> getUsingPointVO(String mem_mail) {
		// TODO Auto-generated method stub
		return upDao.getUsingPointVO(mem_mail);
	}

	@Override
	public int insertUsingPointVO(UsingPointVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUsingPointVO(int o_index, String mem_mail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUsingPointList(UsingPointVO vo) throws RemoteException {
		return upDao.updateUsingPointList(vo);
	}

}
