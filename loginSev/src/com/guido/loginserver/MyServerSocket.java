package com.guido.loginserver;


public class MyServerSocket {

    public static void main(String[] args) {
        new ConnectSocket().start();
        new TxSocket().start();
    }

}