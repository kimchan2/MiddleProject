package kr.or.ddit.vo;

/**
 * 상품 문의사항 댓글
 * @author PC-22
 *
 */
public class InquireCmtVO {

	public int getIqc_index() {
		return iqc_index;
	}
	public void setIqc_index(int iqc_index) {
		this.iqc_index = iqc_index;
	}
	public int getIq_index() {
		return iq_index;
	}
	public void setIq_index(int iq_index) {
		this.iq_index = iq_index;
	}
	public String getIqc_content() {
		return iqc_content;
	}
	public void setIqc_content(String iqc_content) {
		this.iqc_content = iqc_content;
	}
	private int iqc_index   ;	//상품문의사항 댓글 인덱스
	private int iq_index    ;	//상품문의사항 글번호
	private String iqc_content ;	//내용
	
}
