package kr.or.ddit.controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import kr.or.ddit.chatting.ChatServer;
import kr.or.ddit.chattingServer.ChatServerImpl;
import kr.or.ddit.dao.IMember;
import kr.or.ddit.dao.MemberImpl;
import kr.or.ddit.service.AttachmentService;
import kr.or.ddit.service.BackgroundImgListService;
import kr.or.ddit.service.BlockFriendService;
import kr.or.ddit.service.BookMarkService;
import kr.or.ddit.service.CategoryListService;
import kr.or.ddit.service.ChargeCashService;
import kr.or.ddit.service.ChatMessageService;
import kr.or.ddit.service.ChatRoomService;
import kr.or.ddit.service.ConfigurationService;
import kr.or.ddit.service.FAQService;
import kr.or.ddit.service.FavoriteService;
import kr.or.ddit.service.FileService;
import kr.or.ddit.service.FileListService;
import kr.or.ddit.service.FriendListService;
import kr.or.ddit.service.HideFriendService;
import kr.or.ddit.service.IAttachmentService;
import kr.or.ddit.service.IBackgroundImgListService;
import kr.or.ddit.service.IBlockFriendService;
import kr.or.ddit.service.IBookMarkService;
import kr.or.ddit.service.ICategoryListService;
import kr.or.ddit.service.IChargeCashService;
import kr.or.ddit.service.IChatMessageService;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IConfigurationService;
import kr.or.ddit.service.IFAQService;
import kr.or.ddit.service.IFavoriteService;
import kr.or.ddit.service.IFileService;
import kr.or.ddit.service.IFileListService;
import kr.or.ddit.service.IFriendListService;
import kr.or.ddit.service.IHideFriendService;
import kr.or.ddit.service.IInquireCmtService;
import kr.or.ddit.service.IInquireService;
import kr.or.ddit.service.INoticeCmtService;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.service.IOrderDetailListService;
import kr.or.ddit.service.IOrderDetailService;
import kr.or.ddit.service.IOrderListService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.service.IProfileImgListService;
import kr.or.ddit.service.IQnACmtService;
import kr.or.ddit.service.IQnAService;
import kr.or.ddit.service.IReviewCmtService;
import kr.or.ddit.service.IReviewService;
import kr.or.ddit.service.ISavePointService;
import kr.or.ddit.service.IShoppingCartService;
import kr.or.ddit.service.IUpdateHistoryService;
import kr.or.ddit.service.IUsingCashService;
import kr.or.ddit.service.IUsingPointService;
import kr.or.ddit.service.InquireCmtService;
import kr.or.ddit.service.InquireService;
import kr.or.ddit.service.NoticeCmtService;
import kr.or.ddit.service.NoticeService;
import kr.or.ddit.service.OrderDetailListService;
import kr.or.ddit.service.OrderDetailService;
import kr.or.ddit.service.OrderListService;
import kr.or.ddit.service.ParticipantService;
import kr.or.ddit.service.ProductListService;
import kr.or.ddit.service.ProfileImgListService;
import kr.or.ddit.service.QnACmtService;
import kr.or.ddit.service.QnAService;
import kr.or.ddit.service.ReviewCmtService;
import kr.or.ddit.service.ReviewService;
import kr.or.ddit.service.SavePointService;
import kr.or.ddit.service.ShoppingCartService;
import kr.or.ddit.service.UpdateHistoryService;
import kr.or.ddit.service.UsingCashService;
import kr.or.ddit.service.UsingPointService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.service.login.MemberService;

public class RmiServer extends UnicastRemoteObject {

	IMember memberDao;
	
	protected RmiServer() throws RemoteException {
		memberDao = MemberImpl.getInstance();
	}
	
