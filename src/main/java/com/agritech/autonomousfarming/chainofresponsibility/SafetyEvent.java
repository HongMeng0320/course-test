package com.agritech.autonomousfarming.chainofresponsibility;

import com.agritech.autonomousfarming.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 安全事件类
 * 用于责任链模式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SafetyEvent {
    private String id;
    private EventType type;
    private String description;
    private Position position;
    private int severity; // 1-5, 5最严重
    private long timestamp;
    
    public SafetyEvent(EventType type, String description, Position position, int severity) {
        this.id = generateId();
        this.type = type;
        this.description = description;
        this.position = position;
        this.severity = severity;
        this.timestamp = System.currentTimeMillis();
    }
    
    private String generateId() {
        return "SE-" + System.currentTimeMillis();
    }
    
    /**
     * 事件类型枚举
     */
    public enum EventType {
        OBSTACLE("障碍物"),
        BOUNDARY_VIOLATION("边界违规"),
        EQUIPMENT_FAILURE("设备故障"),
        COMMUNICATION_FAILURE("通信故障"),
        WEATHER_ALERT("天气警报"),
        COLLISION_RISK("碰撞风险");
        
        private final String displayName;
        
        EventType(String displayName) {
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
} 