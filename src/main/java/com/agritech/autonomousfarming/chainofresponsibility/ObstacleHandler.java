package com.agritech.autonomousfarming.chainofresponsibility;

import com.agritech.autonomousfarming.model.AgriculturalMachine;

/**
 * 障碍物处理器类
 * 责任链模式中的具体处理器
 */
public class ObstacleHandler extends AbstractSafetyHandler {
    private AgriculturalMachine machine;
    
    public ObstacleHandler(AgriculturalMachine machine) {
        this.machine = machine;
    }
    
    @Override
    protected boolean canHandle(SafetyEvent event) {
        return event.getType() == SafetyEvent.EventType.OBSTACLE;
    }
    
    @Override
    protected boolean doHandle(SafetyEvent event) {
        System.out.println("[障碍物处理器] 处理障碍物事件: " + event.getDescription());
        
        // 根据障碍物严重程度采取不同措施
        if (event.getSeverity() >= 4) {
            // 严重障碍物，紧急停止
            System.out.println("[障碍物处理器] 检测到严重障碍物，执行紧急停止");
            machine.setStatus(com.agritech.autonomousfarming.model.MachineStatus.IDLE);
            return true;
        } else if (event.getSeverity() >= 2) {
            // 一般障碍物，绕行
            System.out.println("[障碍物处理器] 检测到障碍物，计算绕行路径");
            // 模拟绕行
            machine.moveTo(new com.agritech.autonomousfarming.model.Position(
                event.getPosition().getX() + 5,
                event.getPosition().getY() + 5
            ));
            return true;
        } else {
            // 轻微障碍物，减速通过
            System.out.println("[障碍物处理器] 检测到轻微障碍物，减速通过");
            return true;
        }
    }
} 