package kr.or.ddit.view.shoppingMain.chatQna;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.IAttachmentService;
import kr.or.ddit.service.IFileService;
import kr.or.ddit.service.IQnACmtService;
import kr.or.ddit.service.IQnAService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.FileVO;
import kr.or.ddit.vo.QnACmtVO;
import kr.or.ddit.vo.QnAVO;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Pagination;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class QnaListController implements Initializable{

	@FXML AnchorPane Qna_AnchorPane;
	@FXML VBox Qna_VBox;
	@FXML AnchorPane Content_AnchorPane;
	@FXML TextArea Qa_Title;
	@FXML TextField Qa_Index;
	@FXML TextField Qa_MemMail;
	@FXML TextField Qa_Date;
	@FXML TextArea Qa_Content;
	@FXML Button QnaCMT_Write_Btn;
	@FXML TextArea Qac_Content;
	@FXML TextArea Qac_Content_TextArea;
	@FXML Button Qna_Delete_Btn;
	@FXML Button Qna_Correct_Btn;
	@FXML AnchorPane Content_AnchorPane1;
	@FXML TextArea Qa_Title1;
	@FXML TextField Qa_Index1;
	@FXML TextField Qa_MemMail1;
	@FXML TextField Qa_Date1;
	@FXML TextArea Qa_Content1;
	@FXML Button QnaCMT_Write_Btn1;
	@FXML TextArea Qac_Content1;
	@FXML TextArea Qac_Content_TextArea1;
	@FXML Button Qna_Delete_Btn1;
	@FXML Button Qna_Correct_Btn1;
	@FXML Button Qna_Write_Btn;
	@FXML Pagination Qna_Pagination_Btn;
	@FXML Label subtitle;
	@FXML Button Qna_AttachDown_Btn;
	@FXML Button Qna_AttachDown_Btn1;

	private Registry reg;
	private IQnAService iqs;
	private IQnACmtService iqcs;
	private IAttachmentService iams;
	private IFileService ifs;
	List<QnAVO> myQnaList = new ArrayList<>();
	List<QnAVO> adminQnaList = new ArrayList<>();
	boolean btnChk = false;
	boolean btn1Chk = false;
	String addr;
	String addr1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// 초기값 수정 불가
		Qna_AttachDown_Btn.wrapTextProperty().setValue(true);
		Qna_AttachDown_Btn1.wrapTextProperty().setValue(true);
		Qna_AttachDown_Btn.setVisible(false);
		Qna_AttachDown_Btn1.setVisible(false);
		Qa_Title.setEditable(false);
		Qa_Index.setEditable(false);       
		Qa_MemMail.setEditable(false);         
		Qa_Date.setEditable(false);            
		Qa_Content.setEditable(false);         
		Qac_Content.setEditable(false);        
		Qa_Title1.setEditable(false);          
		Qa_Index1.setEditable(false);          
		Qa_MemMail1.setEditable(false);        
		Qa_Date1.setEditable(false);          
		Qa_Content1.setEditable(false);        
		Qac_Content1.setEditable(false);
		
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			iqs = (IQnAService) reg.lookup("qnA");
			iqcs = (IQnACmtService) reg.lookup("qnACmt");
			iams = (IAttachmentService) reg.lookup("attachment");
			ifs = (IFileService) reg.lookup("file");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 댓글 컨트롤
		cmtControl();
		// 게시글 정렬
		boardArr(1);
		// 페이지네이션 초기 세팅
		paginationControl();
		// 페이지네이션 이벤트
		Qna_Pagination_Btn.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(Qna_Pagination_Btn.getPageCount() == Qna_Pagination_Btn.getCurrentPageIndex()+1) {
					boardClear();
				}
				boardArr(Qna_Pagination_Btn.getCurrentPageIndex()+1);
			}
		});
		
	}
	/**
	 * Qna CMT 작성 버튼
	 * admin만 작성 가능
	 */
	@FXML public void qnaCMTwriterBtnOnAction() {
		if(!Session.memVO.getMem_mail().equals("admin")) {
			return; // 종료
		}
		insertCmt(0);
	}

	@FXML public void qnaDeleteBtnOnAction() {
		int num = deleteQna(Integer.parseInt(Qa_Index.getText()));
		if(num < 0 ) qnaListReload();
	}

	@FXML public void qnaCorrectBtnOnAction(ActionEvent event) {
		if(btnChk == true) {
			// 버튼이 이미 눌렸다면(확인버튼으로 바뀐상태)
			boardUpdate(0);
			return;
		}
		correctBtn(0);
	}
	
	@FXML public void qnaCMTwriterBtn1OnAction() {
		if(!Session.memVO.getMem_mail().equals("admin")) {
			return; // 종료
		}
		insertCmt(1);
	}

	@FXML public void qnaDeleteBtn1OnAction() {
		int num = deleteQna(Integer.parseInt(Qa_Index1.getText()));
		if(num < 0 ) qnaListReload();
	}
	
	@FXML public void qnaCorrectBtn1OnAction() {
		if(btn1Chk == true) {
			// 버튼이 이미 눌렸다면(확인버튼으로 바뀐상태)
			boardUpdate(1);
			return;
		}
		correctBtn(1);
	}
	
	public void correctBtn(int chk) {
		if(chk == 0) {
			// 0 => 상단 게시물이면
			btnChk = true;
			Qa_Title.setEditable(true);
			Qa_Content.setEditable(true);
			Qna_Correct_Btn.setText("확인");
		} else {
			// 1 => 하단 게시물이면
			btn1Chk = true;
			Qa_Title1.setEditable(true);
			Qa_Content1.setEditable(true);
			Qna_Correct_Btn1.setText("확인");
		}
			
	}
	/**
	 * Qna 작성화면 팝업
	 */
	@FXML public void qnaWriteBtnOnAction() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("QnaListWrite.fxml"));
		Parent root = null;
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		try {
			root = (Parent)loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Scene scene = new Scene(root);
		stage.setTitle("Q&A 작성");
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML public void qnaAttachDownBtnOnAction() {
		FileVO fvo = new FileVO();
		try {
			fvo = ifs.getFile(addr);
			
			FileOutputStream fout = null;
			FileChooser fc = new FileChooser();
			fc.setTitle("저장할 위치 선택");
			fc.setInitialFileName(fvo.getFileName());
			File dir = new File("C:/users/" + System.getProperty("user.name") + "/Documents/오카카톡 받은 파일");
			if (!dir.exists()) { 
				dir.mkdirs();
			}
			File initialDirectory = new File(dir.toURI());
			fc.setInitialDirectory(initialDirectory);
			File file = fc.showSaveDialog(new Stage());
			fout = new FileOutputStream(file.toString());
			fout.write(fvo.getFileData());
			fout.close();
			if (file != null) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("확인");
				alert.setHeaderText("다운로드 완료");
				alert.setContentText("선택한 파일의 다운로드가 완료되었습니다.");
				alert.showAndWait();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML public void qnaAttachDownBtn1OnAction() {
		FileVO fvo = new FileVO();
		try {
			fvo = ifs.getFile(addr1);
			
			FileOutputStream fout = null;
			FileChooser fc = new FileChooser();
			fc.setTitle("저장할 위치 선택");
			fc.setInitialFileName(fvo.getFileName());
			File dir = new File("C:/users/" + System.getProperty("user.name") + "/Documents/오카카톡 받은 파일");
			if (!dir.exists()) { 
				dir.mkdirs();
			}
			File initialDirectory = new File(dir.toURI());
			fc.setInitialDirectory(initialDirectory);
			File file = fc.showSaveDialog(new Stage());
			fout = new FileOutputStream(file.toString());
			fout.write(fvo.getFileData());
			fout.close();
			if (file != null) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("확인");
				alert.setHeaderText("다운로드 완료");
				alert.setContentText("선택한 파일의 다운로드가 완료되었습니다.");
				alert.showAndWait();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 게시판 정렬 
	 */
	public void boardArr(int idx) {
		
		if(Session.memVO.getMem_mail().equals("admin")) {
			try {
				adminQnaList = iqs.getQnaVOAll(idx);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			HashMap<String, Object> map = new HashMap<>();
			map.put("mem_mail", Session.memVO.getMem_mail());
			map.put("page", idx);
			try {
				myQnaList = iqs.getQnAVO(map);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < (myQnaList.size()!=0 ? myQnaList.size() : adminQnaList.size()); i++) {
			// 0, 1 => 최대길이 2
			// admin일때 전체목록 세팅 
			if(Session.memVO.getMem_mail().equals("admin")) {
				if(i == 1) {
					Qa_Title1.setText(adminQnaList.get(i).getQa_title());
					Qa_Index1.setText(String.valueOf(adminQnaList.get(i).getQa_index()));
					Qa_MemMail1.setText(adminQnaList.get(i).getMem_mail());
					Qa_Date1.setText(adminQnaList.get(i).getQa_date());
					Qa_Content1.setText(adminQnaList.get(i).getQa_content());
					Qac_Content1.setText(getCmt(adminQnaList.get(i).getQa_index()));
					try {
						addr1 = iams.selectQaFile(adminQnaList.get(i).getQa_index());
						if(addr1 != null && !addr1.equals("")) {
							Qna_AttachDown_Btn1.setVisible(true);
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				Qa_Title.setText(adminQnaList.get(i).getQa_title());
				Qa_Index.setText(String.valueOf(adminQnaList.get(i).getQa_index()));
				Qa_MemMail.setText(adminQnaList.get(i).getMem_mail());
				Qa_Date.setText(adminQnaList.get(i).getQa_date());
				Qa_Content.setText(adminQnaList.get(i).getQa_content());
				Qac_Content.setText(getCmt(adminQnaList.get(i).getQa_index()));
				try {
					addr = iams.selectQaFile(adminQnaList.get(i).getQa_index());
					if(addr != null && !addr.equals("")) {
						Qna_AttachDown_Btn.setVisible(true);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 일반 로그인일 때
			else {
				if(i == 1) {
					Qa_Title1.setText(myQnaList.get(i).getQa_title());
					Qa_Index1.setText(String.valueOf(myQnaList.get(i).getQa_index()));
					Qa_MemMail1.setText(myQnaList.get(i).getMem_mail());
					Qa_Date1.setText(myQnaList.get(i).getQa_date());
					Qa_Content1.setText(myQnaList.get(i).getQa_content());
					Qac_Content1.setText(getCmt(myQnaList.get(i).getQa_index())); // 댓글 세팅
					try {
						addr1 = iams.selectQaFile(myQnaList.get(i).getQa_index());
						if(addr1 != null && !addr1.equals("")) {
							Qna_AttachDown_Btn1.setVisible(true);
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				Qa_Title.setText(myQnaList.get(i).getQa_title());
				Qa_Index.setText(String.valueOf(myQnaList.get(i).getQa_index()));
				Qa_MemMail.setText(myQnaList.get(i).getMem_mail());
				Qa_Date.setText(myQnaList.get(i).getQa_date());
				Qa_Content.setText(myQnaList.get(i).getQa_content());
				Qac_Content.setText(getCmt(myQnaList.get(i).getQa_index())); // 댓글 세팅
				try {
					addr = iams.selectQaFile(myQnaList.get(i).getQa_index());
					if(addr != null && !addr.equals("")) {
						Qna_AttachDown_Btn.setVisible(true);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		// 글에 제목과 내용이없으면 버튼 비활성화
		if(Qa_Content.getText().equals("") && Qa_Title.getText().equals("")) {
			Qna_Correct_Btn.setDisable(true);
			Qna_Delete_Btn.setDisable(true);
			Qac_Content_TextArea.setDisable(true);
			QnaCMT_Write_Btn.setDisable(true);
		}
		if(Qa_Content1.getText().equals("") && Qa_Title1.getText().equals("")) {
			Qna_Correct_Btn1.setDisable(true);
			Qna_Delete_Btn1.setDisable(true);
			Qac_Content_TextArea1.setDisable(true);
			QnaCMT_Write_Btn1.setDisable(true);
		}
	}
	
	/**
	 * 댓글 버튼, 텍스트 컨트롤
	 */
	public void cmtControl() {
		if(!Session.memVO.getMem_mail().equals("admin")) {
			// 일반 회원은 답변을 막고
			Qac_Content_TextArea.setVisible(false);
			Qac_Content_TextArea1.setVisible(false);
			QnaCMT_Write_Btn.setVisible(false);
			QnaCMT_Write_Btn1.setVisible(false);
		}
		else {
			// 관리자는 수정을 막음
			Qna_Correct_Btn.setVisible(false);
			Qna_Correct_Btn1.setVisible(false);
			Qna_Write_Btn.setVisible(false);
			subtitle.setText("유저 Q&A 목록");
		}
	}
	
	/**
	 * Pagination 개수 세팅
	 */
	public void paginationControl() {
		if(Session.memVO.getMem_mail().equals("admin")) {
			try {
				Qna_Pagination_Btn.setPageCount( (iqs.adminPageCount()));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				Qna_Pagination_Btn.setPageCount(iqs.pageCount(Session.memVO.getMem_mail()));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 게시판 클리어
	 */
	public void boardClear() {
		Qa_Title1.clear();
		Qa_Index1.clear();
		Qa_MemMail1.clear();
		Qa_Date1.clear();
		Qa_Content1.clear();
		Qa_Title.clear();
		Qa_Index.clear();
		Qa_MemMail.clear();
		Qa_Date.clear();
		Qa_Content.clear();
	}
	
	/**
	 * Qna수정
	 */
	public void boardUpdate(int chk) {
		int num = 0;
		if(chk == 0) {
			QnAVO qvo = new QnAVO();
			qvo.setMem_mail(Session.memVO.getMem_mail());
			qvo.setQa_content(Qa_Content.getText());
			qvo.setQa_title(Qa_Title.getText());
			qvo.setQa_index(Integer.parseInt(Qa_Index.getText()));
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("확인");
			alert.setHeaderText("Q&A수정하기");
			alert.setContentText("정말로 수정 하시겠습니까?");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					num = iqs.updateQnAVO(qvo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Q&A 리스트 다시 불러오기
				qnaListReload();
				
				if(num > 0) {
					Qna_Correct_Btn.setText("수정");
					Qa_Content.setEditable(false);
					Qa_Title.setEditable(false);
					btnChk = false;
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("확인");
					alert.setHeaderText("Q&A수정 완료.");
					alert.setContentText("수정 완료 되었습니다.");
					alert.showAndWait();
				}
			} else {
				alert.close();
			}
		}
		else{
			QnAVO qvo = new QnAVO();
			qvo.setMem_mail(Session.memVO.getMem_mail());
			qvo.setQa_content(Qa_Content1.getText());
			qvo.setQa_title(Qa_Title1.getText());
			qvo.setQa_index(Integer.parseInt(Qa_Index1.getText()));
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("확인");
			alert.setHeaderText("Q&A수정하기");
			alert.setContentText("정말로 수정 하시겠습니까?");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					num = iqs.updateQnAVO(qvo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Q&A 리스트 다시 불러오기
				qnaListReload();
				
				if(num > 0) {
					Qna_Correct_Btn1.setText("수정");
					Qa_Content1.setEditable(false);
					Qa_Title1.setEditable(false);
					btn1Chk = false;
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("확인");
					alert.setHeaderText("Q&A수정하기");
					alert.setContentText("수정 완료 되었습니다.");
					alert.showAndWait();
				}
			} else {
				alert.close();
			}
		}
	}
	
	/**
	 * 관리자 댓글 달기
	 */
	public void insertCmt(int chk) {
		if(chk == 0) {
			// 상단
			int num = 0;
			if(Qac_Content_TextArea.getText().trim() != null 
					&& !Qac_Content_TextArea.getText().trim().equals("")){
				QnACmtVO qcvo = new QnACmtVO();
				qcvo.setMem_mail(Session.memVO.getMem_mail());
				qcvo.setQac_content(Qac_Content_TextArea.getText());
				qcvo.setQa_index(Integer.parseInt(Qa_Index.getText()));
				
				try {
					num = iqcs.insertQnACmtVO(qcvo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(num > 0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("확인");
					alert.setHeaderText("Q&A 답변 작성 완료.");
					alert.setContentText("작성한 답변이 등록되었습니다.");
					alert.showAndWait();
					Qac_Content.setText(Qac_Content_TextArea.getText());
					Qac_Content_TextArea.clear();
				}
			}
				
			else {
				warningAlert();
			}
		}
		
		else {
			// 하단
			int num = 0;
			if(Qac_Content_TextArea1.getText().trim() != null 
					&& !Qac_Content_TextArea1.getText().trim().equals("")){
				QnACmtVO qcvo = new QnACmtVO();
				qcvo.setMem_mail(Session.memVO.getMem_mail());
				qcvo.setQac_content(Qac_Content_TextArea1.getText());
				qcvo.setQa_index(Integer.parseInt(Qa_Index1.getText()));
				
				try {
					num = iqcs.insertQnACmtVO(qcvo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(num > 0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("확인");
					alert.setHeaderText("Q&A 답변 작성 완료.");
					alert.setContentText("작성한 답변이 등록되었습니다.");
					alert.showAndWait();
					Qac_Content1.setText(Qac_Content_TextArea1.getText());
					Qac_Content_TextArea1.clear();
				}
			}
				
			else {
				warningAlert();
			}
		}
	}

	String getCmt(int qa_index) {
		String str = "";
		QnACmtVO qavo = null;
		try {
			qavo = iqcs.selectQnACmtVO(qa_index);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			System.out.println(qa_index + "번째 글에 댓글이 없습니다.");
		}
		if(qavo != null) {
			str = qavo.getQac_content();
		}
		return str;
	}
	
	int deleteQna(int qa_index) {
		int num = 0;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("확인");
		alert.setHeaderText("Q&A 삭제하기");
		alert.setContentText("정말로 삭제 하시겠습니까?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				num = iqs.deleteQnAVO(qa_index);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(num < 0) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("확인");
				alert.setHeaderText("Q&A 삭제 완료.");
				alert.setContentText("작성한 Q&A가 삭제되었습니다.");
				alert.showAndWait();
			}
		}
		else {
			alert.close();
		}
		return num;
	}
	
	void qnaListReload() {
		Node qnaScene = null;
		try {
			qnaScene = FXMLLoader.load(getClass().getResource("QnaList.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Session.Messenger.getChildren().setAll(qnaScene);
	}
	
	void warningAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText("경고");
		alert.setContentText("비어있는 항목이 있습니다.");
	
		alert.showAndWait();
	}

}
