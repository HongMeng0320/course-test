package com.agritech.autonomousfarming.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 环境更新信息类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentUpdate {
    private EnvironmentUpdateType type;
    private String value;
    private Position position;
    private long timestamp;
    
    public EnvironmentUpdate(EnvironmentUpdateType type, String value) {
        this.type = type;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }
    
    /**
     * 环境更新类型枚举
     */
    public enum EnvironmentUpdateType {
        SOIL_MOISTURE("土壤湿度"),
        SOIL_PH("土壤pH值"),
        SOIL_NUTRIENT("土壤养分"),
        CROP_MATURITY("作物成熟度"),
        CROP_HEALTH("作物健康状况"),
        WEATHER("气象数据"),
        OBSTACLE("障碍物"),
        TEMPERATURE("温度"),
        HUMIDITY("湿度"),
        WIND_SPEED("风速");
        
        private final String displayName;
        
        EnvironmentUpdateType(String displayName) {
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
    
    /**
     * 位置类
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Position {
        private double x;
        private double y;
        
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
} 