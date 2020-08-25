package kr.or.ddit.dao;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMember {

	public MemberVO selectMemberVO(MemberVO MemberVO);
	
	public List<MemberVO> getMemberVO();
	
	public Object insertMemberVO(MemberVO vo);
	
	public int deleteMemberVO(String mem_mail);
	
	public Object updateMemberVO(MemberVO vo);
	
	public MemberVO idSearch(String mem_mail);
	
	public MemberVO idSearch2(MemberVO mv);
	
	public MemberVO pwSearch(MemberVO mv);
	
	public List<MemberVO> nickSearch(MemberVO mv);
	
	public String getNickByEmail(String mem_mail);
	
	public List<MemberVO> getBirthdayMember(String mem_mail);
	
	public int updatemodifyMember(MemberVO mv);
	
	public List<MemberVO> getBookmarkMember(String mem_mail);
	
	public List<MemberVO> getFriendList(String mem_mail);
	
	public int updateCashPoint(MemberVO mv);
}
