package com.jm.websocket;


import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.jm.websocket.util.SocketSessionUtil;

public class WebSocketHandler extends TextWebSocketHandler {
	public WebSocketHandler() {
	}

	/*
	 * 建立连接
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		Integer id = SocketSessionUtil.getID(session);
		System.out.println("用户" + id + "连入服务器");
		SocketSessionUtil.add(id, session);
	}

	/*
	 * 收到客户端消息
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		String name = SocketSessionUtil.getName(session);
		System.out.println("WebSocketHandler 处理"+name+"发送的消息"+message.getPayload().toString());
		//SocketSessionUtil.sendMessage(name, message.getPayload().toString());//待修改
	}

	/*
	 * 出现异常
	 */
	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		Integer user = SocketSessionUtil.getID(session);
		SocketSessionUtil.remove(user);
		exception.printStackTrace();
	}

	/*
	 * 连接关闭
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		Integer id = SocketSessionUtil.getID(session);
		System.out.println("用户" + id + "断开服务器");
		SocketSessionUtil.remove(id);
	}

	/*
	 * 是否分段发送消息
	 */
	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
