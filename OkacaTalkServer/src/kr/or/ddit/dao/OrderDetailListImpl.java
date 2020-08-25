package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.OrderDetailListVO;

public class OrderDetailListImpl implements IOrderDetailList {

	private SqlMapClient smc;
	private static OrderDetailListImpl dao;
	private OrderDetailListImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static OrderDetailListImpl getInstance() {
		if(dao == null) {
			dao = new OrderDetailListImpl();
		}
		
		return dao;
	}
	
	@Override
	public List<OrderDetailListVO> selectOrderDetailList(String mem_mail) {
		List<OrderDetailListVO> odlVO = new ArrayList<OrderDetailListVO>();
		
		try {
			odlVO = (List<OrderDetailListVO>) smc.queryForList("OrderList.selectorderdetaillist", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return odlVO;
	}

}
