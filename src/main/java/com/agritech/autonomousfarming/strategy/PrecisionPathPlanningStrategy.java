package com.agritech.autonomousfarming.strategy;

import com.agritech.autonomousfarming.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * 精确路径规划策略
 * 实现策略模式
 */
public class PrecisionPathPlanningStrategy implements PathPlanningStrategy {
    
    @Override
    public List<Position> calculatePath(Position start, Position end) {
        System.out.println("[精确路径规划] 计算从 " + start + " 到 " + end + " 的路径");
        
        List<Position> path = new ArrayList<>();
        path.add(start);
        
        // 精确路径规划，增加更多路径点
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        int steps = 10; // 更多的路径点
        
        for (int i = 1; i < steps; i++) {
            double ratio = (double) i / steps;
            // 添加微小的随机偏移，模拟精确规划中的优化
            double offsetX = Math.sin(ratio * Math.PI) * 0.5;
            double offsetY = Math.cos(ratio * Math.PI) * 0.5;
            
            path.add(new Position(
                start.getX() + dx * ratio + offsetX,
                start.getY() + dy * ratio + offsetY
            ));
        }
        
        path.add(end);
        return path;
    }
    
    @Override
    public List<Position> calculateAvoidancePath(Position currentPosition, Position obstaclePosition, Position targetPosition) {
        System.out.println("[精确避障策略] 计算避障路径");
        
        List<Position> path = new ArrayList<>();
        path.add(currentPosition);
        
        // 精确的避障策略：计算多个点形成平滑曲线
        double avoidanceDistance = 3.0;
        
        // 计算当前位置到障碍物的向量
        double dx = obstaclePosition.getX() - currentPosition.getX();
        double dy = obstaclePosition.getY() - currentPosition.getY();
        
        // 计算垂直于该向量的方向
        double perpX = -dy;
        double perpY = dx;
        
        // 归一化
        double length = Math.sqrt(perpX * perpX + perpY * perpY);
        perpX = perpX / length * avoidanceDistance;
        perpY = perpY / length * avoidanceDistance;
        
        // 添加多个避障点形成平滑曲线
        int avoidancePoints = 5;
        for (int i = 0; i < avoidancePoints; i++) {
            double ratio = (double) (i + 1) / (avoidancePoints + 1);
            double factor = Math.sin(ratio * Math.PI);
            
            Position avoidancePoint = new Position(
                obstaclePosition.getX() + perpX * factor,
                obstaclePosition.getY() + perpY * factor
            );
            path.add(avoidancePoint);
        }
        
        // 添加目标点
        path.add(targetPosition);
        
        return path;
    }
} 