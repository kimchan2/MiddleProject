package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 장바구니
 * @author PC-22
 *
 */
public class ShoppingCartVO implements Serializable {

	public int getSc_index() {
		return sc_index;
	}
	public void setSc_index(int sc_index) {
		this.sc_index = sc_index;
	}
	public int getPl_index() {
		return pl_index;
	}
	public void setPl_index(int pl_index) {
		this.pl_index = pl_index;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public int getSc_quantity() {
		return sc_quantity;
	}
	public void setSc_quantity(int sc_quantity) {
		this.sc_quantity = sc_quantity;
	}
	private int  sc_index     ;				///장바구니 인덱스
	private int  pl_index     ;             ///상품 인덱스
	private String  mem_mail     ;          ///이메일
	private int  sc_quantity  ;             ///상품수량
	
}
