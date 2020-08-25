package kr.or.ddit.vo;

import java.io.Serializable;

public class OrderDetailListVO implements Serializable {
	
	private String mem_mail; // 사용자 메일
	private int o_index;	// 주문 내역 인덱스
	private String o_date;		// 주문 날짜
	private String pl_name;	// 상품 이름
	private int pl_price; 	// 상품 가격
	private int od_quantity; // 상품 수량
	
	public int getOd_quantity() {
		return od_quantity;
	}
	public void setOd_quantity(int od_quantity) {
		this.od_quantity = od_quantity;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public int getO_index() {
		return o_index;
	}
	public void setO_index(int o_index) {
		this.o_index = o_index;
	}
	public String getO_date() {
		return o_date;
	}
	public void setO_date(String o_date) {
		this.o_date = o_date;
	}
	public String getPl_name() {
		return pl_name;
	}
	public void setPl_name(String pl_name) {
		this.pl_name = pl_name;
	}
	public int getPl_price() {
		return pl_price;
	}
	public void setPl_price(int pl_price) {
		this.pl_price = pl_price;
	}
	
}
