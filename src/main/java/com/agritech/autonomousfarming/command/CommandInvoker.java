package com.agritech.autonomousfarming.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 命令调用者类
 * 实现命令模式中的调用者角色
 */
public class CommandInvoker {
    private List<MachineCommand> commandHistory;
    private Stack<MachineCommand> undoStack;
    
    public CommandInvoker() {
        this.commandHistory = new ArrayList<>();
        this.undoStack = new Stack<>();
    }
    
    /**
     * 执行命令
     * @param command 要执行的命令
     */
    public void executeCommand(MachineCommand command) {
        System.out.println("[命令调用者] 执行命令: " + command.getDescription());
        command.execute();
        commandHistory.add(command);
        undoStack.push(command);
    }
    
    /**
     * 撤销上一个命令
     */
    public void undoLastCommand() {
        if (!undoStack.isEmpty()) {
            MachineCommand command = undoStack.pop();
            System.out.println("[命令调用者] 撤销命令: " + command.getDescription());
            command.undo();
        } else {
            System.out.println("[命令调用者] 没有可撤销的命令");
        }
    }
    
    /**
     * 获取命令历史
     * @return 命令历史列表
     */
    public List<MachineCommand> getCommandHistory() {
        return new ArrayList<>(commandHistory);
    }
    
    /**
     * 打印命令历史
     */
    public void printCommandHistory() {
        System.out.println("==== 命令历史记录 ====");
        if (commandHistory.isEmpty()) {
            System.out.println("暂无命令记录");
        } else {
            for (int i = 0; i < commandHistory.size(); i++) {
                System.out.println((i + 1) + ". " + commandHistory.get(i).getDescription());
            }
        }
        System.out.println("====================");
    }
} 