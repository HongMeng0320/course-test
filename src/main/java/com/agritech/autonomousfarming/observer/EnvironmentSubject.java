package com.agritech.autonomousfarming.observer;

/**
 * 环境主题接口
 * 实现观察者模式，所有发布环境更新的组件都需要实现该接口
 */
public interface EnvironmentSubject {
    /**
     * 注册观察者
     * @param observer 环境观察者
     */
    void registerObserver(EnvironmentObserver observer);
    
    /**
     * 移除观察者
     * @param observer 环境观察者
     */
    void removeObserver(EnvironmentObserver observer);
    
    /**
     * 通知所有观察者
     * @param update 环境更新信息
     */
    void notifyObservers(EnvironmentUpdate update);
} 