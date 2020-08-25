package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.BackgroundImgListVO;
import kr.or.ddit.vo.ProfileImgListVO;

public class BackgroundImgListImpl implements IBackgroundImgList {

	private SqlMapClient smc;
	private static BackgroundImgListImpl dao;
	private BackgroundImgListImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static BackgroundImgListImpl getInstance() {
		if(dao == null) {
			dao = new BackgroundImgListImpl();
		}
		
		return dao;	
	}
	
	@Override
	public BackgroundImgListVO selectBGI(int bg_index) {
		return null;
	}

	@Override
	public BackgroundImgListVO getBGI(String mem_mail) {
		
		BackgroundImgListVO vo = null;
		System.out.println(vo);
		System.out.println("데이터 베이스 요청..");
		try {
			vo = (BackgroundImgListVO) smc.queryForObject("BackgroundImgList.getBackgroundImg", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("BackgroundImgListVO NULL 여부 : " + vo);
		return vo;
		
	}

	@Override
	public Object insertBGI(BackgroundImgListVO vo) {

		Object obj = null;
		
		try {
			obj = smc.insert("BackgroundImgList.insertBackgroundImgList", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
		
	}

	@Override
	public Object deleteBGI(int bg_index) {
		return 0;
	}

}
