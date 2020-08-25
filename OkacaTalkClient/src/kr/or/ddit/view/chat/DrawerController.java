package kr.or.ddit.view.chat;

import java.io.File;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.service.IProfileImgListService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ParticipantVO;
import kr.or.ddit.vo.ProfileImgListVO;

public class DrawerController implements Initializable{

	@FXML AnchorPane Participant_List_AnchorPane;
	@FXML VBox Participant_List_VBox;

	private Registry reg;
	private IParticipantService ips;
	private IMemberService ims;
	private IProfileImgListService ipils;
	
	private List<ParticipantVO> partiList = new ArrayList<>();
	private List<MemberVO> partiMemList = new ArrayList<>();
	private List<ProfileImgListVO> profileImagVOList = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ips = (IParticipantService) reg.lookup("participant");
			ims = (IMemberService) reg.lookup("member");
			ipils = (IProfileImgListService)reg.lookup("profileImgList");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 햄버거 메뉴를 누른 ChatRoomVO 가져오기
		ChatRoomVO crvo = Session.seletedCrvoForHamburgerMenu;
		
		try {
			partiList = ips.getParticipantVO(crvo.getCr_index());
			for(ParticipantVO ippvo : partiList) {
				partiMemList.add(ims.idSearch(ippvo.getMem_mail()));
				// 참여인원의 프로필 사진 VO 가져오기
				ProfileImgListVO vo = ipils.getProfileImgListVO(ippvo.getMem_mail());
				if( vo != null) {
					profileImagVOList.add(vo);
				}
				else {
					vo = new ProfileImgListVO();
					vo.setPf_addr("1");
					vo.setMem_mail("NotExist");
					profileImagVOList.add(vo);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// partiList의 Size만큼 for문을 돌아서 HBox를 생성후 VBox에 add
		for(int i = 0; i < partiList.size(); i++) {
			HBox hbox = new HBox();
			ImageView profileImg = new ImageView();
			for(int j = 0; j < profileImagVOList.size(); j++) {
				if(profileImagVOList.get(j).getMem_mail().equals(partiMemList.get(i).getMem_mail())) {
					File imgFile = new File(profileImagVOList.get(j).getPf_addr());
					if(imgFile.exists() == false) {
						imgFile = new File("src/image/기본프로필.png");
						Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
						profileImg.setImage(img);
					}
					else {
						Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
						profileImg.setImage(img);
					}
					break;
				}
				else {
					File imgFile = new File("src/image/기본프로필.png");
					Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
					profileImg.setImage(img);
				}
			}
			hbox.getChildren().add(profileImg);
			Participant_List_VBox.getChildren().add(hbox);
		}
		
	}

}
