package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 첨부파일
 * @author PC-22
 *
 */
public class AttachmentVO implements Serializable{

	private int acm_index;		//파일인덱스
	private int qa_index ;		//QNA글번호
	private int rv_index ;		//리뷰 글번호
	private int iq_index ;		//상품문의사항 글번호
	private String acm_name ;	//파일이름
	private String acm_type ;		//파일 형식
	private String acm_addr ;		//파일 주소
	public int getAcm_index() {
		return acm_index;
	}
	public void setAcm_index(int acm_index) {
		this.acm_index = acm_index;
	}
	public int getQa_index() {
		return qa_index;
	}
	public void setQa_index(int qa_index) {
		this.qa_index = qa_index;
	}
	public int getRv_index() {
		return rv_index;
	}
	public void setRv_index(int rv_index) {
		this.rv_index = rv_index;
	}
	public int getIq_index() {
		return iq_index;
	}
	public void setIq_index(int iq_index) {
		this.iq_index = iq_index;
	}
	public String getAcm_name() {
		return acm_name;
	}
	public void setAcm_name(String acm_name) {
		this.acm_name = acm_name;
	}
	public String getAcm_type() {
		return acm_type;
	}
	public void setAcm_type(String acm_type) {
		this.acm_type = acm_type;
	}
	public String getAcm_addr() {
		return acm_addr;
	}
	public void setAcm_addr(String acm_addr) {
		this.acm_addr = acm_addr;
	}
	
	
}
