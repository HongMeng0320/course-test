package com.agritech.autonomousfarming.command;

import com.agritech.autonomousfarming.model.AgriculturalMachine;
import com.agritech.autonomousfarming.model.MachineStatus;

/**
 * 停止命令类
 * 实现命令模式
 */
public class StopCommand implements MachineCommand {
    private AgriculturalMachine machine;
    private MachineStatus previousStatus;
    
    public StopCommand(AgriculturalMachine machine) {
        this.machine = machine;
    }
    
    @Override
    public void execute() {
        // 保存当前状态，用于撤销
        this.previousStatus = machine.getStatus();
        
        // 执行停止
        System.out.println("[停止命令] 执行: " + machine.getName() + " 停止作业");
        machine.setStatus(MachineStatus.IDLE);
    }
    
    @Override
    public void undo() {
        if (previousStatus != null) {
            System.out.println("[停止命令] 撤销: " + machine.getName() + " 恢复到状态 " + previousStatus);
            machine.setStatus(previousStatus);
        }
    }
    
    @Override
    public String getDescription() {
        return "停止 " + machine.getName() + " 的作业";
    }
} 