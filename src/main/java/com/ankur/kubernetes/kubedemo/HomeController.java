package com.ankur.kubernetes.kubedemo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private Environment env;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> home(Principal user) throws UnknownHostException {
        InetAddress server = InetAddress.getLocalHost();
        logger.info("{} accessing application home", getUsername(user));
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("status", "Running");
        data.put("datetime", Instant.now());
        data.put("host", server.getHostName());
        data.put("address", server.getHostAddress());
        data.put("user", getUsername(user));
        return data;
    }

    @RequestMapping(path = "/api")
    public String api(Principal user) {
        logger.info("{} accessing application API", getUsername(user));
        return "Port " + env.getProperty("server.port") + ": No API yet. Back to <a href='/'>home</a>";
    }
    
    private String getUsername(Principal user) {
	return user == null ? "Random user" : user.getName();
    }
}
