package com.agritech.autonomousfarming.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 农机作业任务类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String id;
    private String name;
    private TaskType type;
    private Position startPosition;
    private Position endPosition;
    private LocalDateTime scheduledTime;
    private TaskStatus status;
    private String description;
    
    /**
     * 任务类型枚举
     */
    public enum TaskType {
        PLOWING("耕地"),
        SEEDING("播种"),
        SPRAYING("喷药"),
        HARVESTING("收割"),
        WEEDING("除草"),
        MONITORING("监测");
        
        private final String displayName;
        
        TaskType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    /**
     * 任务状态枚举
     */
    public enum TaskStatus {
        PENDING("待执行"),
        IN_PROGRESS("执行中"),
        COMPLETED("已完成"),
        FAILED("失败"),
        CANCELLED("已取消");
        
        private final String displayName;
        
        TaskStatus(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
} 