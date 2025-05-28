package com.agritech.autonomousfarming.chainofresponsibility;

/**
 * 抽象安全处理器类
 * 实现责任链模式的基础功能
 */
public abstract class AbstractSafetyHandler implements SafetyHandler {
    private SafetyHandler nextHandler;
    
    @Override
    public SafetyHandler setNext(SafetyHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }
    
    @Override
    public boolean handle(SafetyEvent event) {
        // 如果当前处理器能处理，则处理
        if (canHandle(event)) {
            return doHandle(event);
        }
        
        // 如果有下一个处理器，则传递给下一个处理器
        if (nextHandler != null) {
            return nextHandler.handle(event);
        }
        
        // 没有处理器能处理该事件
        System.out.println("[安全处理] 警告: 没有处理器能处理事件 " + event.getType() + ": " + event.getDescription());
        return false;
    }
    
    /**
     * 检查当前处理器是否能处理该事件
     * @param event 安全事件
     * @return 是否能处理
     */
    protected abstract boolean canHandle(SafetyEvent event);
    
    /**
     * 处理事件的具体实现
     * @param event 安全事件
     * @return 是否处理成功
     */
    protected abstract boolean doHandle(SafetyEvent event);
} 