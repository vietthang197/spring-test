package com.thanglv.springtest.socket.handler;

import java.io.*;
import java.net.Socket;

public class PrintHandler extends Thread {
    private Socket client;
    private BufferedReader inFromClient;
    private boolean isRun;

    public PrintHandler(Socket client) {
        this.client = client;
        isRun = true;
    }

    @Override
    public void run() {
        InputStream inp = null;
        BufferedInputStream reader = null;
        DataOutputStream out = null;
        try {
            inp = client.getInputStream();
            reader = new BufferedInputStream(inp);
        } catch (IOException e) {
            return;
        }
        String line = null;
        while (isRun && client.isConnected()) {
            try {
                int nRead;
                byte[] data = new byte[1024];
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                while ((nRead = reader.read(data, 0, data.length)) != -1) {
                    outputStream.write(data, 0, nRead);
                }
                System.out.println(outputStream.toString());
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
