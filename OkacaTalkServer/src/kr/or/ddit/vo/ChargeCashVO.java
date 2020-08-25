package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 캐시충전 
 * @author PC-22
 *
 */
public class ChargeCashVO implements Serializable {

	public String toString() {
		return "머니 충전내역 " + cc_cash + "         /    내역일자" + cc_date ;
	}
	private int cc_index;		
	public int getCc_index() {
		return cc_index;
	}
	public void setCc_index(int cc_index) {
		this.cc_index = cc_index;
	}
	private String mem_mail;	//이메일
	private int cc_cash ;		//충전 금액
	private String cc_date ;		//충전 일시
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public int getCc_cash() {
		return cc_cash;
	}
	public void setCc_cash(int cc_cash) {
		this.cc_cash = cc_cash;
	}
	public String getCc_date() {
		return cc_date;
	}
	public void setCc_date(String cc_date) {
		this.cc_date = cc_date;
	}
	
}
