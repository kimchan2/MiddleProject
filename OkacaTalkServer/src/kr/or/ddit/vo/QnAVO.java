package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * QNA
 * @author PC-22
 *
 */
public class QnAVO implements Serializable{

	public int getQa_index() {
		return qa_index;
	}
	public void setQa_index(int qa_index) {
		this.qa_index = qa_index;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getQa_title() {
		return qa_title;
	}
	public void setQa_title(String qa_title) {
		this.qa_title = qa_title;
	}
	public String getQa_date() {
		return qa_date;
	}
	public void setQa_date(String qa_date) {
		this.qa_date = qa_date;
	}
	public String getQa_content() {
		return qa_content;
	}
	public void setQa_content(String qa_content) {
		this.qa_content = qa_content;
	}
	private  int  qa_index    ;			//    QnA글번호
	private  String  mem_mail    ;      //    QnA작성자
	private  String  qa_title    ;      //    제목
	private  String  qa_date     ;      //    작성일
	private  String  qa_content  ;      //    내용
	
}
