package com.agritech.autonomousfarming.model;

/**
 * 农机设备类型枚举
 */
public enum MachineType {
    TRACTOR("无人驾驶拖拉机"),
    SEEDER("智能播种机"),
    HARVESTER("自动收割机"),
    SPRAYER("智能喷药机"),
    WEEDER("智能除草机");
    
    private final String displayName;
    
    MachineType(String displayName) {
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