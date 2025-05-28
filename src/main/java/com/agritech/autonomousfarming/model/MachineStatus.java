package com.agritech.autonomousfarming.model;

/**
 * 农机设备状态枚举
 */
public enum MachineStatus {
    IDLE("空闲"),
    MOVING("移动中"),
    WORKING("作业中"),
    CHARGING("充电中"),
    REFUELING("加油中"),
    MAINTENANCE("维护中"),
    ERROR("故障");
    
    private final String displayName;
    
    MachineStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
} 