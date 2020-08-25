package kr.or.ddit.chatting;

import java.rmi.Remote;

import java.rmi.RemoteException;

import kr.or.ddit.vo.ChatMessageVO;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.MemberVO;

public interface ChatClient extends Remote {

	public void printMessage(ChatRoomVO cvo, ChatMessageVO cmvo) throws RemoteException;

}
