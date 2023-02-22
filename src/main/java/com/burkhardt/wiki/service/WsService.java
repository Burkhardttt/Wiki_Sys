package com.burkhardt.wiki.service;

import com.burkhardt.wiki.websocket.WebSocketServer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WsService {

	@Resource
	public WebSocketServer webSocketServer;

	@Async
	public void sendInfo(String message) {
		webSocketServer.sendInfo(message);
	}
}