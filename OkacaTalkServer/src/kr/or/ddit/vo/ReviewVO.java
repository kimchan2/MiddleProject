package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 리뷰
 * @author PC-22
 *
 */
public class ReviewVO implements Serializable {

	public int getRv_index() {
		return rv_index;
	}
	public void setRv_index(int rv_index) {
		this.rv_index = rv_index;
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
	public String getRv_title() {
		return rv_title;
	}
	public void setRv_title(String rv_title) {
		this.rv_title = rv_title;
	}
	public String getRv_date() {
		return rv_date;
	}
	public void setRv_date(String rv_date) {
		this.rv_date = rv_date;
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
	private  int  rv_index    ;				//     리뷰 글번호
	private  int  pl_index    ;             //     상품 인덱스
	private  String  mem_mail    ;          //     리뷰작성자
	private  String  rv_title    ;          //     제목
	private  String  rv_date     ;          //     작성일
	private  String  rv_content  ;          //     내용
	private  int  rv_report   ;             //     신고수
	private  int  rv_rc       ;             //     추천수
	
}
