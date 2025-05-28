package com.agritech.autonomousfarming.model;

import com.agritech.autonomousfarming.observer.EnvironmentUpdate;
import com.agritech.autonomousfarming.strategy.PrecisionPathPlanningStrategy;

/**
 * 智能播种机类
 */
public class Seeder extends AbstractAgriculturalMachine {
    private String seedType;
    private double seedAmount;
    private double seedingDepth;
    
    public Seeder(String id, String name) {
        super(id, name, MachineType.SEEDER);
        this.seedType = "玉米";
        this.seedAmount = 100.0;
        this.seedingDepth = 5.0;
        this.pathPlanningStrategy = new PrecisionPathPlanningStrategy();
    }
    
    public void setSeedType(String seedType) {
        this.seedType = seedType;
        System.out.println("[" + getName() + "] 已更换种子类型: " + seedType);
    }
    
    public void adjustSeedingDepth(double depth) {
        this.seedingDepth = depth;
        System.out.println("[" + getName() + "] 已调整播种深度: " + depth + "厘米");
    }
    
    public void refillSeeds(double amount) {
        this.seedAmount += amount;
        System.out.println("[" + getName() + "] 已补充种子: " + amount + "公斤, 当前总量: " + this.seedAmount + "公斤");
    }
    
    @Override
    protected void adjustOperationBasedOnEnvironment(EnvironmentUpdate update) {
        switch(update.getType()) {
            case SOIL_MOISTURE:
                handleSoilMoistureUpdate(update);
                break;
            case SOIL_PH:
                handleSoilPhUpdate(update);
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
            System.out.println("[" + getName() + "] 检测到土壤湿度过高 (" + moisture + "%), 推迟播种操作");
            setStatus(MachineStatus.IDLE);
        } else if (moisture < 20) {
            System.out.println("[" + getName() + "] 检测到土壤湿度过低 (" + moisture + "%), 增加播种深度");
            adjustSeedingDepth(seedingDepth + 1.0);
        }
    }
    
    private void handleSoilPhUpdate(EnvironmentUpdate update) {
        double ph = Double.parseDouble(update.getValue());
        if (ph < 5.5 || ph > 8.0) {
            System.out.println("[" + getName() + "] 检测到土壤pH值异常 (" + ph + "), 调整播种密度");
        }
    }
    
    private void handleWeatherUpdate(EnvironmentUpdate update) {
        if (update.getValue().contains("雨")) {
            System.out.println("[" + getName() + "] 检测到雨天天气, 暂停播种作业");
            setStatus(MachineStatus.IDLE);
        }
    }
} 