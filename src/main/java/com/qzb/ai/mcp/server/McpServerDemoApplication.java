package com.qzb.ai.mcp.server;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootApplication
public class McpServerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerDemoApplication.class, args);
    }

    @Bean
    public List<ToolCallback> cityCallback(CityServer cityServer) {
        return List.of(ToolCallbacks.from(cityServer));
    }

//    @Bean
//    public ToolCallbackProvider weatherTools(CityServer cityServer) {
//        return MethodToolCallbackProvider.builder()
//                .toolObjects(cityServer)
//                .build();
//    }

    @Bean
    public ToolCallbackProvider weatherTools(OpenMeteoService openMeteoService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(openMeteoService)
                .build();
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
