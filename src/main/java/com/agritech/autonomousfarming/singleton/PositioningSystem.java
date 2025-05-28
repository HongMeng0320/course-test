package com.agritech.autonomousfarming.singleton;

import com.agritech.autonomousfarming.model.Position;
import java.util.HashMap;
import java.util.Map;

/**
 * 高精度定位系统类
 * 实现单例模式
 */
public class PositioningSystem {
    // 单例实例
    private static volatile PositioningSystem instance;
    
    // 存储各农机设备的位置信息
    private Map<String, Position> machinePositions;
    
    // 系统状态
    private boolean initialized;
    private double accuracy; // 精度，单位：厘米
    
    /**
     * 私有构造方法，防止外部实例化
     */
    private PositioningSystem() {
        this.machinePositions = new HashMap<>();
        this.initialized = false;
        this.accuracy = 0.0;
    }
    
    /**
     * 获取单例实例
     * @return 定位系统实例
     */
    public static PositioningSystem getInstance() {
        if (instance == null) {
            synchronized (PositioningSystem.class) {
                if (instance == null) {
                    instance = new PositioningSystem();
                }
            }
        }
        return instance;
    }
    
    /**
     * 初始化定位系统
     * @param accuracy 系统精度（厘米）
     */
    public void initialize(double accuracy) {
        if (!initialized) {
            System.out.println("[定位系统] 初始化高精度定位系统，精度: " + accuracy + "厘米");
            this.accuracy = accuracy;
            this.initialized = true;
        } else {
            System.out.println("[定位系统] 定位系统已经初始化");
        }
    }
    
    /**
     * 更新农机位置
     * @param machineId 农机ID
     * @param position 位置
     */
    public void updateMachinePosition(String machineId, Position position) {
        checkInitialized();
        machinePositions.put(machineId, position);
        System.out.println("[定位系统] 更新农机 " + machineId + " 位置: " + position);
    }
    
    /**
     * 获取农机位置
     * @param machineId 农机ID
     * @return 位置
     */
    public Position getMachinePosition(String machineId) {
        checkInitialized();
        Position position = machinePositions.get(machineId);
        if (position != null) {
            System.out.println("[定位系统] 获取农机 " + machineId + " 位置: " + position);
        } else {
            System.out.println("[定位系统] 农机 " + machineId + " 位置未记录");
        }
        return position;
    }
    
    /**
     * 计算两个农机之间的距离
     * @param machineId1 农机1 ID
     * @param machineId2 农机2 ID
     * @return 距离
     */
    public double calculateDistance(String machineId1, String machineId2) {
        checkInitialized();
        Position pos1 = machinePositions.get(machineId1);
        Position pos2 = machinePositions.get(machineId2);
        
        if (pos1 == null || pos2 == null) {
            System.out.println("[定位系统] 无法计算距离，农机位置未记录");
            return -1;
        }
        
        double distance = pos1.distanceTo(pos2);
        System.out.println("[定位系统] 农机 " + machineId1 + " 和 " + machineId2 + " 之间的距离: " + distance);
        return distance;
    }
    
    /**
     * 获取系统精度
     * @return 精度（厘米）
     */
    public double getAccuracy() {
        return accuracy;
    }
    
    /**
     * 检查系统是否已初始化
     */
    private void checkInitialized() {
        if (!initialized) {
            throw new IllegalStateException("定位系统尚未初始化");
        }
    }
} 