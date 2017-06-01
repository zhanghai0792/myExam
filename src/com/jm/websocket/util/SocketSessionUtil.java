package com.jm.websocket.util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;


import com.jm.pojo.User;
import com.jm.util.JsonUtil;
import com.jm.websocket.websocketMessage;
import com.jm.websocket.interceptor.Constants;


public class SocketSessionUtil{
	private static  JsonUtil jsonUtil;
	private static Map<Integer, WebSocketSession> clients = new ConcurrentHashMap<>(0);
	

	/**
	 * 保存一个连接
	 * 
	 * @param inquiryId
	 * @param session
	 * @throws Exception
	 */
	public static void add(Integer userId, WebSocketSession session)
			throws Exception {
		if (hasConnection(getKey(userId))) {// refresh
			try {
				get(getKey(userId)).close();// close connection
				remove(getKey(userId));// remove connection
			} catch (IOException e) {
				throw new Exception(getKey(userId)
						+ "connection does not exit!");
			}
		}
		clients.put(getKey(userId), session);
		sendUserComing(userId);// 发送进入信息
	}

	/**
	 * 获取一个连接
	 * 
	 * @param inquiryId
	 * @return
	 */
	public static WebSocketSession get(Integer userId) {
		return clients.get(getKey(userId));
	}

	/**
	 * 移除一个连接
	 * 
	 * @param inquiryId
	 */
	public static void remove(Integer userId) throws IOException {
		clients.remove(getKey(userId));
		sendUserLeave(userId);// 发送离开信息
	}

	/**
	 * 组装sessionId
	 * 
	 * @param inquiryId
	 * @return
	 */
	public static Integer getKey(Integer userId) {
		return userId;
	}

