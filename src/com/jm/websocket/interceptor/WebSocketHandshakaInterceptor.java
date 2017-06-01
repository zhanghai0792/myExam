package com.jm.websocket.interceptor;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.jm.pojo.User;

public class WebSocketHandshakaInterceptor extends
		HttpSessionHandshakeInterceptor {
	/*
	 * 链接服务器设置用户名 只会执行一次，发送消息是不执行
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		 
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest()
					.getSession(false);
			if (session != null) {
				// 使用userName区分WebSocketHandler，以便定向发送消息
				User user= ((User) session
						.getAttribute("user"));
				attributes.put(Constants.WEBSOCKET_USER_ID, user.getId());
				attributes.put(Constants.WEBSOCKET_USER_Name, user.getName());
				attributes.put(Constants.WEBSOCKET_USER_type, user.getType());
			System.out.println(user.getName()+"进入websocket的session中");
			}
		}
		return super.beforeHandshake(request, response, wsHandler, attributes);

	}

	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		super.afterHandshake(request, response, wsHandler, ex);
	}

}
