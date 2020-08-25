package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 공지사항
 * @author PC-22
 *
 */
public class NoticeVO implements Serializable {

	public int getNt_index() {
		return nt_index;
	}
	public void setNt_index(int nt_index) {
		this.nt_index = nt_index;
	}
	public String getNt_title() {
		return nt_title;
	}
	public void setNt_title(String nt_title) {
		this.nt_title = nt_title;
	}
	public String getNt_date() {
		return nt_date;
	}
	public void setNt_date(String nt_date) {
		this.nt_date = nt_date;
	}
	public String getNt_content() {
		return nt_content;
	}
	public void setNt_content(String nt_content) {
		this.nt_content = nt_content;
	}
	private int  nt_index   ;        ///공지사항 글번호
	private String  nt_title   ;     ///제목
	private String  nt_date    ;     ///작성일
	private String  nt_content ;		///내용
	
}
