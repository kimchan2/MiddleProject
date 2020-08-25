package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 마일리지 적립
 * @author PC-22
 *
 */
public class SavePointVO implements Serializable{
	public String toString() {
		return "마일리지 적립내역 " + sp_point + "         /    내역일자" + sp_date ;
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
	public int getSp_point() {
		return sp_point;
	}
	public void setSp_point(int sp_point) {
		this.sp_point = sp_point;
	}
	public String getSp_date() {
		return sp_date;
	}
	public void setSp_date(String sp_date) {
		this.sp_date = sp_date;
	}
	private String  mem_mail  ;		//이메일
	private int  o_index   ;        //주문 내역 인덱스
	private int  sp_point  ;        //적립 금액
	private String  sp_date   ;     //적립 일시
	
}
