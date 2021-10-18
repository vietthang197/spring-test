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
        BufferedReader reader = null;
        DataOutputStream out = null;
        try {
            inp = client.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String line = null;
        while (isRun && client.isConnected()) {
            try {
                line = reader.readLine();
                if (line != null) {
                    System.out.println(line);
                }
                if (line.equals("PRINT 1"))
                    isRun = false;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
