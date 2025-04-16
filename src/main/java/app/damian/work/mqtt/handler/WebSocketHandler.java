package app.damian.work.mqtt.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LogManager.getLogger(WebSocketHandler.class);

    private static final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    public static void sendToAll(String json) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(json));
            } catch (IOException e) {
                logger.error("Error while sending message to all connected sessions.", e);
            }
        }
    }
}
