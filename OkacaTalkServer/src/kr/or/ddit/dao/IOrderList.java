package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.OrderListVO;

public interface IOrderList {

	public OrderListVO selectOrderListVO(int o_index);
	
	public List<OrderListVO> getOrderListVO(int o_index);
	
	public int insertOrderListVO(OrderListVO vo);
	
	public int deleteOrderListVO(int o_index);
	
	public int updateOrderListVO(OrderListVO vo);
	
	public List<OrderListVO> SelectOrderList(String mem_mail);
	
}
