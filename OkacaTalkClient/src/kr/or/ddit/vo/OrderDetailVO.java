package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 주문 상세 내역
 * @author PC-22
 *
 */
public class OrderDetailVO implements Serializable {

	public int getOd_index() {
		return od_index;
	}
	public void setOd_index(int od_index) {
		this.od_index = od_index;
	}
	public int getPl_index() {
		return pl_index;
	}
	public void setPl_index(int pl_index) {
		this.pl_index = pl_index;
	}
	public int getO_index() {
		return o_index;
	}
	public void setO_index(int o_index) {
		this.o_index = o_index;
	}
	public String getOd_status() {
		return od_status;
	}
	public void setOd_status(String od_status) {
		this.od_status = od_status;
	}
	public int getOd_quantity() {
		return od_quantity;
	}
	public void setOd_quantity(int od_quantity) {
		this.od_quantity = od_quantity;
	}
	public int getOd_price() {
		return od_price;
	}
	public void setOd_price(int od_price) {
		this.od_price = od_price;
	}
	private  int od_index    ;				//	주문 상세 내역 인덱스
	private  int pl_index    ;              //  상품 인덱스
	private  int o_index     ;              //  주문 내역 인덱스
	private  String od_status   ;           //  주문 상태
	private  int od_quantity ;              //  주문 수량
	private  int od_price    ;              //  주문 금액
	
}
