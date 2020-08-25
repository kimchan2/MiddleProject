package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.ChargeCashVO;

public interface IChargeCash {

	public List<ChargeCashVO> selectChargeCash(String mem_mail);
	
	public List<ChargeCashVO> getChargeCash(String mem_mail);
	
	public int insertChargeCash(ChargeCashVO vo);
	
	public int deleteChargeCash(String mem_mail);
	
	public int updateChargeCash(ChargeCashVO vo);
	
}
