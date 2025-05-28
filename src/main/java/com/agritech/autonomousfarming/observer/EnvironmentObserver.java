package com.agritech.autonomousfarming.observer;

/**
 * 环境观察者接口
 * 实现观察者模式，所有需要接收环境更新的组件都需要实现该接口
 */
public interface EnvironmentObserver {
    /**
     * 处理环境更新
     * @param update 环境更新信息
     */
    void handleEnvironmentUpdate(EnvironmentUpdate update);
} 