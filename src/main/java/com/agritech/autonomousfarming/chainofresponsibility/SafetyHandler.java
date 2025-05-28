package com.agritech.autonomousfarming.chainofresponsibility;

/**
 * 安全处理器接口
 * 实现责任链模式
 */
public interface SafetyHandler {
    /**
     * 设置下一个处理器
     * @param nextHandler 下一个处理器
     * @return 下一个处理器
     */
    SafetyHandler setNext(SafetyHandler nextHandler);
    
    /**
     * 处理安全事件
     * @param event 安全事件
     * @return 是否处理成功
     */
    boolean handle(SafetyEvent event);
} 