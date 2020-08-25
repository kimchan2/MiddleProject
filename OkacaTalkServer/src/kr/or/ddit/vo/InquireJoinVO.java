package kr.or.ddit.vo;

import java.io.Serializable;

public class InquireJoinVO implements Serializable {
	private int iq_index		;            //상품문의사항 글번호
	private String mem_mail        ;         //상품 문의사항 작성자
	private String iq_content      ;         //내용
	private String iq_date         ;         //작성일
	private  int  pl_index ;			  ///상품 인덱스
	private  String  pl_name  ;         ///이름
	private  String  pl_image ;         ///이미지
	private int pl_price ;				///가격
	
	public int getPl_price() {
		return pl_price;
	}
	public void setPl_price(int pl_price) {
		this.pl_price = pl_price;
	}
	public int getIq_index() {
		return iq_index;
	}
	public void setIq_index(int iq_index) {
		this.iq_index = iq_index;
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
	public int getPl_index() {
		return pl_index;
	}
	public void setPl_index(int pl_index) {
		this.pl_index = pl_index;
	}
	public String getPl_name() {
		return pl_name;
	}
	public void setPl_name(String pl_name) {
		this.pl_name = pl_name;
	}
	public String getPl_image() {
		return pl_image;
	}
	public void setPl_image(String pl_image) {
		this.pl_image = pl_image;
	}

}
