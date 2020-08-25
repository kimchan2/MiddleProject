package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.CategoryListImpl;
import kr.or.ddit.dao.ChargeCashImpl;
import kr.or.ddit.vo.ChargeCashVO;

public class ChargeCashService extends UnicastRemoteObject implements IChargeCashService {

	ChargeCashImpl ccDao; 
	private static ChargeCashService service; 
	
	private ChargeCashService() throws RemoteException{
		ccDao = ChargeCashImpl.getInstance();
	}
	
	public static ChargeCashService getInstance() throws RemoteException {
		if(service== null) {
			service = new ChargeCashService();
		}
		return service;
	}
	
	@Override
	public List<ChargeCashVO> selectChargeCash(String mem_mail)throws RemoteException {
		// TODO Auto-generated method stub
		return ccDao.selectChargeCash(mem_mail);
	}

	@Override
	public List<ChargeCashVO> getChargeCash(String mem_mail) throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertChargeCash(ChargeCashVO ccvo)throws RemoteException {
		// TODO Auto-generated method stub
		return ccDao.insertChargeCash(ccvo);
	}

	@Override
	public int deleteChargeCash(String mem_mail) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateChargeCash(ChargeCashVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
