package com.agritech.autonomousfarming.command;

import com.agritech.autonomousfarming.model.AgriculturalMachine;
import com.agritech.autonomousfarming.model.Position;

/**
 * 移动命令类
 * 实现命令模式
 */
public class MoveCommand implements MachineCommand {
    private AgriculturalMachine machine;
    private Position targetPosition;
    private Position previousPosition;
    
    public MoveCommand(AgriculturalMachine machine, Position targetPosition) {
        this.machine = machine;
        this.targetPosition = targetPosition;
    }
    
    @Override
    public void execute() {
        // 保存当前位置，用于撤销
        this.previousPosition = machine.getPosition();
        
        // 执行移动
        System.out.println("[移动命令] 执行: " + machine.getName() + " 移动到 " + targetPosition);
        machine.moveTo(targetPosition);
    }
    
    @Override
    public void undo() {
        if (previousPosition != null) {
            System.out.println("[移动命令] 撤销: " + machine.getName() + " 返回到 " + previousPosition);
            machine.moveTo(previousPosition);
        }
    }
    
    @Override
    public String getDescription() {
        return "移动 " + machine.getName() + " 到位置 " + targetPosition;
    }
} 