package com.wellington.springwebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp-endpoint")
                // .setAllowedOrigins("http://127.0.0.1:5500", "http://localhost:8080")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    // @Bean
    // public CorsFilter corsFilter() {
    // // UrlBasedCorsConfigurationSource source = new
    // // UrlBasedCorsConfigurationSource();
    // CorsConfiguration config = new CorsConfiguration();

    // // Defina as origens permitidas
    // config.addAllowedOrigin("http://127.0.0.1:5500");
    // config.addAllowedOrigin("http://localhost:8080");

    // // Outras configurações de CORS
    // // ...

    // // source.registerCorsConfiguration("/**", config);
    // return new CorsFilter();
    // }

}
