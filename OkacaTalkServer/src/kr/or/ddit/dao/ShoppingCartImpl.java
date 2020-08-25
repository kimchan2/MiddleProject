package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.ShoppingCartVO;

public class ShoppingCartImpl implements IShoppingCart {

	private SqlMapClient smc;
	private static ShoppingCartImpl dao;
	private ShoppingCartImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ShoppingCartImpl getInstance() {
		if(dao == null) {
			dao = new ShoppingCartImpl();
		}
		
		return dao;	
	}
	
	@Override
	public ShoppingCartVO selectShoppingCartVO(int sc_index) {
		ShoppingCartVO vo = null;
		
		try {
			vo = (ShoppingCartVO)smc.queryForObject("shoppingCart.SelectShoppingCartVO" , sc_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public List<ShoppingCartVO> getShoppingCartVO(String mem_mail) {

		List<ShoppingCartVO> vo = null;
		
		try {
			vo = (List<ShoppingCartVO>) smc.queryForList("shoppingCart.getShoppingCartVO", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	
	}

	@Override
	public int insertShoppingCartVO(ShoppingCartVO vo) {
		int shoppingCart = 0;
		
		try {
			shoppingCart = smc.update("shoppingCart.insertshoppingcartlist", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return shoppingCart;
	}

	@Override
	public int deleteShoppingCartVO(int sc_index) {
		
		int shoppingCart = 0;
		System.out.println(shoppingCart);
		System.out.println("데이터 베이스 요청..");
		try {
			shoppingCart = smc.update("shoppingCart.deleteShoppingCartVO", sc_index);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("spVO NULL 여부 : " + shoppingCart);
		
		
		return shoppingCart;
	}

	@Override
	public int updateShoppingCartVO(ShoppingCartVO vo) {
		return 0;
	}

}
