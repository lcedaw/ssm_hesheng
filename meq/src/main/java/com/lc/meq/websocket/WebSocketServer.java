package com.lc.meq.websocket;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/socket/{ip}")
@Component
public class WebSocketServer {

	//日志
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);
	
	//记录当前在线连接数
	private static int onLineCount = 0;
	
	//用来存放每个客户端对应的WebSocketServer对象
	private static ConcurrentHashMap<Session, WebSocketServer> webSocketMap = new ConcurrentHashMap<Session, WebSocketServer>();
	
	//某个客户端的连接回话，需要通过它来给客户端发送消息
	private Session session;
	
	//客户端的ip地址
	private String ip;
	
	//
	private String message;
	
	@OnOpen
	public void onOpen(@PathParam("ip") String ip, Session session) {
		this.session = session;
		this.ip = ip;
		webSocketMap.put(session, this);
		addOnlineCount();
		LOGGER.info("有新的连接加入，ip：{}，当前在线人数：{}",ip);
	}
	
	@OnClose
	public void onClose() {
		webSocketMap.remove(session);
		subOnlineCount();
		LOGGER.info("WebSocket关闭，ip:{}，当前在线人数：{}",ip);
	}
	
	@OnMessage
	public void onMessage(String message,Session session) {
		this.message = message;
		LOGGER.info("收到客户端的消息：{}",message);
	}
	
	@OnError
	public void onError(Session session,Throwable error) {
		LOGGER.info("WebSocket发生错误");
		error.printStackTrace();
	}
	
	public void sendMessage(String message) {
		try {
			//getBasicRemote()是同步发送消息，推荐使用getAsyncRemote()异步
			this.session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void sendMessageAll(final String message) {
		//使用entrySet而不是用keySet的原因是,entrySet体现了map的映射关系,遍历获取数据更快。
		Set<Map.Entry<Session, WebSocketServer>> entries = webSocketMap.entrySet();
		for (Map.Entry<Session, WebSocketServer> entry : entries) {
			final WebSocketServer webSocketServer = entry.getValue();
			//这里只用线程来控制消息的发送，这样效率更高
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					webSocketServer.sendMessage(message);
				}
			}).start();
		}
	}
	
	/**
	 * 获取当前的连接数
	 * @return
	 */
	public static synchronized int getOnlineCount() {
		return WebSocketServer.onLineCount;
	}
	
	/**
	 * 连接数加1
	 */
	public static synchronized void addOnlineCount() {
		WebSocketServer.onLineCount ++ ;
	}
	
	/**
	 * 连接数减1
	 */
	public static synchronized void subOnlineCount() {
		WebSocketServer.onLineCount --;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public static ConcurrentHashMap<Session, WebSocketServer> getWebSocketMap() {
		return webSocketMap;
	}

	public static void setWebSocketMap(ConcurrentHashMap<Session, WebSocketServer> webSocketMap) {
		WebSocketServer.webSocketMap = webSocketMap;
	}

}
