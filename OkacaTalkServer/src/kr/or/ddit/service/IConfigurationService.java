package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ConfigurationVO;

public interface IConfigurationService extends Remote {

	public ConfigurationVO selectConfiguration(String mem_mail) throws RemoteException;
	
	public List<ConfigurationVO> getConfiguration(String mem_mail) throws RemoteException;
	
	public int insertConfiguration(ConfigurationVO vo) throws RemoteException;
	
	public int deleteConfiguration(String mem_mail) throws RemoteException;
	
	public int updateConfiguration(ConfigurationVO vo) throws RemoteException;
	
}
