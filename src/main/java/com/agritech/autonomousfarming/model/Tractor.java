package com.agritech.autonomousfarming.model;

import com.agritech.autonomousfarming.observer.EnvironmentUpdate;
import com.agritech.autonomousfarming.strategy.DefaultPathPlanningStrategy;

/**
 * 无人驾驶拖拉机类
 */
public class Tractor extends AbstractAgriculturalMachine {
    private double loadCapacity;
    private boolean plowAttached;
    
    public Tractor(String id, String name) {
        super(id, name, MachineType.TRACTOR);
        this.loadCapacity = 5000.0; // 默认载重5吨
        this.plowAttached = false;
        this.pathPlanningStrategy = new DefaultPathPlanningStrategy();
    }
    
    public void attachPlow() {
        this.plowAttached = true;
        System.out.println("[" + getName() + "] 已安装犁具");
    }
    
    public void detachPlow() {
        this.plowAttached = false;
        System.out.println("[" + getName() + "] 已卸载犁具");
    }
    
    @Override
    protected void adjustOperationBasedOnEnvironment(EnvironmentUpdate update) {
        switch(update.getType()) {
            case SOIL_MOISTURE:
                handleSoilMoistureUpdate(update);
                break;
            case OBSTACLE:
                handleObstacleUpdate(update);
                break;
            case WEATHER:
                handleWeatherUpdate(update);
                break;
            default:
                System.out.println("[" + getName() + "] 收到未处理的环境更新类型: " + update.getType());
        }
    }
    
    private void handleSoilMoistureUpdate(EnvironmentUpdate update) {
        double moisture = Double.parseDouble(update.getValue());
        if (moisture > 80) {
            System.out.println("[" + getName() + "] 检测到土壤湿度过高 (" + moisture + "%), 调整路径避开该区域");
            // 模拟路径调整
            Position currentPos = getPosition();
            moveTo(new Position(currentPos.getX() + 10, currentPos.getY() + 5));
        }
    }
    
    private void handleObstacleUpdate(EnvironmentUpdate update) {
        System.out.println("[" + getName() + "] 检测到障碍物: " + update.getValue() + ", 执行避障操作");
        // 模拟避障
        Position currentPos = getPosition();
        moveTo(new Position(currentPos.getX() - 5, currentPos.getY() + 2));
    }
    
    private void handleWeatherUpdate(EnvironmentUpdate update) {
        if (update.getValue().contains("雨")) {
            System.out.println("[" + getName() + "] 检测到雨天天气, 降低作业速度");
        }
    }
} 