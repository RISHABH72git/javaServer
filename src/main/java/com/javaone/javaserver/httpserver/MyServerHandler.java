package com.javaone.javaserver.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MyServerHandler implements HttpHandler{
    Logger logger =  LoggerFactory.getLogger(MyServerHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        logger.info("Request Method .."+httpExchange.getRequestMethod());
        logger.info("Request of Remote socket  ["+httpExchange.getRemoteAddress().getAddress().toString()+":"+httpExchange.getRemoteAddress().getPort()+"]");
        logger.info("Request of URI -> "+httpExchange.getRequestURI().toString());
        String path = httpExchange.getRequestURI().getPath();
        if (path.equals("/")){
            File file = new File("/var/www/html/index.html");
            String response = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            String encoding = "UTF-8";
            httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=" + encoding);
            httpExchange.getResponseHeaders().set("Accept-Ranges", "bytes");
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }else {
            File file = new File("/var/www/html"+path);
            if (file.exists()) {
                String response = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                String encoding = "UTF-8";
                httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=" + encoding);
                httpExchange.getResponseHeaders().set("Accept-Ranges", "bytes");
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes("UTF-8"));
                os.close();
            }else {
                String stringBuilder = "404" + "" +
                        "page not found";
                String response = StringEscapeUtils.unescapeHtml4(stringBuilder);
                String encoding = "UTF-8";
                httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=" + encoding);
                httpExchange.getResponseHeaders().set("Accept-Ranges", "bytes");
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes("UTF-8"));
                os.close();
            }
        }
    }
}
