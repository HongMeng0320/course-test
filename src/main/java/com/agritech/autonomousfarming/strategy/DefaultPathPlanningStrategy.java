package com.agritech.autonomousfarming.strategy;

import com.agritech.autonomousfarming.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认路径规划策略
 * 实现策略模式
 */
public class DefaultPathPlanningStrategy implements PathPlanningStrategy {
    
    @Override
    public List<Position> calculatePath(Position start, Position end) {
        System.out.println("[默认路径规划] 计算从 " + start + " 到 " + end + " 的路径");
        
        List<Position> path = new ArrayList<>();
        path.add(start);
        
        // 简单的直线路径规划
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        int steps = 5; // 路径点数量
        
        for (int i = 1; i < steps; i++) {
            double ratio = (double) i / steps;
            path.add(new Position(
                start.getX() + dx * ratio,
                start.getY() + dy * ratio
            ));
        }
        
        path.add(end);
        return path;
    }
    
    @Override
    public List<Position> calculateAvoidancePath(Position currentPosition, Position obstaclePosition, Position targetPosition) {
        System.out.println("[默认避障策略] 计算避障路径");
        
        List<Position> path = new ArrayList<>();
        path.add(currentPosition);
        
        // 简单的避障策略：绕过障碍物
        double avoidanceDistance = 5.0;
        
        // 计算当前位置到障碍物的向量
        double dx = obstaclePosition.getX() - currentPosition.getX();
        double dy = obstaclePosition.getY() - currentPosition.getY();
        
        // 计算垂直于该向量的方向（顺时针旋转90度）
        double perpX = -dy;
        double perpY = dx;
        
        // 归一化
        double length = Math.sqrt(perpX * perpX + perpY * perpY);
        perpX = perpX / length * avoidanceDistance;
        perpY = perpY / length * avoidanceDistance;
        
        // 添加避障点
        Position avoidancePoint = new Position(
            obstaclePosition.getX() + perpX,
            obstaclePosition.getY() + perpY
        );
        path.add(avoidancePoint);
        
        // 添加目标点
        path.add(targetPosition);
        
        return path;
    }
} 