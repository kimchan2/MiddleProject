package kr.or.ddit.vo;

import java.io.Serializable;

public class FileVO implements Serializable {

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public MemberVO getMemVO() {
		return memVO;
	}
	public void setMemVO(MemberVO memVO) {
		this.memVO = memVO;
	}
	private String fileName;
	private byte[] fileData;
	private MemberVO memVO;
}
