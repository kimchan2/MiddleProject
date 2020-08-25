package kr.or.ddit.dao;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.UsingCashVO;

public interface IUsingCash {

	public UsingCashVO selectUsingCashVO(int o_index, String mem_mail);
	
	public List<UsingCashVO> getUsingCashVO(String mem_mail);
	
	public int insertUsingCashVO(UsingCashVO vo);
	
	public int deleteUsingCashVO(int o_index, String mem_mail);
	
//	public int updateUsingCashVO(UsingCashVO vo);
	
	public Object insertUsingCashListVO(UsingCashVO vo);
	
}
