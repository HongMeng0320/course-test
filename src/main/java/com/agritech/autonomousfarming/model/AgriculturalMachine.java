package com.agritech.autonomousfarming.model;

import com.agritech.autonomousfarming.observer.EnvironmentObserver;
import com.agritech.autonomousfarming.observer.EnvironmentUpdate;

/**
 * 农机设备接口
 * 定义所有农机设备必须实现的基本功能
 */
public interface AgriculturalMachine extends EnvironmentObserver {
    /**
     * 获取设备ID
     */
    String getId();
    
    /**
     * 获取设备名称
     */
    String getName();
    
    /**
     * 获取设备类型
     */
    MachineType getType();
    
    /**
     * 获取设备位置
     */
    Position getPosition();
    
    /**
     * 设置设备位置
     */
    void setPosition(Position position);
    
    /**
     * 获取设备状态
     */
    MachineStatus getStatus();
    
    /**
     * 设置设备状态
     */
    void setStatus(MachineStatus status);
    
    /**
     * 获取设备油量
     */
    double getFuelLevel();
    
    /**
     * 设置设备油量
     */
    void setFuelLevel(double fuelLevel);
    
    /**
     * 执行作业
     */
    void performTask(Task task);
    
    /**
     * 移动到指定位置
     */
    void moveTo(Position position);
    
    /**
     * 打印设备状态
     */
    void printStatus();
} 