package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.ChatRoomImpl;
import kr.or.ddit.dao.ConfigurationImpl;
import kr.or.ddit.vo.ConfigurationVO;

public class ConfigurationService extends UnicastRemoteObject implements IConfigurationService {

	ConfigurationImpl cfDao; 
	private static ConfigurationService service; 
	
	private ConfigurationService() throws RemoteException{
		cfDao = ConfigurationImpl.getInstance();
	}
	
	public static ConfigurationService getInstance() throws RemoteException {
		if(service== null) {
			service = new ConfigurationService();
		}
		return service;
	}
	
	@Override
	public ConfigurationVO selectConfiguration(String mem_mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfigurationVO> getConfiguration(String mem_mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertConfiguration(ConfigurationVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteConfiguration(String mem_mail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateConfiguration(ConfigurationVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
