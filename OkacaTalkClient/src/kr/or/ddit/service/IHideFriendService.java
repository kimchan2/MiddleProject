package kr.or.ddit.service;

import java.rmi.Remote;
import java.util.List;

import kr.or.ddit.vo.HideFriendVO;

public interface IHideFriendService extends Remote {

	public HideFriendVO selectHideFriendVO(String mem_mail, String fr_mail);
	
	public List<HideFriendVO> getHideFriendVO();
	
	public int insertHideFriendVO(HideFriendVO vo);
	
	public int deleteHideFriendVO(String mem_mail, String fr_mail);
	
//	public int updateFriendListVO(HideFriendVO vo);
	
}
