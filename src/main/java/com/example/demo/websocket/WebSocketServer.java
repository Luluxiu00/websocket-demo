package com.example.demo.websocket;
 
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.MessageEntity;
import com.example.demo.service.IMessageService;
import com.example.demo.service.impl.MessageService;
import com.example.demo.util.ApplicationContextUtils;

import groovy.util.logging.Slf4j;


@Slf4j
@ServerEndpoint(value = "/websocket/{user}")
@Component
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
 
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    
    private MessageService messageservice = (MessageService) ApplicationContextUtils.getBean(IMessageService.class);
 
    
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
        	 sendMessage("连接成功");
        } catch (IOException e) {
        	logger.error("websocket IO异常");
        }
    }
 
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
 
    
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
    	logger.info("来自客户端的消息:" + message);
 
        //群发消息
    	try {
    		MessageEntity entity = new MessageEntity();
    		entity.setContent(message);
    		entity.setCreateById(1l);
			messageservice.save(entity );
    		
			sendMessage("收到消息了");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
	/**
	 * 
	 * @param session
	 * @param error
	 */
    @OnError
    public void onError(Session session, Throwable error) {
    	logger.error("发生错误");
        error.printStackTrace();
    }
 
 
    /**
     * 发给单个用户
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
 
 
    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
    	logger.info(message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }
 
    
    
    /**
     * 获取当前在线连接数
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    
    /**
     * 当前在线连接数+1
     */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
 
    /**
     * 当前在线连接数-1
     */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
