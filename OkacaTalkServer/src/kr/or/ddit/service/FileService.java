package kr.or.ddit.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.stage.FileChooser;
import kr.or.ddit.vo.FileVO;

public class FileService extends UnicastRemoteObject implements IFileService {

	protected FileService() throws RemoteException {
		super();
	}

	private static FileService service; 
	
	public static FileService getInstance() throws RemoteException {
		if(service== null) {
			service = new FileService();
		}
		return service;
	}
	
	@Override
	public void setFile(FileVO file) throws RemoteException {

		FileOutputStream fout = null;
		String dir = "C:/Filedata/";
		File folder = new File(dir);
		if(folder.exists() == false) {
			folder.mkdir();
		}
		dir = "C:/Filedata/" + file.getMemVO().getMem_mail() + "/";
		folder = new File(dir);
		if(folder.exists() == false) {
			folder.mkdir();
		}
		
		try {
			fout = new FileOutputStream(dir + file.getFileName());
			fout.write(file.getFileData());
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String setFile(FileVO file, Integer cr_index) throws RemoteException {
		
		FileOutputStream fout = null;
		String dir = "C:/Filedata/ChatRoom/" + cr_index + "/";
		
		File folder = new File(dir);
		if(folder.exists() == false) {
			folder.mkdir();
		}
		dir = dir + file.getMemVO().getMem_mail() + "/";
		folder = new File(dir);
		if(folder.exists() == false) {
			folder.mkdir();
		}
		
		try {
			fout = new FileOutputStream(dir + file.getFileName());
			fout.write(file.getFileData());
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dir  + file.getFileName() ;
		
	}
	
	/**
	 * 첨부파일 저장
	 * flag
	 * 	0 -> 상품 문의사항
	 *	1 -> 리뷰
	 *	3 -> QnA
	 */
	@Override
	public String setAttachment(int flag, FileVO file, int index) throws RemoteException {

		FileOutputStream fout = null;
		String dir = "";
		if(flag == 0) {
			dir = "C:/Filedata/Inquire/" + index + "/";
		} else if(flag == 1) {
			dir = "C:/Filedata/Review/"  + index + "/";
		} else if(flag == 2) {
			dir = "C:/Filedata/QnA/"  + index + "/";
		}
		File folder = new File(dir);
		if(folder.exists() == false) {
			folder.mkdirs();
		}
		dir = dir + file.getMemVO().getMem_mail() + "/";
		folder = new File(dir);
		if(folder.exists() == false) {
			folder.mkdir();
		}
		
		try {
			fout = new FileOutputStream(dir + file.getFileName());
			fout.write(file.getFileData());
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dir  + file.getFileName() ;
		
	}

	@Override
	public FileVO getFile(String path) throws RemoteException {
		FileVO vo = new FileVO();
		File file = new File(path);
		vo.setFileName(file.getName());
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int len = (int)file.length();
		byte[] data = new byte[len];
		try {
			fin.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		vo.setFileData(data);
		
		return vo;
	}

}
