package com.agritech.autonomousfarming.chainofresponsibility;

import com.agritech.autonomousfarming.model.AgriculturalMachine;
import com.agritech.autonomousfarming.model.MachineStatus;
import com.agritech.autonomousfarming.model.Position;

/**
 * 边界违规处理器类
 * 责任链模式中的具体处理器
 */
public class BoundaryViolationHandler extends AbstractSafetyHandler {
    private AgriculturalMachine machine;
    private Position fieldTopLeft;
    private Position fieldBottomRight;
    
    public BoundaryViolationHandler(AgriculturalMachine machine, Position fieldTopLeft, Position fieldBottomRight) {
        this.machine = machine;
        this.fieldTopLeft = fieldTopLeft;
        this.fieldBottomRight = fieldBottomRight;
    }
    
    @Override
    protected boolean canHandle(SafetyEvent event) {
        return event.getType() == SafetyEvent.EventType.BOUNDARY_VIOLATION;
    }
    
    @Override
    protected boolean doHandle(SafetyEvent event) {
        System.out.println("[边界违规处理器] 处理边界违规事件: " + event.getDescription());
        
        Position currentPosition = event.getPosition();
        
        // 检查是否超出边界
        if (isOutOfBounds(currentPosition)) {
            System.out.println("[边界违规处理器] 检测到农机超出工作区域边界");
            
            // 计算安全位置
            Position safePosition = calculateSafePosition(currentPosition);
            
            // 移动到安全位置
            System.out.println("[边界违规处理器] 将农机移回安全位置: " + safePosition);
            machine.setStatus(MachineStatus.MOVING);
            machine.moveTo(safePosition);
            
            return true;
        }
        
        return false;
    }
    
    private boolean isOutOfBounds(Position position) {
        return position.getX() < fieldTopLeft.getX() ||
               position.getY() < fieldTopLeft.getY() ||
               position.getX() > fieldBottomRight.getX() ||
               position.getY() > fieldBottomRight.getY();
    }
    
    private Position calculateSafePosition(Position currentPosition) {
        double safeX = Math.max(fieldTopLeft.getX(), Math.min(fieldBottomRight.getX(), currentPosition.getX()));
        double safeY = Math.max(fieldTopLeft.getY(), Math.min(fieldBottomRight.getY(), currentPosition.getY()));
        
        // 向内部移动一点距离，避免边界
        if (safeX == fieldTopLeft.getX()) safeX += 5;
        if (safeX == fieldBottomRight.getX()) safeX -= 5;
        if (safeY == fieldTopLeft.getY()) safeY += 5;
        if (safeY == fieldBottomRight.getY()) safeY -= 5;
        
        return new Position(safeX, safeY);
    }
} 