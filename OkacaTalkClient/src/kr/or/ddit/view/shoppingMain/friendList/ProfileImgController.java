package kr.or.ddit.view.shoppingMain.friendList;

import java.io.File;
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
import javafx.scene.input.ScrollEvent;
import kr.or.ddit.service.IProfileImgListService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProfileImgListVO;
import javafx.scene.control.ScrollPane;

public class ProfileImgController implements Initializable{

	@FXML ImageView ProfileImg_Img;
	MemberVO vo;
	IProfileImgListService ipil;
	Registry reg;
	@FXML ScrollPane ProfileImg_pane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		vo = Session.memVO;
//		if(Session.selectedFriendListVO != null) {
//			vo = Session.selectedFriendListVO;
//		}
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ipil = (IProfileImgListService) reg.lookup("profileImgList");
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	@FXML public void ProfileImgOnScroll(ScrollEvent event) {
		double zoomScale = 1.05;
		double deltaY = event.getDeltaY();
		
		if(deltaY < 0) {
			zoomScale = 0.95;
		}
		ProfileImg_pane.setScaleX(ProfileImg_pane.getScaleX() * zoomScale);
		ProfileImg_pane.setScaleY(ProfileImg_pane.getScaleY() * zoomScale);
		event.consume();
		
	}

	public void initData(MemberVO vo2) {
		vo = vo2;
		
		ProfileImgListVO pvo = null;
		try {
			pvo = ipil.getProfileImgListVO(vo.getMem_mail());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(pvo != null) {
			String dir = pvo.getPf_addr(); //pvo.getPf_addr(); //Session.ServerIPAddress + vo.getPf_addr();
			File file = new File(dir);
			if(file.exists() == false) {
				file = new File("src/image/기본프로필.png");
			}
			Image image = new Image(file.toURI().toString());
			ProfileImg_Img.setImage(image);
		}
	}

}
