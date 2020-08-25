package kr.or.ddit.view.shoppingMain.chatQna;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kr.or.ddit.service.IAttachmentService;
import kr.or.ddit.service.IFileService;
import kr.or.ddit.service.IQnAService;
import kr.or.ddit.session.Session;
import kr.or.ddit.utils.ObjectUtils;
import kr.or.ddit.vo.AttachmentVO;
import kr.or.ddit.vo.FileListVO;
import kr.or.ddit.vo.FileVO;
import kr.or.ddit.vo.QnAVO;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class QnaListWriteController implements Initializable{

	@FXML public AnchorPane Qna_Write_AnchorPane;
	@FXML Button Qna_Write_Btn;
	@FXML Button Qna_AddImage_Btn;
	@FXML TextArea Qa_Title;
	@FXML TextArea Qa_Content;
	@FXML VBox Qa_Content_VBox;
	
	private Registry reg;
	private IQnAService iqs;
	private IFileService ifs;
	private IAttachmentService ias;
	
	AttachmentVO avo = new AttachmentVO();
	String attachmentChk = null;
	FileVO fvo = new FileVO();
	File file;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			iqs = (IQnAService) reg.lookup("qnA");
			ifs = (IFileService) reg.lookup("file");
			ias = (IAttachmentService) reg.lookup("attachment");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Qa_Title.setWrapText(true);
		Qa_Content.setWrapText(true);
	}

	@FXML public void qnaWriteBtnOnAction() {
		QnAVO vo = new QnAVO();
		int qa_index = 0;
		if(Qa_Title.getText().trim() != null && !Qa_Title.getText().trim().equals("") &&
				Qa_Content.getText().trim() != null && !Qa_Content.getText().trim().equals("")) {
			
			vo.setMem_mail(Session.memVO.getMem_mail());
			vo.setQa_title(Qa_Title.getText());
			vo.setQa_content(Qa_Content.getText());
			
			try {
				qa_index = iqs.insertQnAVO(vo);
				if(fvo.getFileData() != null) {
					avo.setQa_index(qa_index);
					insertImage(qa_index);
					ias.insertQaFile(avo);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("확인");
			alert.setHeaderText("Q&A 작성 완료.");
			alert.setContentText("작성한 글이 등록되었습니다.");
			alert.showAndWait();
						
			((Stage) Qna_Write_AnchorPane.getScene().getWindow()).close();
			
			Node node = null;
			try {
				node = new FXMLLoader(getClass().getResource("QnaList.fxml")).load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Session.Messenger.getChildren().setAll(node);
		}
			
		else {
			warningAlert();
		}
		
	}
	@FXML public void qnaAddImageBtnOnAction() throws IOException {
		FileChooser fc = new FileChooser();
		String saveFileDir = null;
		fc.setTitle("전송할 파일 선택");
		fc.getExtensionFilters().add(
			new FileChooser.ExtensionFilter("Files", "*.jpg", "*.png", "*.bmp")
		);
		
		fvo = new FileVO();
		file = fc.showOpenDialog(new Stage());
		
		FileInputStream fin = new FileInputStream(file);
		int len = (int)file.length();
		byte[] data = new byte[len];
		fin.read(data);
		fvo.setFileData(data);
		fvo.setFileName(file.getName());
		fvo.setMemVO(Session.memVO);
		
		if(fvo != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("확인");
			alert.setHeaderText("파일 첨부 완료.");
			alert.setContentText("파일 첨부가 완료되었습니다.");
			Qna_AddImage_Btn.setDisable(true);
			alert.showAndWait();
			
		}
	}
	
	void insertImage(int qa_index) {
		try {
			attachmentChk = ifs.setAttachment(2, fvo, qa_index);
			avo.setAcm_name(file.getName());
			avo.setAcm_type(file.getName().substring( file.getName().lastIndexOf(".")+1, file.getName().length() ));
			avo.setAcm_addr(attachmentChk);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void warningAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText("경고");
		alert.setContentText("비어있는 항목이 있습니다.");
		
		alert.showAndWait();
	}
}
