package com.agritech.autonomousfarming.factory;

import com.agritech.autonomousfarming.model.*;

/**
 * 标准农机设备工厂实现类
 * 实现工厂方法模式
 */
public class StandardAgriculturalMachineFactory implements AgriculturalMachineFactory {
    
    @Override
    public AgriculturalMachine createMachine(String id, String name, MachineType type) {
        System.out.println("[农机工厂] 创建" + type.getDisplayName() + ": " + name + " (ID: " + id + ")");
        
        switch (type) {
            case TRACTOR:
                return new Tractor(id, name);
            case SEEDER:
                return new Seeder(id, name);
            case HARVESTER:
                return new Harvester(id, name);
            default:
                throw new IllegalArgumentException("不支持的农机类型: " + type);
        }
    }
} 