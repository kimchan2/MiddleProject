package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 상품 문의사항
 * @author PC-22
 *
 */
public class InquireVO  implements Serializable{

	public int getIq_index() {
		return iq_index;
	}
	public void setIq_index(int iq_index) {
		this.iq_index = iq_index;
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
	public String getIq_content() {
		return iq_content;
	}
	public void setIq_content(String iq_content) {
		this.iq_content = iq_content;
	}
	public String getIq_date() {
		return iq_date;
	}
	public void setIq_date(String iq_date) {
		this.iq_date = iq_date;
	}
	private int iq_index		;            //상품문의사항 글번호
	private int pl_index        ;            //상품 인덱스
	private String mem_mail        ;         //상품 문의사항 작성자
	private String iq_content      ;         //내용
	private String iq_date         ;         //작성일
	
}
