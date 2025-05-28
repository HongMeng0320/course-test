package com.agritech.autonomousfarming.model;

import com.agritech.autonomousfarming.observer.EnvironmentUpdate;
import com.agritech.autonomousfarming.strategy.EfficientPathPlanningStrategy;

/**
 * 自动收割机类
 */
public class Harvester extends AbstractAgriculturalMachine {
    private double harvestCapacity;
    private double currentLoad;
    private double cuttingHeight;
    
    public Harvester(String id, String name) {
        super(id, name, MachineType.HARVESTER);
        this.harvestCapacity = 10000.0; // 默认收割容量10吨
        this.currentLoad = 0.0;
        this.cuttingHeight = 10.0; // 默认切割高度10厘米
        this.pathPlanningStrategy = new EfficientPathPlanningStrategy();
    }
    
    public void adjustCuttingHeight(double height) {
        this.cuttingHeight = height;
        System.out.println("[" + getName() + "] 已调整切割高度: " + height + "厘米");
    }
    
    public void unloadHarvest() {
        System.out.println("[" + getName() + "] 卸载收获物: " + currentLoad + "公斤");
        this.currentLoad = 0.0;
    }
    
    public void harvest(double amount) {
        if (currentLoad + amount <= harvestCapacity) {
            currentLoad += amount;
            System.out.println("[" + getName() + "] 收割: " + amount + "公斤, 当前负载: " + currentLoad + "公斤");
        } else {
            System.out.println("[" + getName() + "] 收割仓已满, 需要卸载");
            unloadHarvest();
            currentLoad = amount;
            System.out.println("[" + getName() + "] 继续收割: " + amount + "公斤, 当前负载: " + currentLoad + "公斤");
        }
    }
    
    @Override
    protected void adjustOperationBasedOnEnvironment(EnvironmentUpdate update) {
        switch(update.getType()) {
            case CROP_MATURITY:
                handleCropMaturityUpdate(update);
                break;
            case WEATHER:
                handleWeatherUpdate(update);
                break;
            case OBSTACLE:
                handleObstacleUpdate(update);
                break;
            default:
                System.out.println("[" + getName() + "] 收到未处理的环境更新类型: " + update.getType());
        }
    }
    
    private void handleCropMaturityUpdate(EnvironmentUpdate update) {
        int maturity = Integer.parseInt(update.getValue());
        if (maturity < 80) {
            System.out.println("[" + getName() + "] 检测到作物成熟度不足 (" + maturity + "%), 暂停收割");
            setStatus(MachineStatus.IDLE);
        } else if (maturity > 95) {
            System.out.println("[" + getName() + "] 检测到作物过熟 (" + maturity + "%), 加速收割");
            setStatus(MachineStatus.WORKING);
        }
    }
    
    private void handleWeatherUpdate(EnvironmentUpdate update) {
        if (update.getValue().contains("雨")) {
            System.out.println("[" + getName() + "] 检测到雨天天气, 调整收割速度");
        } else if (update.getValue().contains("大风")) {
            System.out.println("[" + getName() + "] 检测到大风天气, 暂停收割作业");
            setStatus(MachineStatus.IDLE);
        }
    }
    
    private void handleObstacleUpdate(EnvironmentUpdate update) {
        System.out.println("[" + getName() + "] 检测到障碍物: " + update.getValue() + ", 执行避障操作");
        Position currentPos = getPosition();
        moveTo(new Position(currentPos.getX() - 3, currentPos.getY() + 4));
    }
} 