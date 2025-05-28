package com.agritech.autonomousfarming.chainofresponsibility;

import com.agritech.autonomousfarming.model.AgriculturalMachine;
import com.agritech.autonomousfarming.model.Position;

/**
 * 安全处理链构建类
 * 用于构建和管理责任链
 */
public class SafetyHandlerChain {
    private SafetyHandler firstHandler;
    private SafetyHandler lastHandler;
    
    public SafetyHandlerChain() {
        this.firstHandler = null;
        this.lastHandler = null;
    }
    
    /**
     * 添加处理器到责任链
     * @param handler 处理器
     * @return 当前安全处理链对象
     */
    public SafetyHandlerChain addHandler(SafetyHandler handler) {
        if (firstHandler == null) {
            firstHandler = handler;
            lastHandler = handler;
        } else {
            lastHandler.setNext(handler);
            lastHandler = handler;
        }
        return this;
    }
    
    /**
     * 处理安全事件
     * @param event 安全事件
     * @return 是否处理成功
     */
    public boolean handleEvent(SafetyEvent event) {
        if (firstHandler == null) {
            System.out.println("[安全处理链] 警告: 没有配置任何处理器");
            return false;
        }
        
        System.out.println("[安全处理链] 处理安全事件: " + event.getType() + " - " + event.getDescription());
        return firstHandler.handle(event);
    }
    
    /**
     * 为农机创建标准安全处理链
     * @param machine 农机设备
     * @param fieldTopLeft 农田左上角坐标
     * @param fieldBottomRight 农田右下角坐标
     * @return 安全处理链
     */
    public static SafetyHandlerChain createStandardChain(
            AgriculturalMachine machine, 
            Position fieldTopLeft, 
            Position fieldBottomRight) {
        
        SafetyHandlerChain chain = new SafetyHandlerChain();
        
        // 添加各种处理器，按优先级排序
        chain.addHandler(new ObstacleHandler(machine))
             .addHandler(new EquipmentFailureHandler(machine))
             .addHandler(new BoundaryViolationHandler(machine, fieldTopLeft, fieldBottomRight))
             .addHandler(new WeatherAlertHandler(machine));
        
        System.out.println("[安全处理链] 已创建标准安全处理链");
        return chain;
    }
} 