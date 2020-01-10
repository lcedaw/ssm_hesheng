package com.lc.meq.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob {

	@Scheduled(cron = "* * * * * ?")
	public void task() {
		ConcurrentHashMap<Session, WebSocketServer> map = WebSocketServer.getWebSocketMap();
		if(map != null) {
			for(Map.Entry<Session, WebSocketServer> entry : map.entrySet()) {
				WebSocketServer webSocketServer = entry.getValue();
				try {
					webSocketServer.getSession().getBasicRemote().sendText(webSocketServer.getIp() + webSocketServer.getMessage());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}else {
			System.err.println("WebSocket未连接");
		}
	}
}
