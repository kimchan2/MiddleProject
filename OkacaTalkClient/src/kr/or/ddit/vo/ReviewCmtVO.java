package kr.or.ddit.vo;

/**
 * 리뷰 댓글
 * @author PC-22
 *
 */
public class ReviewCmtVO {

	public int getRvc_index() {
		return rvc_index;
	}
	public void setRvc_index(int rvc_index) {
		this.rvc_index = rvc_index;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public int getRv_index() {
		return rv_index;
	}
	public void setRv_index(int rv_index) {
		this.rv_index = rv_index;
	}
	public String getRvc_content() {
		return rvc_content;
	}
	public void setRvc_content(String rvc_content) {
		this.rvc_content = rvc_content;
	}
	private int   rvc_index    ;				//리뷰댓글 인덱스
	private String   mem_mail     ;             //리뷰댓글작성자
	private int   rv_index     ;                //리뷰 글번호
	private String   rvc_content  ;             //내용
	
}
