package kr.or.ddit.view.shoppingMain.configurationList;

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

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kr.or.ddit.service.IBackgroundImgListService;
import kr.or.ddit.service.IFileService;
import kr.or.ddit.service.IProfileImgListService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.BackgroundImgListVO;
import kr.or.ddit.vo.FileVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProfileImgListVO;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ConfigurationProfileController implements Initializable {

	@FXML ImageView Conf_Background_Img;
	@FXML ImageView Conf_Forward_Img;
	@FXML TextField Conf_Mail_Text;
	@FXML TextField Conf_Nick_Text;
	@FXML TextField Conf_Message_Text;
	@FXML Button Conf_Apply_Btn;

	private IMemberService imember;
	private IFileService ifile;
	private IProfileImgListService ipil;
	private IBackgroundImgListService ibil;
	private Registry reg;
	@FXML ImageView Conf_Forward_Img_load;
	@FXML ImageView Conf_Background_Img_load;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Conf_Mail_Text.setText(Session.memVO.getMem_mail());
		Conf_Nick_Text.setText(Session.memVO.getMem_nick());
		Conf_Message_Text.setText(Session.memVO.getMem_message());
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			imember = (IMemberService)reg.lookup("member");
			ifile = (IFileService) reg.lookup("file");
			ipil = (IProfileImgListService)reg.lookup("profileImgList");
			ibil = (IBackgroundImgListService) reg.lookup("backgroundImgList");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		ProfileImgListVO vo = null;;
		try {
			vo = ipil.getProfileImgListVO(Session.memVO.getMem_mail());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(vo != null) {
			String dir = vo.getPf_addr(); //Session.ServerIPAddress + vo.getPf_addr();
			File file = new File(dir);
			if(file.exists() == false) {
				file = new File("src/image/기본프로필.png");
			}
			Image image = new Image(file.toURI().toString(), 150, 150, false, false, false);
			Conf_Forward_Img.setImage(image);
		}
		
		BackgroundImgListVO vo2 = null;
		vo2 = ibil.getBGI(Session.memVO.getMem_mail());
		if(vo2 != null) {
			String dir = vo2.getBg_addr();
			File file = new File(dir);
			if(file.exists() == false) {
				file = new File("src/image/기본배경이미지.png");
			}
			Image image = new Image(file.toURI().toString(), 400, 500, false, false, false);
			Conf_Background_Img.setImage(image);
		}else {
			File file = new File("src/image/기본배경이미지.png");
			Image image = new Image(file.toURI().toString(), 400, 500, false, false, false);
			Conf_Background_Img.setImage(image);
		}
		
	}

	@FXML public void ConfApplyBtnOnMouseClicked() {
		
		MemberVO vo = new MemberVO();
		vo.setMem_message(Conf_Message_Text.getText());
		vo.setMem_mail(Session.memVO.getMem_mail());
		vo.setMem_nick(Conf_Nick_Text.getText());
		Object obj = null;
		try {
			obj = (Object)imember.updateMemberVO(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(obj != null) {
			try {
				Session.memVO = (MemberVO)imember.idSearch(Session.memVO.getMem_mail());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("확인");
				alert.setContentText("적용되었습니다.");
				alert.showAndWait();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
	}

	@FXML public void ConfForwardImgloadOnMouseClicked() throws FileNotFoundException, IOException {
		
		FileChooser fc = new FileChooser();
		fc.setTitle("프로필 이미지 선택");
		fc.getExtensionFilters().add(
			new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.bmp")
		);
		FileVO vo = new FileVO();
		File file = fc.showOpenDialog(new Stage());
		vo.setFileName(file.getName());
		vo.setMemVO(Session.memVO);
		
		FileInputStream fin = new FileInputStream(file);
		int len = (int)file.length();
		byte[] data = new byte[len];
		fin.read(data);
		vo.setFileData(data);
		ifile.setFile(vo);
		
		Image image = new Image(file.toURI().toString(), 150, 150, false, false, false);
		Conf_Forward_Img.setImage(image);
		
		ProfileImgListVO pilvo = new ProfileImgListVO();
		pilvo.setMem_mail(Session.memVO.getMem_mail());
		String dir = Session.ServerIPAddress + "Filedata/" + vo.getMemVO().getMem_mail() + "/" + vo.getFileName();
		pilvo.setPf_addr(dir);
		
		ipil.insertProfileImgListVO(pilvo);
	}

	@FXML public void ConfBackgroundImgloadOnMouseClicked() throws IOException {
		FileChooser fc = new FileChooser();
		fc.setTitle("프로필 이미지 선택");
		fc.getExtensionFilters().add(
			new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.bmp")
		);
		FileVO vo = new FileVO();
		File file = fc.showOpenDialog(new Stage());
		vo.setFileName(file.getName());
		vo.setMemVO(Session.memVO);
		
		FileInputStream fin = new FileInputStream(file);
		int len = (int)file.length();
		byte[] data = new byte[len];
		fin.read(data);
		vo.setFileData(data);
		ifile.setFile(vo);
		
		Image image = new Image(file.toURI().toString(), 400, 500, false, false, false);
		Conf_Background_Img.setImage(image);
		
		BackgroundImgListVO bilVO = new BackgroundImgListVO();
		bilVO.setMem_mail(Session.memVO.getMem_mail());
		String dir = Session.ServerIPAddress + "Filedata/" + vo.getMemVO().getMem_mail() + "/" + vo.getFileName();
		bilVO.setBg_addr(dir);
		
		Object obj = ibil.insertBGI(bilVO);
		
	}

}
