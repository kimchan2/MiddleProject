package kr.or.ddit.vo;

import java.io.Serializable;

public class MyReviewVO implements Serializable {
	
	private int pl_index;			// 상품 인덱스
	private String pl_name;			// 상품 이름
	private int pl_price;			// 상품 가격
	private String pl_image; 		// 상품 이미지
	private int rv_index;			// 리뷰 인덱스
	private String rv_content;		// 리뷰 내용
	private int rv_report;			// 신고 수
	private int rv_rc;				// 추천 수
	private String mem_mail;		// 사용자 메일
	
	public int getRv_index() {
		return rv_index;
	}
	public void setRv_index(int rv_index) {
		this.rv_index = rv_index;
	}
	
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getPl_image() {
		return pl_image;
	}
	public void setPl_image(String pl_image) {
		this.pl_image = pl_image;
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
	public int getPl_index() {
		return pl_index;
	}
	public void setPl_index(int pl_index) {
		this.pl_index = pl_index;
	}
	public String getRv_content() {
		return rv_content;
	}
	public void setRv_content(String rv_content) {
		this.rv_content = rv_content;
	}
	public int getRv_report() {
		return rv_report;
	}
	public void setRv_report(int rv_report) {
		this.rv_report = rv_report;
	}
	public int getRv_rc() {
		return rv_rc;
	}
	public void setRv_rc(int rv_rc) {
		this.rv_rc = rv_rc;
	}
}
