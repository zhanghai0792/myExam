package com.jm.websocket;

import java.util.UUID;

public class websocketMessage {
 private static UUID idGeners=UUID.randomUUID();
 private String id=idGeners.toString();
 public static final int Msg_type_boast=1;
 public static final int Msg_type_person=0;
 private int type=1;//通知类型，1-1通信 0;广播通信1;
 private String className;//消息中蕴含的类名
 private Object msg;
 private Integer fromId;
 private Integer toId;
 private String eventName;//表示客户端派发什么样的事件
 public static final String StudenExamRecordStartEvent="StudenExamRecordStartEvent";
 public static final String OneStudenExamRecordEndEvent="OneStudenExamRecordEndEvent";
public static UUID getIdGeners() {
	return idGeners;
}
public String getId() {
	return id;
}
public int getType() {
	return type;
}
public String getClassName() {
	return className;
}
public Object getMsg() {
	return msg;
}
public Integer getFromId() {
	return fromId;
}
public Integer getToId() {
	return toId;
}
public static void setIdGeners(UUID idGeners) {
	websocketMessage.idGeners = idGeners;
}
public void setId(String id) {
	this.id = id;
}
public void setType(int type) {
	this.type = type;
}
public void setClassName(String className) {
	this.className = className;
}
public void setMsg(Object msg) {
	this.msg = msg;
}
public void setFromId(Integer fromId) {
	this.fromId = fromId;
}
public void setToId(Integer toId) {
	this.toId = toId;
}
 
 
}
