package kr.or.ddit.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.ParticipantVO;

public interface IParticipant {

//	public ParticipantVO selectParticipantVO(int cr_index);
	
	public List<ParticipantVO> getParticipantVO(int cr_index);
	
	public void insertParticipantVO(ParticipantVO vo);
	
	public int deleteParticipantVO(int cr_index);

	public List<Integer> getCrIndex(String mem_mail);
	
	public List<String> getMemMailByCrIndex(int cr_index);
	
	public Integer exitChatRoom(HashMap<String, Object> map);
//	public int updateParticipantVO(ParticipantVO vo);
	
}
