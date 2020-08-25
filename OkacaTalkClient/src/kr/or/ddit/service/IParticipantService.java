package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.ParticipantVO;

public interface IParticipantService extends Remote {

//	public ParticipantVO selectParticipantVO(int cr_index);
	
	public List<ParticipantVO> getParticipantVO(int cr_index) throws RemoteException;
	
	public void insertParticipantVO(ParticipantVO vo) throws RemoteException;
	
	public int deleteParticipantVO(int cr_index) throws RemoteException;
	
	public List<Integer> getCrIndex(String mem_mail) throws RemoteException;
	
	public List<String> getMemMailByCrIndex(int cr_index) throws RemoteException;
	
	public Integer exitChatRoom(HashMap<String, Object> map) throws RemoteException;
//	public int updateParticipantVO(ParticipantVO vo);
	
}
