package com.javaone.javaserver;

import com.javaone.javaserver.httpserver.MyServer;
import com.javaone.javaserver.httpserver.MyServerHandler;
import com.javaone.javaserver.servlet.MyServlet;
import com.sun.net.httpserver.HttpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

@SpringBootApplication
public class JavaServerApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(JavaServerApplication.class, args);
        MyServer server = new MyServer();
        server.run();
    }
}
