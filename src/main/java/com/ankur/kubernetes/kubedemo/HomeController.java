package com.ankur.kubernetes.kubedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/home")
    public Map<String, Object> home() throws UnknownHostException {
        InetAddress server = InetAddress.getLocalHost();
        logger.info("Accessing Application Home");
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("status", "Running");
        data.put("datetime", Instant.now());
        data.put("host", server.getHostName());
        data.put("address", server.getHostAddress());
        return data;
    }
}
