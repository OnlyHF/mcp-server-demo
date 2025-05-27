package com.qzb.ai.mcp.server;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpServerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerDemoApplication.class, args);
    }

//    @Bean
//    public List<ToolCallback> cityCallback(CityServer cityServer) {
//        return List.of(ToolCallbacks.from(cityServer));
//    }

//    @Bean
//    public ToolCallbackProvider cityTools(CityServer cityServer) {
//        return MethodToolCallbackProvider.builder()
//                .toolObjects(cityServer)
//                .build();
//    }

//    @Bean
//    public ToolCallbackProvider toolCallbackProvider(OpenMeteoService openMeteoService, CityServer cityServer) {
//        return MethodToolCallbackProvider.builder()
//                .toolObjects(cityServer, openMeteoService)
//                .build();
//    }

    @Bean
    public ToolCallbackProvider toolCallbackProvider(CommandService commandService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(commandService)
                .build();
    }

//    @Bean
//    public WebClient.Builder webClientBuilder() {
//        return WebClient.builder();
//    }

}
