package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.ConfigurationVO;

public interface IConfiguration {

	public ConfigurationVO selectConfiguration(String mem_mail);
	
	public List<ConfigurationVO> getConfiguration(String mem_mail);
	
	public int insertConfiguration(ConfigurationVO vo);
	
	public int deleteConfiguration(String mem_mail);
	
	public int updateConfiguration(ConfigurationVO vo);
	
}
