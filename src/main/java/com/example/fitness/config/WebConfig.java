//package com.example.fitness.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.*;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins(
//                            "http://localhost:3000",
//                            "https://fitness-tracker-frontend-iu0643h0w-ayushs-projects-4abd934e.vercel.app",
//                            "https://fitness-tracker-frontend-*.vercel.app" // For preview deployments
//                        )
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedHeaders("*")
//                        .allowCredentials(true)
//                        .maxAge(3600);
//            }
//        };
//    }
//}


package com.example.fitness.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                            "http://localhost:3000",
                            "https://fitness-tracker-frontend.vercel.app",
                            "https://fitness-tracker-frontend-iu0643h0w-ayushs-projects-4abd934e.vercel.app",
                            "https://fitness-tracker-frontend-git-main-ayushs-projects-4abd934e.vercel.app",
                            "https://fitness-tracker-frontend-e8kvc80c7-ayushs-projects-4abd934e.vercel.app"  // ← ADD THIS
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}