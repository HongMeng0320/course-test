package com.agritech.autonomousfarming.command;

import com.agritech.autonomousfarming.model.AgriculturalMachine;
import com.agritech.autonomousfarming.model.MachineStatus;
import com.agritech.autonomousfarming.model.Task;

/**
 * 执行任务命令类
 * 实现命令模式
 */
public class PerformTaskCommand implements MachineCommand {
    private AgriculturalMachine machine;
    private Task task;
    private MachineStatus previousStatus;
    
    public PerformTaskCommand(AgriculturalMachine machine, Task task) {
        this.machine = machine;
        this.task = task;
    }
    
    @Override
    public void execute() {
        // 保存当前状态，用于撤销
        this.previousStatus = machine.getStatus();
        
        // 执行任务
        System.out.println("[任务命令] 执行: " + machine.getName() + " 执行任务 " + task.getName());
        machine.performTask(task);
    }
    
    @Override
    public void undo() {
        if (previousStatus != null) {
            System.out.println("[任务命令] 撤销: " + machine.getName() + " 恢复到状态 " + previousStatus);
            machine.setStatus(previousStatus);
        }
    }
    
    @Override
    public String getDescription() {
        return machine.getName() + " 执行任务 " + task.getName();
    }
} 