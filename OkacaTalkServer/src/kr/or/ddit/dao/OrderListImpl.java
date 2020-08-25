package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.OrderListVO;

public class OrderListImpl implements IOrderList {

	private SqlMapClient smc;
	private static OrderListImpl dao;
	private OrderListImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static OrderListImpl getInstance() {
		if(dao == null) {
			dao = new OrderListImpl();
		}
		
		return dao;	
	}
	
	@Override
	public OrderListVO selectOrderListVO(int o_index) {
		return null;
	}

	@Override
	public List<OrderListVO> getOrderListVO(int o_index) {

		List<OrderListVO> list = null;
		
		try {
			list = (List<OrderListVO>)smc.queryForList("OrderList.getorderlist", o_index);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	
	}

	@Override
	public int insertOrderListVO(OrderListVO vo) {
		int insert = 0;
		
		try {
			insert = (int) smc.insert("OrderList.insertorderlist", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(insert !=0) {
			System.out.println("insert 성공!");
		}else {
			System.out.println("다시하세요..^^");
		}
		
		return insert;
	}

	@Override
	public int deleteOrderListVO(int o_index) {
		return 0;
	}

	@Override
	public int updateOrderListVO(OrderListVO vo) {
		return 0;
	}

	@Override
	public List<OrderListVO> SelectOrderList(String mem_mail) {
		List<OrderListVO> list = null;
		
		try {
			list = (List<OrderListVO>)smc.queryForList("OrderList.selectorderlist", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
