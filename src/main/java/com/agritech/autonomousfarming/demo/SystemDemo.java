package com.agritech.autonomousfarming.demo;

import com.agritech.autonomousfarming.adapter.WeatherService;
import com.agritech.autonomousfarming.adapter.WeatherServiceAdapter;
import com.agritech.autonomousfarming.chainofresponsibility.SafetyEvent;
import com.agritech.autonomousfarming.chainofresponsibility.SafetyHandlerChain;
import com.agritech.autonomousfarming.command.CommandInvoker;
import com.agritech.autonomousfarming.command.MoveCommand;
import com.agritech.autonomousfarming.command.PerformTaskCommand;
import com.agritech.autonomousfarming.command.StopCommand;
import com.agritech.autonomousfarming.decorator.PrecisionFarmingDecorator;
import com.agritech.autonomousfarming.factory.AgriculturalMachineFactory;
import com.agritech.autonomousfarming.factory.StandardAgriculturalMachineFactory;
import com.agritech.autonomousfarming.model.*;
import com.agritech.autonomousfarming.observer.EnvironmentMonitor;
import com.agritech.autonomousfarming.singleton.PositioningSystem;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 系统演示类
 * 用于展示系统的运行
 */
@Component
public class SystemDemo implements CommandLineRunner {
    
