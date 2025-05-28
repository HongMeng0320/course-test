package com.agritech.autonomousfarming.factory;

import com.agritech.autonomousfarming.model.AgriculturalMachine;
import com.agritech.autonomousfarming.model.MachineType;

/**
 * 农机设备工厂接口
 * 实现工厂方法模式
 */
public interface AgriculturalMachineFactory {
    /**
     * 创建农机设备
     * @param id 设备ID
     * @param name 设备名称
     * @param type 设备类型
     * @return 农机设备实例
     */
    AgriculturalMachine createMachine(String id, String name, MachineType type);
} 