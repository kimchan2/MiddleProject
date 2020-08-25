package kr.or.ddit.service.login;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.MemberImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberService extends UnicastRemoteObject implements IMemberService {

	MemberImpl memDao; 
	private static MemberService service; 
	
	private MemberService() throws RemoteException{
		memDao = MemberImpl.getInstance();
	}
	
	public static MemberService getInstance() throws RemoteException {
		if(service== null) {
			service = new MemberService();
		}
		return service;
	}
	
	@Override
	public MemberVO selectMemberVO(MemberVO vo) {
		return memDao.selectMemberVO(vo);
	}

	@Override
	public List<MemberVO> getMemberVO() {
		return memDao.getMemberVO();
	}

	@Override
	public Object insertMemberVO(MemberVO vo) {
		return memDao.insertMemberVO(vo);
	}

	@Override
	public int deleteMemberVO(String mem_mail) {
		return memDao.deleteMemberVO(mem_mail);
	}

	@Override
	public Object updateMemberVO(MemberVO vo) {
		return memDao.updateMemberVO(vo);
	}

	@Override
	public List<MemberVO> memberLogin(MemberVO mv) throws RemoteException {
		return null;
	}

	@Override
	public MemberVO idSearch2(MemberVO mv) throws RemoteException {
		return memDao.idSearch2(mv);
	}

	@Override
	public List<MemberVO> memNoSearch(MemberVO mv) throws RemoteException {
		return null;
	}

	@Override
	public MemberVO pwSearch(MemberVO mv) throws RemoteException {
		return memDao.pwSearch(mv);
	}

	@Override
	public MemberVO idSearch(String mem_mail) throws RemoteException {
		return memDao.idSearch(mem_mail);
	}

	@Override
	public List<MemberVO> nickSearch(MemberVO mv) throws RemoteException {
		return memDao.nickSearch(mv);
	}

	@Override
	public String getNickByEmail(String mem_mail) throws RemoteException {
		return memDao.getNickByEmail(mem_mail);
	}

	@Override
	public List<MemberVO> getBirthdayMember(String mem_mail) throws RemoteException {
		return memDao.getBirthdayMember(mem_mail);
	}

	@Override
	public int updatemodifyMember(MemberVO mv) throws RemoteException {
		
		return memDao.updatemodifyMember(mv);
	}

	@Override
	public int updatecashMember(MemberVO mv) throws RemoteException {
		
		return memDao.updatecashMember(mv);
	}

	@Override
	public List<MemberVO> getBookmarkMember(String mem_mail) throws RemoteException {
		return memDao.getBookmarkMember(mem_mail);
	}

	@Override
	public List<MemberVO> getFriendList(String mem_mail) throws RemoteException {
		return memDao.getFriendList(mem_mail);
	}

	@Override
	public int updateCashPoint(MemberVO mv) throws RemoteException {
		return memDao.updateCashPoint(mv);
	}

}
