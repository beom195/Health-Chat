package com.beom195.health_chat.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    //세션에 담아두기 위한 clients 변수 생성
    private static final ConcurrentHashMap<String, WebSocketSession> clients = new ConcurrentHashMap<String, WebSocketSession>();

    //웹소켓 서버 접속시 동작
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        log.info("sessionId = {}", session.getId());
        //WebsocketSession 값 생 후 clients에 저장
        clients.put(session.getId(), session);

    }

    //웹소켓 서버 접속 종료시 동작 clients에 해당하는 세션 제거
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        clients.remove(session.getId());
    }

    //사용자의 메시지를 받으면 동작
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String id = session.getId(); //메시지 보낼 아이디
        clients.entrySet().forEach(arg ->{
            if(!arg.getKey().equals(id)){
                //clients에 담긴 세션 값과 같은 아이디가 아니면 메시지 전달
                try{
                    arg.getValue().sendMessage(message);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

    }
}
