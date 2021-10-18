package com.thanglv.springtest.socket;

import com.thanglv.springtest.socket.handler.PrintHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PrinterServer extends Thread {
    private  Logger logger = LoggerFactory.getLogger(this.getClass());

    private ServerSocket server;
    private boolean isRun;

    public PrinterServer(int port) throws IOException {
        server = new ServerSocket(port);
        isRun = true;
    }

    @Override
    public void run() {
        while (isRun) {
            try {
                Socket client = server.accept();
                if (client.isConnected()) {
                    logger.debug("NEW DEVICES CONNECTED!");
                }
                new PrintHandler(client).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
