package com.agritech.autonomousfarming.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 位置坐标类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    private double x;
    private double y;
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    /**
     * 计算与另一个位置的距离
     */
    public double distanceTo(Position other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
} 