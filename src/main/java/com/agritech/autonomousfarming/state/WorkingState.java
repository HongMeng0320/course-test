package com.agritech.autonomousfarming.state;

import com.agritech.autonomousfarming.model.AbstractAgriculturalMachine;
import com.agritech.autonomousfarming.model.MachineStatus;
import com.agritech.autonomousfarming.model.Task;

/**
 * 工作状态类
 * 实现状态模式
 */
public class WorkingState implements MachineState {
    private AbstractAgriculturalMachine machine;
    
    public WorkingState(AbstractAgriculturalMachine machine) {
        this.machine = machine;
        this.machine.setStatus(MachineStatus.WORKING);
    }
    
    @Override
    public void handleTask(Task task) {
        System.out.println("[" + machine.getName() + "] 工作状态接收到新任务: " + task.getName() + ", 但当前正在执行其他任务");
    }
    
    @Override
    public void handleMove() {
        System.out.println("[" + machine.getName() + "] 工作状态暂停工作，开始移动");
        machine.setStatus(MachineStatus.MOVING);
        machine.changeState(new MovingState(machine));
    }
    
    @Override
    public void handleStop() {
        System.out.println("[" + machine.getName() + "] 工作状态停止工作");
        machine.setStatus(MachineStatus.IDLE);
        machine.changeState(new IdleState(machine));
    }
    
    @Override
    public void handleError(String errorMessage) {
        System.out.println("[" + machine.getName() + "] 工作状态发生故障: " + errorMessage);
        machine.setStatus(MachineStatus.ERROR);
        machine.changeState(new ErrorState(machine, errorMessage));
    }
} 