    @Override
    public void run(String... args) {
        System.out.println("\n====== 智能无人驾驶农机系统演示 ======\n");
        
        // 1. 初始化定位系统（单例模式）
        System.out.println("【步骤1】初始化定位系统");
        PositioningSystem positioningSystem = PositioningSystem.getInstance();
        positioningSystem.initialize(2.5); // 精度2.5厘米
        System.out.println();
        
        // 2. 创建农机设备（工厂方法模式）
        System.out.println("【步骤2】创建农机设备");
        AgriculturalMachineFactory factory = new StandardAgriculturalMachineFactory();
        AgriculturalMachine tractor = factory.createMachine("T-001", "智能拖拉机A型", MachineType.TRACTOR);
        AgriculturalMachine seeder = factory.createMachine("S-001", "精准播种机B型", MachineType.SEEDER);
        AgriculturalMachine harvester = factory.createMachine("H-001", "高效收割机C型", MachineType.HARVESTER);
        System.out.println();
        
        // 3. 使用装饰器模式增强播种机功能
        System.out.println("【步骤3】使用装饰器模式增强播种机功能");
        AgriculturalMachine precisionSeeder = new PrecisionFarmingDecorator(seeder, 8.5);
        System.out.println("已为 " + seeder.getName() + " 添加精准农业功能");
        System.out.println();
        
        // 4. 创建环境监测器（观察者模式）
        System.out.println("【步骤4】创建环境监测器");
        EnvironmentMonitor environmentMonitor = new EnvironmentMonitor("EM-001", "主农田环境监测器");
        environmentMonitor.registerObserver((Tractor)tractor);
        environmentMonitor.registerObserver((Seeder)precisionSeeder);
        environmentMonitor.registerObserver((Harvester)harvester);
        System.out.println();
        
        // 5. 创建安全处理链（责任链模式）
        System.out.println("【步骤5】创建安全处理链");
        Position fieldTopLeft = new Position(0, 0);
        Position fieldBottomRight = new Position(1000, 1000);
        SafetyHandlerChain safetyChain = SafetyHandlerChain.createStandardChain(
            tractor, fieldTopLeft, fieldBottomRight);
        System.out.println();
        
        // 6. 创建命令调用者（命令模式）
        System.out.println("【步骤6】创建命令调用者");
        CommandInvoker commandInvoker = new CommandInvoker();
        System.out.println();
        
        // 7. 使用适配器模式集成第三方气象服务
        System.out.println("【步骤7】使用适配器模式集成第三方气象服务");
        WeatherService weatherService = new WeatherServiceAdapter();
        double temperature = weatherService.getTemperature(39.9, 116.3);
        double humidity = weatherService.getHumidity(39.9, 116.3);
        double windSpeed = weatherService.getWindSpeed(39.9, 116.3);
        String weatherDesc = weatherService.getWeatherDescription(39.9, 116.3);
        
        System.out.println("当前天气状况：");
        System.out.println("- 温度: " + String.format("%.1f", temperature) + "°C");
        System.out.println("- 湿度: " + String.format("%.1f", humidity) + "%");
        System.out.println("- 风速: " + String.format("%.1f", windSpeed) + "米/秒");
        System.out.println("- 天气: " + weatherDesc);
        System.out.println();
        
        // 8. 模拟系统运行
        System.out.println("【步骤8】模拟系统运行");
        
        // 8.1 更新农机位置到定位系统
        System.out.println("\n--- 更新农机位置 ---");
        tractor.setPosition(new Position(100, 100));
        precisionSeeder.setPosition(new Position(200, 200));
        harvester.setPosition(new Position(300, 300));
        
        positioningSystem.updateMachinePosition(tractor.getId(), tractor.getPosition());
        positioningSystem.updateMachinePosition(precisionSeeder.getId(), precisionSeeder.getPosition());
        positioningSystem.updateMachinePosition(harvester.getId(), harvester.getPosition());
        
        // 8.2 执行移动命令
        System.out.println("\n--- 执行移动命令 ---");
        commandInvoker.executeCommand(new MoveCommand(tractor, new Position(150, 150)));
        
        // 8.3 发布环境更新
        System.out.println("\n--- 发布环境更新 ---");
        environmentMonitor.publishSoilMoistureUpdate(75.5);
        environmentMonitor.publishWeatherUpdate(weatherDesc);
        
        // 8.4 创建并执行任务
        System.out.println("\n--- 创建并执行任务 ---");
        Task plowingTask = new Task(
            "T-001",
            "春季耕地任务",
            Task.TaskType.PLOWING,
            new Position(150, 150),
            new Position(250, 250),
            LocalDateTime.now(),
            Task.TaskStatus.PENDING,
            "对A区域进行深耕作业"
        );
        
        commandInvoker.executeCommand(new PerformTaskCommand(tractor, plowingTask));
        
        // 8.5 使用精准农业播种机执行任务
        System.out.println("\n--- 使用精准农业播种机执行任务 ---");
        Task seedingTask = new Task(
            "S-001",
            "精准播种任务",
            Task.TaskType.SEEDING,
            new Position(200, 200),
            new Position(300, 300),
            LocalDateTime.now(),
            Task.TaskStatus.PENDING,
            "对B区域进行精准播种作业"
        );
        
        commandInvoker.executeCommand(new PerformTaskCommand(precisionSeeder, seedingTask));
        
        // 8.6 处理安全事件
        System.out.println("\n--- 处理安全事件 ---");
        SafetyEvent obstacleEvent = new SafetyEvent(
            SafetyEvent.EventType.OBSTACLE,
            "检测到大石块",
            new Position(180, 180),
            3
        );
        
        safetyChain.handleEvent(obstacleEvent);
        
        // 8.7 发布更多环境更新
        System.out.println("\n--- 发布更多环境更新 ---");
        if (windSpeed > 5.0) {
            environmentMonitor.publishWeatherUpdate("大风");
        }
        
        // 8.8 执行停止命令
        System.out.println("\n--- 执行停止命令 ---");
        commandInvoker.executeCommand(new StopCommand(tractor));
        
        // 8.9 撤销命令
        System.out.println("\n--- 撤销命令 ---");
        commandInvoker.undoLastCommand();
        
        // 8.10 打印命令历史
        System.out.println("\n--- 打印命令历史 ---");
        commandInvoker.printCommandHistory();
        
        // 8.11 打印农机状态
        System.out.println("\n--- 打印农机状态 ---");
        tractor.printStatus();
        precisionSeeder.printStatus();
        harvester.printStatus();
        
        System.out.println("\n====== 演示结束 ======\n");
    }
} 