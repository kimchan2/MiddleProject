package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.dao.OrderListImpl;
import kr.or.ddit.dao.ParticipantImpl;
import kr.or.ddit.vo.ParticipantVO;

public class ParticipantService extends UnicastRemoteObject implements IParticipantService {

	ParticipantImpl pcDao; 
	private static ParticipantService service; 
	
	private ParticipantService() throws RemoteException{
		pcDao = ParticipantImpl.getInstance();
	}
	
	public static ParticipantService getInstance() throws RemoteException {
		if(service== null) {
			service = new ParticipantService();
		}
		return service;
	}
	
	@Override
	public List<ParticipantVO> getParticipantVO(int cr_index) {
		// TODO Auto-generated method stub
		return pcDao.getParticipantVO(cr_index);
	}

	@Override
	public void insertParticipantVO(ParticipantVO vo) {
		// TODO Auto-generated method stub
		pcDao.insertParticipantVO(vo);
	}

	@Override
	public int deleteParticipantVO(int cr_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> getCrIndex(String mem_mail) throws RemoteException {
		return pcDao.getCrIndex(mem_mail);
	}

	@Override
	public List<String> getMemMailByCrIndex(int cr_index) {
		return pcDao.getMemMailByCrIndex(cr_index);
	}

	@Override
	public Integer exitChatRoom(HashMap<String, Object> map) throws RemoteException {
		Integer num = null;
		
		num = pcDao.exitChatRoom(map);
		
		return num;
	}

}
