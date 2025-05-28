package com.agritech.autonomousfarming.observer;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 环境监测类
 * 实现观察者模式中的主题(Subject)
 */
@Data
public class EnvironmentMonitor implements EnvironmentSubject {
    private List<EnvironmentObserver> observers;
    private String monitorId;
    private String monitorName;
    private boolean active;
    
    public EnvironmentMonitor(String monitorId, String monitorName) {
        this.observers = new ArrayList<>();
        this.monitorId = monitorId;
        this.monitorName = monitorName;
        this.active = true;
    }
    
    @Override
    public void registerObserver(EnvironmentObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("[环境监测器 " + monitorName + "] 注册观察者: " + observer.getClass().getSimpleName());
        }
    }
    
    @Override
    public void removeObserver(EnvironmentObserver observer) {
        observers.remove(observer);
        System.out.println("[环境监测器 " + monitorName + "] 移除观察者: " + observer.getClass().getSimpleName());
    }
    
    @Override
    public void notifyObservers(EnvironmentUpdate update) {
        System.out.println("[环境监测器 " + monitorName + "] 发布环境更新: " + update.getType() + " - " + update.getValue());
        for (EnvironmentObserver observer : observers) {
            observer.handleEnvironmentUpdate(update);
        }
    }
    
    /**
     * 模拟发布土壤湿度更新
     */
    public void publishSoilMoistureUpdate(double moisture) {
        if (!active) return;
        EnvironmentUpdate update = new EnvironmentUpdate(
            EnvironmentUpdate.EnvironmentUpdateType.SOIL_MOISTURE,
            String.valueOf(moisture)
        );
        notifyObservers(update);
    }
    
    /**
     * 模拟发布土壤pH值更新
     */
    public void publishSoilPhUpdate(double ph) {
        if (!active) return;
        EnvironmentUpdate update = new EnvironmentUpdate(
            EnvironmentUpdate.EnvironmentUpdateType.SOIL_PH,
            String.valueOf(ph)
        );
        notifyObservers(update);
    }
    
    /**
     * 模拟发布作物成熟度更新
     */
    public void publishCropMaturityUpdate(int maturity) {
        if (!active) return;
        EnvironmentUpdate update = new EnvironmentUpdate(
            EnvironmentUpdate.EnvironmentUpdateType.CROP_MATURITY,
            String.valueOf(maturity)
        );
        notifyObservers(update);
    }
    
    /**
     * 模拟发布天气更新
     */
    public void publishWeatherUpdate(String weather) {
        if (!active) return;
        EnvironmentUpdate update = new EnvironmentUpdate(
            EnvironmentUpdate.EnvironmentUpdateType.WEATHER,
            weather
        );
        notifyObservers(update);
    }
    
    /**
     * 模拟发布障碍物更新
     */
    public void publishObstacleUpdate(String obstacle) {
        if (!active) return;
        EnvironmentUpdate update = new EnvironmentUpdate(
            EnvironmentUpdate.EnvironmentUpdateType.OBSTACLE,
            obstacle
        );
        notifyObservers(update);
    }
} 