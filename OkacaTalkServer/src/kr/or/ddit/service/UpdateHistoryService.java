package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.ShoppingCartImpl;
import kr.or.ddit.dao.UpdateHistoryImpl;
import kr.or.ddit.vo.UpdateHistoryVO;

public class UpdateHistoryService extends UnicastRemoteObject implements IUpdateHistoryService {

	UpdateHistoryImpl uhDao; 
	private static UpdateHistoryService service; 
	
	private UpdateHistoryService() throws RemoteException{
		uhDao = UpdateHistoryImpl.getInstance();
	}
	
	public static UpdateHistoryService getInstance() throws RemoteException {
		if(service== null) {
			service = new UpdateHistoryService();
		}
		return service;
	}
	
	@Override
	public UpdateHistoryVO selectUpdateHistoryVO(int uh_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpdateHistoryVO> getUpdateHistoryVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertUpdateHistoryVO(UpdateHistoryVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUpdateHistoryVO(int uh_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUpdateHistoryVO(UpdateHistoryVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
