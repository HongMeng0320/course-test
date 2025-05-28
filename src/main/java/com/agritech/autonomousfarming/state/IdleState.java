package com.agritech.autonomousfarming.state;

import com.agritech.autonomousfarming.model.AbstractAgriculturalMachine;
import com.agritech.autonomousfarming.model.MachineStatus;
import com.agritech.autonomousfarming.model.Task;

/**
 * 空闲状态类
 * 实现状态模式
 */
public class IdleState implements MachineState {
    private AbstractAgriculturalMachine machine;
    
    public IdleState(AbstractAgriculturalMachine machine) {
        this.machine = machine;
        this.machine.setStatus(MachineStatus.IDLE);
    }
    
    @Override
    public void handleTask(Task task) {
        System.out.println("[" + machine.getName() + "] 空闲状态接收到任务: " + task.getName());
        machine.setStatus(MachineStatus.WORKING);
        machine.changeState(new WorkingState(machine));
    }
    
    @Override
    public void handleMove() {
        System.out.println("[" + machine.getName() + "] 空闲状态开始移动");
        machine.setStatus(MachineStatus.MOVING);
        machine.changeState(new MovingState(machine));
    }
    
    @Override
    public void handleStop() {
        System.out.println("[" + machine.getName() + "] 空闲状态已经停止");
    }
    
    @Override
    public void handleError(String errorMessage) {
        System.out.println("[" + machine.getName() + "] 空闲状态发生故障: " + errorMessage);
        machine.setStatus(MachineStatus.ERROR);
        machine.changeState(new ErrorState(machine, errorMessage));
    }
} 