	public static int acceptServer() {
		try{
//			System.setProperty("java.rmi.server.hostname","localhost");
			System.setProperty("java.rmi.server.hostname","192.168.41.24");

			Registry reg = LocateRegistry.createRegistry(8315);
			
			IAttachmentService attachment 				= AttachmentService.getInstance(); 
			IBackgroundImgListService backgroundImgList = BackgroundImgListService.getInstance();
			IBlockFriendService blockFriend 			= BlockFriendService.getInstance();
			IBookMarkService bookMark 					= BookMarkService.getInstance();
			ICategoryListService categoryList 			= CategoryListService.getInstance();
			IChargeCashService chargeCash 				= ChargeCashService.getInstance();
			IChatMessageService chatMessage 			= ChatMessageService.getInstance();
			IChatRoomService chatRoom 					= ChatRoomService.getInstance();
			IConfigurationService configuration 		= ConfigurationService.getInstance();
			IFAQService faq 							= FAQService.getInstance();
			IFavoriteService favorite					= FavoriteService.getInstance();
			IFileListService fileList					= FileListService.getInstance();
			IFriendListService friendList 				= FriendListService.getInstance();
			IHideFriendService hideFriend				= HideFriendService.getInstance();
			IInquireCmtService inquireCmt				= InquireCmtService.getInstance();
			IInquireService inquire						= InquireService.getInstance();
			IMemberService member						= MemberService.getInstance();
			INoticeCmtService noticeCmt					= NoticeCmtService.getInstance();
			INoticeService notice						= NoticeService.getInstance();
			IOrderDetailListService orderDetailList		= OrderDetailListService.getInstance();
			IOrderDetailService orderDetail				= OrderDetailService.getInstance();
			IOrderListService orderList					= OrderListService.getInstance();
			IParticipantService participant				= ParticipantService.getInstance();
			IProductListService productList				= ProductListService.getInstance();
			IProfileImgListService profileImgList		= ProfileImgListService.getInstance();
			IQnACmtService qnACmt						= QnACmtService.getInstance();
			IQnAService qnA								= QnAService.getInstance();
			IReviewCmtService reviewCmt					= ReviewCmtService.getInstance();
			IReviewService review						= ReviewService.getInstance();
			ISavePointService savePoint					= SavePointService.getInstance();
			IShoppingCartService shoppingCart			= ShoppingCartService.getInstance();
			IUpdateHistoryService updateHistory			= UpdateHistoryService.getInstance();
			IUsingCashService usingCash					= UsingCashService.getInstance();
			IUsingPointService usingPoint				= UsingPointService.getInstance();
			IFileService file							= FileService.getInstance();
			ChatServer server							= ChatServerImpl.getInstance();
			
			reg.rebind("attachment"			, attachment);
			reg.rebind("backgroundImgList"	, backgroundImgList);
			reg.rebind("blockFriend"		, blockFriend);
			reg.rebind("bookMark"			, bookMark);
			reg.rebind("categoryList"		, categoryList);
			reg.rebind("chargeCash"			, chargeCash);
			reg.rebind("chatMessage"		, chatMessage);
			reg.rebind("chatRoom"			, chatRoom);
			reg.rebind("configuration"		, configuration);
			reg.rebind("faq"				, faq);
			reg.rebind("favorite"			, favorite);
			reg.rebind("fileList"			, fileList);
			reg.rebind("friendList"			, friendList);
			reg.rebind("hideFriend"			, hideFriend);
			reg.rebind("inquireCmt"			, inquireCmt);
			reg.rebind("inquire"			, inquire);
			reg.rebind("member"				, member);
			reg.rebind("noticeCmt"			, noticeCmt);
			reg.rebind("notice"				, notice);
			reg.rebind("orderDetailList"	, orderDetailList);
			reg.rebind("orderDetail"		, orderDetail);
			reg.rebind("orderList"			, orderList);
			reg.rebind("participant"		, participant);
			reg.rebind("productList"		, productList);
			reg.rebind("profileImgList"		, profileImgList);
			reg.rebind("qnACmt"				, qnACmt);
			reg.rebind("qnA"				, qnA);
			reg.rebind("reviewCmt"			, reviewCmt);
			reg.rebind("review"				, review);
			reg.rebind("savePoint"			, savePoint);
			reg.rebind("shoppingCart"		, shoppingCart);
			reg.rebind("updateHistory"		, updateHistory);
			reg.rebind("usingCash"			, usingCash);
			reg.rebind("usingPoint"			, usingPoint);
			reg.rebind("file"				, file);
			reg.rebind("server"				, server);
			
			
			return 1;
       } catch (Exception e){

          System.out.println("ChatServerImpl error: " + e.getMessage());

          e.printStackTrace();

          return 0;
       }
		
	}
}
