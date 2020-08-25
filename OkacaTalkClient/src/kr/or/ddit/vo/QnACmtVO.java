package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * QNA댓글
 * @author PC-22
 *
 */
public class QnACmtVO implements Serializable{

	public int getQac_index() {
		return qac_index;
	}
	public void setQac_index(int qac_index) {
		this.qac_index = qac_index;
	}
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
	public String getQac_content() {
		return qac_content;
	}
	public void setQac_content(String qac_content) {
		this.qac_content = qac_content;
	}
	private int   qac_index   ;			//QnA댓글 인덱스
	private int   qa_index    ;         //QnA글번호
	private String   mem_mail    ;      //QnA 댓글 작성자
	private String   qac_content ;      //내용
	
}
