package com.agritech.autonomousfarming.strategy;

import com.agritech.autonomousfarming.model.Position;
import java.util.List;

/**
 * 路径规划策略接口
 * 实现策略模式
 */
public interface PathPlanningStrategy {
    /**
     * 计算从起点到终点的路径
     * @param start 起点
     * @param end 终点
     * @return 路径点列表
     */
    List<Position> calculatePath(Position start, Position end);
    
    /**
     * 计算避障路径
     * @param currentPosition 当前位置
     * @param obstaclePosition 障碍物位置
     * @param targetPosition 目标位置
     * @return 避障路径点列表
     */
    List<Position> calculateAvoidancePath(Position currentPosition, Position obstaclePosition, Position targetPosition);
} 