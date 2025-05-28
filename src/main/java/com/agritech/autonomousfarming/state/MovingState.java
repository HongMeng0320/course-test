package com.agritech.autonomousfarming.state;

import com.agritech.autonomousfarming.model.AbstractAgriculturalMachine;
import com.agritech.autonomousfarming.model.MachineStatus;
import com.agritech.autonomousfarming.model.Task;

/**
 * 移动状态类
 * 实现状态模式
 */
public class MovingState implements MachineState {
    private AbstractAgriculturalMachine machine;
    
    public MovingState(AbstractAgriculturalMachine machine) {
        this.machine = machine;
        this.machine.setStatus(MachineStatus.MOVING);
    }
    
    @Override
    public void handleTask(Task task) {
        System.out.println("[" + machine.getName() + "] 移动状态接收到任务: " + task.getName() + ", 将在到达目的地后执行");
    }
    
    @Override
    public void handleMove() {
        System.out.println("[" + machine.getName() + "] 移动状态已经在移动中");
    }
    
    @Override
    public void handleStop() {
        System.out.println("[" + machine.getName() + "] 移动状态停止移动");
        machine.setStatus(MachineStatus.IDLE);
        machine.changeState(new IdleState(machine));
    }
    
    @Override
    public void handleError(String errorMessage) {
        System.out.println("[" + machine.getName() + "] 移动状态发生故障: " + errorMessage);
        machine.setStatus(MachineStatus.ERROR);
        machine.changeState(new ErrorState(machine, errorMessage));
    }
} 