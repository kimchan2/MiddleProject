package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.OrderDetailVO;

public interface IOrderDetail {

	public OrderDetailVO selectOrderDetailVO(int o_index, int od_index);
	
	public List<OrderDetailVO> getOrderDetailVO(int o_index);
	
	public Object insertOrderDetailVO(OrderDetailVO vo);
	
	public int deleteOrderDetailVO(int o_index, int od_index);
	
	public int updateOrderDetailVO(OrderDetailVO vo);
	
}
