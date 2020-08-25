package kr.or.ddit.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.ConfigurationVO;

public class ConfigurationImpl implements IConfiguration {

	private SqlMapClient smc;
	private static ConfigurationImpl dao;
	private ConfigurationImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ConfigurationImpl getInstance() {
		if(dao == null) {
			dao = new ConfigurationImpl();
		}
		
		return dao;	
	}
	
	@Override
	public ConfigurationVO selectConfiguration(String mem_mail) {
		return null;
	}

	@Override
	public List<ConfigurationVO> getConfiguration(String mem_mail) {
		return null;
	}

	@Override
	public int insertConfiguration(ConfigurationVO vo) {
		return 0;
	}

	@Override
	public int deleteConfiguration(String mem_mail) {
		return 0;
	}

	@Override
	public int updateConfiguration(ConfigurationVO vo) {
		return 0;
	}

}
