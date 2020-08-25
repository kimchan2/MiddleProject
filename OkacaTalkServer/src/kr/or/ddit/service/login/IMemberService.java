package kr.or.ddit.service.login;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMemberService extends Remote {

	public MemberVO selectMemberVO(MemberVO vo) throws RemoteException;
	
	public List<MemberVO> getMemberVO() throws RemoteException;
	
	public Object insertMemberVO(MemberVO vo) throws RemoteException;

	public int deleteMemberVO(String mem_mail) throws RemoteException;

	public Object updateMemberVO(MemberVO vo) throws RemoteException;
	
	public int updatecashMember(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> memberLogin(MemberVO mv) throws RemoteException;  
	
	public MemberVO idSearch(String mem_mail) throws RemoteException;
	
	public MemberVO idSearch2(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> memNoSearch(MemberVO mv) throws RemoteException;
	
	public MemberVO pwSearch(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> nickSearch(MemberVO mv) throws RemoteException;
	
	public String getNickByEmail(String mem_mail) throws RemoteException;
	
	public List<MemberVO> getBirthdayMember(String mem_mail) throws RemoteException;
	
	public int updatemodifyMember(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> getBookmarkMember(String mem_mail) throws RemoteException;
	
	public List<MemberVO> getFriendList(String mem_mail) throws RemoteException;
	
	public int updateCashPoint(MemberVO mv) throws RemoteException;
}
