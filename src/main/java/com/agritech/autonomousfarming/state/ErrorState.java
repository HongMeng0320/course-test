package com.agritech.autonomousfarming.state;

import com.agritech.autonomousfarming.model.AbstractAgriculturalMachine;
import com.agritech.autonomousfarming.model.MachineStatus;
import com.agritech.autonomousfarming.model.Task;

/**
 * 错误状态类
 * 实现状态模式
 */
public class ErrorState implements MachineState {
    private AbstractAgriculturalMachine machine;
    private String errorMessage;
    
    public ErrorState(AbstractAgriculturalMachine machine, String errorMessage) {
        this.machine = machine;
        this.errorMessage = errorMessage;
        this.machine.setStatus(MachineStatus.ERROR);
        System.out.println("[" + machine.getName() + "] 进入错误状态: " + errorMessage);
    }
    
    @Override
    public void handleTask(Task task) {
        System.out.println("[" + machine.getName() + "] 错误状态无法执行任务: " + task.getName() + ", 需要先修复故障");
    }
    
    @Override
    public void handleMove() {
        System.out.println("[" + machine.getName() + "] 错误状态无法移动, 需要先修复故障");
    }
    
    @Override
    public void handleStop() {
        System.out.println("[" + machine.getName() + "] 错误状态已停止");
    }
    
    @Override
    public void handleError(String newErrorMessage) {
        System.out.println("[" + machine.getName() + "] 错误状态收到新故障: " + newErrorMessage);
        this.errorMessage = newErrorMessage;
    }
    
    /**
     * 修复故障
     */
    public void repair() {
        System.out.println("[" + machine.getName() + "] 故障已修复: " + errorMessage);
        machine.setStatus(MachineStatus.IDLE);
        machine.changeState(new IdleState(machine));
    }
} 