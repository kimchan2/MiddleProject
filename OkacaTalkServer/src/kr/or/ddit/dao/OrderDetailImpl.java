package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.OrderDetailVO;

public class OrderDetailImpl implements IOrderDetail {

	private SqlMapClient smc;
	private static OrderDetailImpl dao;
	private OrderDetailImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static OrderDetailImpl getInstance() {
		if(dao == null) {
			dao = new OrderDetailImpl();
		}
		
		return dao;	
	}
	
	@Override
	public OrderDetailVO selectOrderDetailVO(int o_index, int od_index) {
		return null;
	}

	@Override
	public List<OrderDetailVO> getOrderDetailVO(int o_index) {
		ArrayList<OrderDetailVO> odvo = new ArrayList<>();
		
		try {
			odvo = (ArrayList<OrderDetailVO>) smc.queryForList("OrderList.getorderlistDetail" , o_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return odvo;
	}

	@Override
	public Object insertOrderDetailVO(OrderDetailVO vo) {
		Object obj = null;
		
		try {
			obj = smc.insert("OrderList.insertorderlistdetail", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public int deleteOrderDetailVO(int o_index, int od_index) {
		return 0;
	}

	@Override
	public int updateOrderDetailVO(OrderDetailVO vo) {
		return 0;
	}

}
