package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * FAQ
 * @author PC-22
 *
 */
public class FAQVO implements Serializable {

	public int getFaq_no() {
		return faq_no;
	}
	public void setFaq_no(int faq_no) {
		this.faq_no = faq_no;
	}
	public String getFaq_title() {
		return faq_title;
	}
	public void setFaq_title(String faq_title) {
		this.faq_title = faq_title;
	}
	public String getFaq_content() {
		return faq_content;
	}
	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}
	private int faq_no     ;         //글번호
	private String faq_title  ;      //제목
	private String faq_content;      //내용
	
}
