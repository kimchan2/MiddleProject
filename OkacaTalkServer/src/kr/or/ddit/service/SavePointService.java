package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.ReviewImpl;
import kr.or.ddit.dao.SavePointImpl;
import kr.or.ddit.vo.SavePointVO;

public class SavePointService extends UnicastRemoteObject implements ISavePointService {

	SavePointImpl spDao; 
	private static SavePointService service; 
	
	private SavePointService() throws RemoteException{
		spDao = SavePointImpl.getInstance();
	}
	
	public static SavePointService getInstance() throws RemoteException {
		if(service== null) {
			service = new SavePointService();
		}
		return service;
	}
	
	@Override
	public SavePointVO selectSavePointVO(String mem_mail, int o_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SavePointVO> getSavePointVO(String mem_mail) {
		// TODO Auto-generated method stub
		return spDao.getSavePointVO(mem_mail);
	}

	@Override
	public int insertSavePointVO(SavePointVO vo) {
		return spDao.insertSavePointVO(vo);
	}

	@Override
	public int deleteSavePointVO(String mem_mail, int o_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSavePointVO(SavePointVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
