package kr.or.ddit.vo;

import java.io.Serializable;

/** 
 * 주문 내역
 * @author PC-22
 *
 */
public class OrderListVO implements Serializable {

	public int getO_index() {
		return o_index;
	}
	public void setO_index(int o_index) {
		this.o_index = o_index;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getO_date() {
		return o_date;
	}
	public void setO_date(String o_date) {
		this.o_date = o_date;
	}
	private int  o_index    ;					//주문 내역 인덱스
	private String  mem_mail   ;                //이메일
	private String  o_date     ;                //주문일자
	           
}
