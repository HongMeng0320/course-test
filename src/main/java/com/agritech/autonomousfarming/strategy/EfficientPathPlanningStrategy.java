package com.agritech.autonomousfarming.strategy;

import com.agritech.autonomousfarming.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * 高效路径规划策略
 * 实现策略模式
 */
public class EfficientPathPlanningStrategy implements PathPlanningStrategy {
    
    @Override
    public List<Position> calculatePath(Position start, Position end) {
        System.out.println("[高效路径规划] 计算从 " + start + " 到 " + end + " 的路径");
        
        List<Position> path = new ArrayList<>();
        path.add(start);
        
        // 高效路径规划，尽量减少路径点，优化直线路径
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        
        // 只添加少量的关键点
        if (Math.abs(dx) > 10 || Math.abs(dy) > 10) {
            // 如果距离较远，添加一个中间点
            path.add(new Position(
                start.getX() + dx * 0.5,
                start.getY() + dy * 0.5
            ));
        }
        
        path.add(end);
        return path;
    }
    
    @Override
    public List<Position> calculateAvoidancePath(Position currentPosition, Position obstaclePosition, Position targetPosition) {
        System.out.println("[高效避障策略] 计算避障路径");
        
        List<Position> path = new ArrayList<>();
        path.add(currentPosition);
        
        // 高效的避障策略：计算最少的点，快速绕过障碍物
        double avoidanceDistance = 7.0; // 更大的避障距离，确保安全
        
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
        
        // 只添加一个避障点，直接绕过障碍物
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