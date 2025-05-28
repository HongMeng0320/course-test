package com.agritech.autonomousfarming.command;

/**
 * 农机命令接口
 * 实现命令模式
 */
public interface MachineCommand {
    /**
     * 执行命令
     */
    void execute();
    
    /**
     * 撤销命令
     */
    void undo();
    
    /**
     * 获取命令描述
     * @return 命令描述
     */
    String getDescription();
} 