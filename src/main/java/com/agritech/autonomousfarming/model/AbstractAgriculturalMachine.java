package com.agritech.autonomousfarming.model;

import com.agritech.autonomousfarming.observer.EnvironmentObserver;
import com.agritech.autonomousfarming.observer.EnvironmentUpdate;
import com.agritech.autonomousfarming.state.MachineState;
import com.agritech.autonomousfarming.state.IdleState;
import com.agritech.autonomousfarming.strategy.PathPlanningStrategy;
import lombok.Data;

/**
 * 农机设备抽象基类
 * 实现农机设备的通用功能
 */
@Data
public abstract class AbstractAgriculturalMachine implements AgriculturalMachine, EnvironmentObserver {
    protected String id;
    protected String name;
    protected MachineType type;
    protected Position position;
    protected MachineStatus status;
    protected double fuelLevel;
    protected MachineState state;
    protected PathPlanningStrategy pathPlanningStrategy;
    
    public AbstractAgriculturalMachine(String id, String name, MachineType type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.position = new Position(0, 0);
        this.status = MachineStatus.IDLE;
        this.fuelLevel = 100.0;
        this.state = new IdleState(this);
    }
    
    @Override
    public void handleEnvironmentUpdate(EnvironmentUpdate update) {
        System.out.println("[" + name + "] 收到环境更新: " + update.getType() + " - " + update.getValue());
        // 根据环境更新调整作业策略
        adjustOperationBasedOnEnvironment(update);
    }
    
    @Override
    public void moveTo(Position position) {
        System.out.println("[" + name + "] 正在移动到位置: " + position);
        this.position = position;
        this.fuelLevel -= 5.0; // 模拟油耗
        if (this.fuelLevel < 0) {
            this.fuelLevel = 0;
        }
    }
    
    @Override
    public void performTask(Task task) {
        System.out.println("[" + name + "] 正在执行任务: " + task.getName());
        state.handleTask(task);
    }
    
    @Override
    public void printStatus() {
        System.out.println("==== " + name + " (" + type + ") 状态 ====");
        System.out.println("ID: " + id);
        System.out.println("位置: " + position);
        System.out.println("状态: " + status);
        System.out.println("油量: " + fuelLevel + "%");
        System.out.println("当前状态: " + state.getClass().getSimpleName());
        System.out.println("===========================");
    }
    
    /**
     * 设置路径规划策略
     */
    public void setPathPlanningStrategy(PathPlanningStrategy pathPlanningStrategy) {
        this.pathPlanningStrategy = pathPlanningStrategy;
        System.out.println("[" + name + "] 更新路径规划策略: " + pathPlanningStrategy.getClass().getSimpleName());
    }
    
    /**
     * 更改状态
     */
    public void changeState(MachineState state) {
        this.state = state;
        System.out.println("[" + name + "] 状态变更为: " + state.getClass().getSimpleName());
    }
    
    /**
     * 根据环境更新调整作业策略
     * 由具体子类实现
     */
    protected abstract void adjustOperationBasedOnEnvironment(EnvironmentUpdate update);
} 