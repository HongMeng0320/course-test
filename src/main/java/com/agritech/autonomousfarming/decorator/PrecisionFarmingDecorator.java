package com.agritech.autonomousfarming.decorator;

import com.agritech.autonomousfarming.model.AgriculturalMachine;
import com.agritech.autonomousfarming.model.Position;
import com.agritech.autonomousfarming.model.Task;
import com.agritech.autonomousfarming.observer.EnvironmentObserver;
import com.agritech.autonomousfarming.observer.EnvironmentUpdate;

/**
 * 精准农业装饰器类
 * 为农机增加精准农业功能
 */
public class PrecisionFarmingDecorator extends MachineDecorator implements EnvironmentObserver {
    private double precisionLevel; // 精准度级别，1-10
    
    public PrecisionFarmingDecorator(AgriculturalMachine decoratedMachine, double precisionLevel) {
        super(decoratedMachine);
        this.precisionLevel = Math.min(10, Math.max(1, precisionLevel)); // 确保精准度在1-10之间
    }
    
    @Override
    public void performTask(Task task) {
        System.out.println("[精准农业] " + getName() + " 使用精准农业技术执行任务: " + task.getName());
        analyzeFieldData();
        optimizeTaskExecution(task);
        super.performTask(task);
        recordTaskData(task);
    }
    
    @Override
    public void handleEnvironmentUpdate(EnvironmentUpdate update) {
        System.out.println("[精准农业] " + getName() + " 精准分析环境数据: " + update.getType());
        analyzeEnvironmentData(update);
        super.handleEnvironmentUpdate(update);
    }
    
    @Override
    public void moveTo(Position position) {
        System.out.println("[精准农业] " + getName() + " 使用高精度导航移动到: " + position);
        Position optimizedPosition = optimizePosition(position);
        super.moveTo(optimizedPosition);
    }
    
    @Override
    public void printStatus() {
        super.printStatus();
        System.out.println("精准农业级别: " + precisionLevel);
    }
    
    /**
     * 分析农田数据
     */
    private void analyzeFieldData() {
        System.out.println("[精准农业] 分析农田数据，精准度: " + precisionLevel);
    }
    
    /**
     * 优化任务执行
     */
    private void optimizeTaskExecution(Task task) {
        System.out.println("[精准农业] 根据农田数据优化任务执行计划: " + task.getName());
    }
    
    /**
     * 记录任务数据
     */
    private void recordTaskData(Task task) {
        System.out.println("[精准农业] 记录任务执行数据用于后续分析: " + task.getName());
    }
    
    /**
     * 分析环境数据
     */
    private void analyzeEnvironmentData(EnvironmentUpdate update) {
        System.out.println("[精准农业] 深度分析环境数据: " + update.getType() + " - " + update.getValue());
    }
    
    /**
     * 优化位置
     */
    private Position optimizePosition(Position position) {
        // 根据精准度级别微调位置
        double adjustment = 0.1 / precisionLevel; // 精准度越高，调整越小
        return new Position(
            position.getX() + (Math.random() * 2 - 1) * adjustment,
            position.getY() + (Math.random() * 2 - 1) * adjustment
        );
    }
} 