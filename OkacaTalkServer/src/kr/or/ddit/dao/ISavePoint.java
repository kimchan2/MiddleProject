package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.SavePointVO;

public interface ISavePoint {

	public SavePointVO selectSavePointVO(String mem_mail, int o_index);
	
	public List<SavePointVO> getSavePointVO(String mem_mail);
	
	public int insertSavePointVO(SavePointVO vo);
	
	public int deleteSavePointVO(String mem_mail, int o_index);
	
	public int updateSavePointVO(SavePointVO vo);
	
}
