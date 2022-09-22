package com.example.catalogmicroservice.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Greeting {

    @Value("${greeting.message}")
    private String message;

    private String serverPort;

    public void setServerPort(String serverPort){
        this.serverPort = serverPort;
    }
}

@Service
class ServerPortService {
    private Greeting greeting;

    public ServerPortService(Greeting greeting) {
        this.greeting = greeting;
    }

    @EventListener
    public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
        this.greeting.setServerPort(String.valueOf(event.getWebServer().getPort()));
    }
}

