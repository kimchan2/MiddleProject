package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.OrderDetailListVO;

public interface IOrderDetailList {
	
	public List<OrderDetailListVO> selectOrderDetailList(String mem_mail);
}
