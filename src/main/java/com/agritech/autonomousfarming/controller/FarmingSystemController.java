package com.agritech.autonomousfarming.controller;

import com.agritech.autonomousfarming.factory.AgriculturalMachineFactory;
import com.agritech.autonomousfarming.factory.StandardAgriculturalMachineFactory;
import com.agritech.autonomousfarming.model.*;
import com.agritech.autonomousfarming.observer.EnvironmentMonitor;
import com.agritech.autonomousfarming.singleton.PositioningSystem;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 农机系统控制器
 * 提供Web接口
 */
@RestController
@RequestMapping("/api")
public class FarmingSystemController {
    
    private final AgriculturalMachineFactory factory;
    private final Map<String, AgriculturalMachine> machines;
    private final EnvironmentMonitor environmentMonitor;
    private final PositioningSystem positioningSystem;
    
    public FarmingSystemController() {
        this.factory = new StandardAgriculturalMachineFactory();
        this.machines = new HashMap<>();
        this.environmentMonitor = new EnvironmentMonitor("EM-001", "主农田环境监测器");
        this.positioningSystem = PositioningSystem.getInstance();
        this.positioningSystem.initialize(2.5);
        
        // 初始化一些示例农机
        initializeSampleMachines();
    }
    
    private void initializeSampleMachines() {
        AgriculturalMachine tractor = factory.createMachine("T-001", "智能拖拉机A型", MachineType.TRACTOR);
        AgriculturalMachine seeder = factory.createMachine("S-001", "精准播种机B型", MachineType.SEEDER);
        AgriculturalMachine harvester = factory.createMachine("H-001", "高效收割机C型", MachineType.HARVESTER);
        
        machines.put(tractor.getId(), tractor);
        machines.put(seeder.getId(), seeder);
        machines.put(harvester.getId(), harvester);
        
        environmentMonitor.registerObserver((Tractor)tractor);
        environmentMonitor.registerObserver((Seeder)seeder);
        environmentMonitor.registerObserver((Harvester)harvester);
        
        tractor.setPosition(new Position(100, 100));
        seeder.setPosition(new Position(200, 200));
        harvester.setPosition(new Position(300, 300));
        
        positioningSystem.updateMachinePosition(tractor.getId(), tractor.getPosition());
        positioningSystem.updateMachinePosition(seeder.getId(), seeder.getPosition());
        positioningSystem.updateMachinePosition(harvester.getId(), harvester.getPosition());
    }
    
    @GetMapping("/machines")
    public List<Map<String, Object>> getAllMachines() {
        List<Map<String, Object>> result = new ArrayList<>();
        
        for (AgriculturalMachine machine : machines.values()) {
            Map<String, Object> machineData = new HashMap<>();
            machineData.put("id", machine.getId());
            machineData.put("name", machine.getName());
            machineData.put("type", machine.getType());
            machineData.put("status", machine.getStatus());
            machineData.put("position", machine.getPosition());
            machineData.put("fuelLevel", machine.getFuelLevel());
            
            result.add(machineData);
        }
        
        return result;
    }
    
    @GetMapping("/machines/{id}")
    public Map<String, Object> getMachine(@PathVariable String id) {
        AgriculturalMachine machine = machines.get(id);
        
        if (machine == null) {
            throw new RuntimeException("农机不存在: " + id);
        }
        
        Map<String, Object> machineData = new HashMap<>();
        machineData.put("id", machine.getId());
        machineData.put("name", machine.getName());
        machineData.put("type", machine.getType());
        machineData.put("status", machine.getStatus());
        machineData.put("position", machine.getPosition());
        machineData.put("fuelLevel", machine.getFuelLevel());
        
        return machineData;
    }
    
    @PostMapping("/machines/{id}/move")
    public Map<String, Object> moveMachine(@PathVariable String id, @RequestBody Position position) {
        AgriculturalMachine machine = machines.get(id);
        
        if (machine == null) {
            throw new RuntimeException("农机不存在: " + id);
        }
        
        machine.moveTo(position);
        positioningSystem.updateMachinePosition(machine.getId(), position);
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "农机 " + machine.getName() + " 正在移动到位置 " + position);
        
        return result;
    }
    
    @PostMapping("/environment/update")
    public Map<String, Object> updateEnvironment(@RequestBody Map<String, Object> update) {
        String type = (String) update.get("type");
        String value = (String) update.get("value");
        
        switch (type) {
            case "soil_moisture":
                environmentMonitor.publishSoilMoistureUpdate(Double.parseDouble(value));
                break;
            case "soil_ph":
                environmentMonitor.publishSoilPhUpdate(Double.parseDouble(value));
                break;
            case "crop_maturity":
                environmentMonitor.publishCropMaturityUpdate(Integer.parseInt(value));
                break;
            case "weather":
                environmentMonitor.publishWeatherUpdate(value);
                break;
            case "obstacle":
                environmentMonitor.publishObstacleUpdate(value);
                break;
            default:
                throw new RuntimeException("不支持的环境更新类型: " + type);
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "环境更新已发布: " + type + " - " + value);
        
        return result;
    }
    
    @GetMapping("/system/status")
    public Map<String, Object> getSystemStatus() {
        Map<String, Object> status = new HashMap<>();
        
        status.put("machineCount", machines.size());
        status.put("positioningAccuracy", positioningSystem.getAccuracy());
        
        List<String> activeMachines = new ArrayList<>();
        for (AgriculturalMachine machine : machines.values()) {
            if (machine.getStatus() == MachineStatus.WORKING || 
                machine.getStatus() == MachineStatus.MOVING) {
                activeMachines.add(machine.getId());
            }
        }
        
        status.put("activeMachines", activeMachines);
        
        return status;
    }
} 