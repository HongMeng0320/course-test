package com.agritech.autonomousfarming.chainofresponsibility;

import com.agritech.autonomousfarming.model.AgriculturalMachine;
import com.agritech.autonomousfarming.model.MachineStatus;

/**
 * 设备故障处理器类
 * 责任链模式中的具体处理器
 */
public class EquipmentFailureHandler extends AbstractSafetyHandler {
    private AgriculturalMachine machine;
    
    public EquipmentFailureHandler(AgriculturalMachine machine) {
        this.machine = machine;
    }
    
    @Override
    protected boolean canHandle(SafetyEvent event) {
        return event.getType() == SafetyEvent.EventType.EQUIPMENT_FAILURE;
    }
    
    @Override
    protected boolean doHandle(SafetyEvent event) {
        System.out.println("[设备故障处理器] 处理设备故障事件: " + event.getDescription());
        
        // 根据故障严重程度采取不同措施
        if (event.getSeverity() >= 4) {
            // 严重故障，紧急停止并请求维护
            System.out.println("[设备故障处理器] 检测到严重故障，执行紧急停止并请求维护");
            machine.setStatus(MachineStatus.ERROR);
            requestMaintenance(event);
            return true;
        } else if (event.getSeverity() >= 2) {
            // 一般故障，尝试自修复
            System.out.println("[设备故障处理器] 检测到一般故障，尝试自修复");
            boolean repaired = attemptSelfRepair(event);
            if (repaired) {
                System.out.println("[设备故障处理器] 自修复成功");
                return true;
            } else {
                System.out.println("[设备故障处理器] 自修复失败，请求维护");
                machine.setStatus(MachineStatus.MAINTENANCE);
                requestMaintenance(event);
                return true;
            }
        } else {
            // 轻微故障，记录并继续
            System.out.println("[设备故障处理器] 检测到轻微故障，记录并继续运行");
            return true;
        }
    }
    
    private void requestMaintenance(SafetyEvent event) {
        System.out.println("[设备故障处理器] 发送维护请求: " + event.getDescription() + 
                          ", 位置: " + event.getPosition());
    }
    
    private boolean attemptSelfRepair(SafetyEvent event) {
        System.out.println("[设备故障处理器] 尝试自修复: " + event.getDescription());
        // 模拟自修复成功率
        return Math.random() > 0.3; // 70%的概率修复成功
    }
} 