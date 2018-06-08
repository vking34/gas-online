package manager;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
//import javax.servlet.annotation.WebServlet;
//
//@WebServlet(name = "/takeOrder")

@ServerEndpoint(value = "/takeOrder")
public class orderWebSocket {

    @OnOpen
    public void onOpen(){
        System.out.println("Open connection...");
    }

    @OnClose
    public void onClose(){
        System.out.println("Close connetion...");
    }

    @OnMessage
    public String onMessage(String mess){
        System.out.println("Client: " + mess);
        String echoMess = "ok";
        return echoMess;
    }

    @OnError
    public void onError(Throwable e){
        e.printStackTrace();
    }
}
