package kr.or.ddit.vo;

/**
 * 공지사항 댓글
 * @author PC-22
 *
 */
public class NoticeCmtVO {

	public int getNtc_index() {
		return ntc_index;
	}
	public void setNtc_index(int ntc_index) {
		this.ntc_index = ntc_index;
	}
	public int getNt_index() {
		return nt_index;
	}
	public void setNt_index(int nt_index) {
		this.nt_index = nt_index;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getNtc_content() {
		return ntc_content;
	}
	public void setNtc_content(String ntc_content) {
		this.ntc_content = ntc_content;
	}
	private int ntc_index   ;                  ////공지사항댓글 인덱스
	private int nt_index    ;                  ////공지사항 글번호
	private String mem_mail    ;               ////공지사항 댓글 작성자
	private String ntc_content ;               ////내용
	
}
