package com.guido.loginserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ConnectSocket extends Thread {
    @Override
    public void run() {
    	ServerSocket serversocket=null;
        try {
            //创建绑定到特定端口的服务器套接字  1-65535
            serversocket = new ServerSocket(5510);
            while(true) {
                //建立连接，获取socket对象
                Socket socket=serversocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

                String line=null;
                while((line=br.readLine())!=null)
                {
                    System.out.println(line);
                    //ChatManager.GetChatManager().Send(this, line);
                }
                br.close();
                //消息提示框
                //JOptionPane.showMessageDialog(null, "有客户端连接到了本机62224端口哦");
                //与客户端通信
                //ChatSocket cs=new ChatSocket(socket);
                //cs.start();
                //添加socket到队列
                //ChatManager.GetChatManager().AddChatPeople(cs);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
				if(serversocket!=null)
					serversocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }finally {
        	try {
        		if(serversocket!=null)
        			serversocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }


    }
}