	/**
	 * 判断是否有效连接 判断是否存在 判断连接是否开启 无效的进行清除
	 * 
	 * @param inquiryId
	 * @return
	 */
	public static boolean hasConnection(Integer userId) {
		Integer key = getKey(userId);
		if (clients.containsKey(key)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取连接数的数量
	 * 
	 * @return
	 */
	public static int getSize() {
		return clients.size();
	}

	/**
	 * 获得所有在线用户名
	 * 
	 * @return
	 */
	public static List<Integer> getUserIds() {
		List<Integer> ids = new ArrayList<Integer>(clients.keySet());
		return ids;
	}

	/**
	 * 用户进入聊天室消息0#message
	 * 
	 * @param username
	 */
	private static void sendUserComing(Integer userId) {
		
		websocketMessage messageObj=new websocketMessage();
		messageObj.setType(1);
		messageObj.setFromId(userId);
		messageObj.setMsg("用户" + userId + "进入系统");
		sendMessageToALL(messageObj);
	}

	/**
	 * 用户离开聊天室消息0#message
	 * 
	 * @param username
	 */
	private static void sendUserLeave(Integer userId) {
		websocketMessage messageObj=new websocketMessage();
		messageObj.setType(1);
		messageObj.setFromId(userId);
		messageObj.setMsg("用户" + userId + "离开系统");
		sendMessageToALL(messageObj);
	}

	/**
	 * 给某个用户发消息
	 * 
	 * @param username
	 *            接受消息的用户名
	 * @param message
	 * @throws Exception
	 */
	private static void sendMessagetoUser(Integer toId,String message)
			throws Exception {
		if (!hasConnection(toId)) {
			/*throw new NullPointerException(getKey(toId)
					+ " connection does not exist");*/
			System.out.println("用户不在线");
			return;
		}
		WebSocketSession session = get(toId);
		try {
			session.sendMessage(new TextMessage(message));
		} catch (IOException e) {
			clients.remove(getKey(toId));
		}
	}

	private static void sendMessagetoUser(websocketMessage message)
			throws Exception {
		 String msg=jsonUtil.getJsonString(message);
		 sendMessagetoUser(message.getToId(),msg);
	}
	
	
	public static void sendMessage(websocketMessage message)
			throws Exception {
		  if(message.getType()==message.Msg_type_boast){
			  sendMessageToALL(message);
		  }else{
			  sendMessagetoUser(message);
		  }
	}
	
	/**
	 * 给所有用户发送消息
	 *
	 * @param userName
	 *            客户端用户名
	 * @param message
	 *            发给客户端的消息
	 */
	
	private static void sendMessageToALL(websocketMessage message) {
		 if(message.getType()==message.Msg_type_boast){
			 sendMessageToALL(message.getFromId(),jsonUtil.getJsonString(message));
		 }
	}
	
	private static void sendMessageToALL(Integer userId, String message) {
		Set<Entry<Integer, WebSocketSession>> users = clients.entrySet();
		for (Entry<Integer, WebSocketSession> user : users) {
			WebSocketSession session = user.getValue();
			if (session.isOpen()) {
				if (user.getKey().equals(userId)) {// 不给自己发
					continue;
				}
				try {
					session.sendMessage(new TextMessage(message));
					System.out.println("服务器发送信息" + message.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {// 连接关闭，移除无效链接
				Integer id = user.getKey();
				clients.remove(id);
			}
		}
	}

	/**
	 * 获得客户端用户名
	 * 
	 * @param session
	 * @return
	 */
	public static Integer getID(WebSocketSession session) {
		String conntype = session.toString();
		Integer name = null;
		if (conntype.startsWith("SockJS")) {// sockjs
			HttpHeaders cookie = session.getHandshakeHeaders();
			name = analyzeIdGetCookie(cookie);
		} else {// websocket
			name = (Integer) session.getAttributes().get(
					Constants.WEBSOCKET_USER_ID);
		}
		return name;
	}

	public static String getName(WebSocketSession session) {
		String conntype = session.toString();
		String name = null;
		if (conntype.startsWith("SockJS")) {// sockjs
			HttpHeaders cookie = session.getHandshakeHeaders();
			name = analyzeNameGetCookie(cookie);
		} else {// websocket
			name = (String) session.getAttributes().get(
					Constants.WEBSOCKET_USER_Name);
		}
		return name;
	}
	
	/**
	 * 分析Sockjs获得客户端用户名
	 * 
	 * @param head
	 * @return
	 */
	private static Integer analyzeIdGetCookie(HttpHeaders head) {
		List<String> cookie = head.get("cookie");
		Integer id = null;
		// 比较奇葩的是 cookie并不是一个集合，而是一个字符串
		if (cookie.size() <= 1) {// 默认jsessionid
			String[] cookies = ((String) cookie.get(0)).split(";");
			for (String string : cookies) {
				if (string.trim().startsWith("userId")) {
					String name = string.substring(string.indexOf("=") + 1);
					id = Integer.parseInt(name);
				}
			}
		}
		return id;
	}

	private static String analyzeNameGetCookie(HttpHeaders head) {
		List<String> cookie = head.get("cookie");
		String name = null;
		// 比较奇葩的是 cookie并不是一个集合，而是一个字符串
		if (cookie.size() <= 1) {// 默认jsessionid
			String[] cookies = ((String) cookie.get(0)).split(";");
			for (String string : cookies) {
				if (string.trim().startsWith("userName")) {
					 name = string.substring(string.indexOf("=") + 1);
					name = unicode2String(name);
				}
			}
		}
		return name;
	}
	
	public static String unicode2String(String unicode) {
		List<String> list = new ArrayList<String>();
		String zz = "%u[0-9,a-z,A-Z]{4}";

		// 正则表达式用法参考API
		Pattern pattern = Pattern.compile(zz);
		Matcher m = pattern.matcher(unicode);
		while (m.find()) {
			list.add(m.group());
		}
		for (int i = 0, j = 2; i < list.size(); i++) {
			String st = list.get(i).substring(j, j + 4);

			// 将得到的数值按照16进制解析为十进制整数，再強转为字符
			char ch = (char) Integer.parseInt(st, 16);
			// 用得到的字符替换编码表达式
			unicode = unicode.replace(list.get(i), String.valueOf(ch));
		}
		return unicode;
	}

	/**
	 * @param name
	 *            客户端姓名
	 * @param string
	 *            客户端发来的消息
	 */
	/*public static void sendMessage(Integer id, websocketMessage message) throws Exception {
		  String msg=jsonUtil.getJsonString(message);
		if (message.getType()==1) {// 公聊1#name@message
			
			sendMessageToALL(message.getFromId(), msg);
		} else {// 私聊
			
			sendMessagetoUser(message.getToId(), msg);
		}
	}*/

	private static boolean canSpeak(String name) throws Exception {
		/*ISpeakService service = (ISpeakService) beanFactory
				.getBean("ISpeakService");
		boolean result = service.canSpeak(new User(name, null, null));
		if (!result) {// 被禁言
			String message = SYSTEM_MSG + "#" + "对不起您被禁言！";
			sendMessagetoUser(name, message);
			return false;
		}*/
		return true;
	}

	/**
	 * 解析客户端消息,返回消息方式
	 * 
	 * @param string
	 *            name@message
	 * @return
	 */
/*	private static Map<String, String> analyzeMessage(String string) {
		String[] result = string.trim().split("@");
		Map<String, String> back = new HashMap<String, String>();
		if (result.length == 1) {// 非法发送空字符
			return null;//
		}
		back.put("toName", result[0].trim());
		back.put("message", result[1]);
		return back;
	}*/



}
