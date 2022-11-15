package com.javaone.javaserver.httpserver;
import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MyServer implements Runnable {

    Logger logger =  LoggerFactory.getLogger(MyServer.class);

    public void MyServerMain() throws IOException {
    }

    @SneakyThrows
    @Override
    public void run() {
        HttpServer server = HttpServer.create(new InetSocketAddress(8440), 0);
        server.setExecutor(Executors.newCachedThreadPool());
        server.createContext("/", new MyServerHandler());
        server.start();
        logger.info("Server started on port 8440");

    }
}
