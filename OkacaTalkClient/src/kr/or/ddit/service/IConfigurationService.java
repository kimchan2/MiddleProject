package kr.or.ddit.service;

import java.rmi.Remote;
import java.util.List;

import kr.or.ddit.vo.ConfigurationVO;

public interface IConfigurationService extends Remote {

	public ConfigurationVO selectConfiguration(String mem_mail);
	
	public List<ConfigurationVO> getConfiguration(String mem_mail);
	
	public int insertConfiguration(ConfigurationVO vo);
	
	public int deleteConfiguration(String mem_mail);
	
	public int updateConfiguration(ConfigurationVO vo);
	
}
