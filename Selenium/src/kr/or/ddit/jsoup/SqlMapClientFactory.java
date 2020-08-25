package kr.or.ddit.jsoup;

import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactory {
	private static SqlMapClient smc;
	
	static {
		Reader rd;
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/SqlMapConfig.xml");
			
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
		} catch (Exception e) {
			System.out.println("sql 클라이언트 객체 생성 실패");
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getClient() {
		return smc;
	}
}
