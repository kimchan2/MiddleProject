package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.FavoriteListVO;
import kr.or.ddit.vo.FavoriteVO;
import kr.or.ddit.vo.FriendListVO;

public class FavoriteImpl implements IFavorite {

	private SqlMapClient smc;
	private static FavoriteImpl dao;
	private FavoriteImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static FavoriteImpl getInstance() {
		if(dao == null) {
			dao = new FavoriteImpl();
		}
		
		return dao;	
	}
	
	@Override
	public FavoriteVO selectFavoriteVO(int pl_index, String mem_mail) {
		return null;
	}

	@Override
	public List<FavoriteListVO> getFavoriteVO(String mem_mail) {
		List<FavoriteListVO> list = null;
		System.out.println("getFavoriteVO 데이터 베이스 요청..");
		try {
			list = (List<FavoriteListVO>) smc.queryForList("favorite.getFavoriteVO", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("list NULL 여부 : " + list);
		return list;
	}

	@Override
	public int insertFavoriteVO(FavoriteVO vo) {
		int cnt = 0;

		try {
			cnt = smc.update("favorite.insertFavoriteVO", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int deleteFavoriteVO(FavoriteVO vo) {

		int cnt = 0;

		try {
			cnt = smc.update("favorite.deleteFavoriteVO", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	
	}

	@Override
	public int updateFavoriteVO(FavoriteVO vo) {
		return 0;
	}

}
