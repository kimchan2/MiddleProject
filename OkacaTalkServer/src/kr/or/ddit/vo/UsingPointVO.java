package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 마일리지 사용
 * @author PC-22
 *
 */
public class UsingPointVO implements Serializable {
	public String toString() {
		return "마일리지 사용내역 " + up_point + "         /    내역일자" + up_date ;
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
	public int getUp_point() {
		return up_point;
	}
	public void setUp_point(int up_point) {
		this.up_point = up_point;
	}
	public String getUp_date() {
		return up_date;
	}
	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}
	private String  mem_mail   ;		//이메일
	private int  o_index    ;           //주문 내역 인덱스
	private int  up_point   ;           //사용 금액
	private String  up_date    ;        //사용 일시
	
}
