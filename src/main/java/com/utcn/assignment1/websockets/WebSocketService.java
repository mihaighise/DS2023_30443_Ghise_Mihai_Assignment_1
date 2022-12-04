package com.utcn.assignment1.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    SimpMessageSendingOperations simpMessageSendingOperations;

    public void sendNotification(Long id, WebSocketMessage message) {
        simpMessageSendingOperations.convertAndSendToUser( id.toString(),"/notification", message);
    }
}
