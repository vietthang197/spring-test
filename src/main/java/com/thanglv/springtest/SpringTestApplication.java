package com.thanglv.springtest;

import com.thanglv.springtest.socket.PrinterServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringTestApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringTestApplication.class, args);
        PrinterServer printerServer = new PrinterServer(9100);
        printerServer.start();
    }

}
