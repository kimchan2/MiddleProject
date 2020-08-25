package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.FriendListVO;

public interface IFriendList {

	public FriendListVO selectFriendListVO(String mem_mail, String fr_mail);
	
	public List<FriendListVO> getFriendListVO(String mem_mail);
	
	public Object insertFriendListVO(FriendListVO vo);
	
	public Object deleteFriendListVO(FriendListVO vo);
	
//	public int updateFriendListVO(FriendListVO vo);
	
}
