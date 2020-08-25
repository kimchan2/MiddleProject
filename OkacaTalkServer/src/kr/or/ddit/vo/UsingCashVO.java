package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 캐시 사용
 * @author PC-22
 *
 */
public class UsingCashVO implements Serializable {
	
	public String toString() {
		return "머니 사용내역 " + uc_cash + "         /    내역일자" + uc_date ;
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
	public int getUc_cash() {
		return uc_cash;
	}
	public void setUc_cash(int uc_cash) {
		this.uc_cash = uc_cash;
	}
	public String getUc_date() {
		return uc_date;
	}
	public void setUc_date(String uc_date) {
		this.uc_date = uc_date;
	}
	private String  mem_mail;		//이메일
	private int  o_index ;          //주문 내역 인덱스
	private int  uc_cash ;          //사용 금액
	private String  uc_date ;       //사용 일시
	
}
