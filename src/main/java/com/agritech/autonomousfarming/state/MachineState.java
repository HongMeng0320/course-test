package com.agritech.autonomousfarming.state;

import com.agritech.autonomousfarming.model.Task;

/**
 * 机器状态接口
 * 实现状态模式
 */
public interface MachineState {
    /**
     * 处理任务
     * @param task 任务
     */
    void handleTask(Task task);
    
    /**
     * 处理移动
     */
    void handleMove();
    
    /**
     * 处理停止
     */
    void handleStop();
    
    /**
     * 处理故障
     * @param errorMessage 故障信息
     */
    void handleError(String errorMessage);
} 