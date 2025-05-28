package com.agritech.autonomousfarming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 智能无人驾驶农机系统主应用类
 */
@SpringBootApplication
public class AutonomousFarmingApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutonomousFarmingApplication.class, args);
        System.out.println("=================================================");
        System.out.println("    智能无人驾驶农机系统启动成功");
        System.out.println("    访问地址: http://localhost:8080");
        System.out.println("=================================================");
    }
} 