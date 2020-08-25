package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.UsingPointVO;

public interface IUsingPoint {

	public UsingPointVO selectUsingPointVO(int o_index, String mem_mail);
	
	public List<UsingPointVO> getUsingPointVO(String mem_mail);
	
	public int insertUsingPointVO(UsingPointVO vo);
	
	public int deleteUsingPointVO(int o_index, String mem_mail);
	
//	public int updateUsingPointVO(UsingPointVO vo);
	
	public int updateUsingPointList(UsingPointVO vo);
	
}
