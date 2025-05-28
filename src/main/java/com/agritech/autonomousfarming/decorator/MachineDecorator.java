package com.agritech.autonomousfarming.decorator;

import com.agritech.autonomousfarming.model.AgriculturalMachine;
import com.agritech.autonomousfarming.model.MachineStatus;
import com.agritech.autonomousfarming.model.MachineType;
import com.agritech.autonomousfarming.model.Position;
import com.agritech.autonomousfarming.model.Task;
import com.agritech.autonomousfarming.observer.EnvironmentUpdate;

/**
 * 农机装饰器抽象类
 * 实现装饰器模式
 */
public abstract class MachineDecorator implements AgriculturalMachine {
    protected AgriculturalMachine decoratedMachine;
    
    public MachineDecorator(AgriculturalMachine decoratedMachine) {
        this.decoratedMachine = decoratedMachine;
    }
    
    @Override
    public String getId() {
        return decoratedMachine.getId();
    }
    
    @Override
    public String getName() {
        return decoratedMachine.getName();
    }
    
    @Override
    public MachineType getType() {
        return decoratedMachine.getType();
    }
    
    @Override
    public Position getPosition() {
        return decoratedMachine.getPosition();
    }
    
    @Override
    public void setPosition(Position position) {
        decoratedMachine.setPosition(position);
    }
    
    @Override
    public MachineStatus getStatus() {
        return decoratedMachine.getStatus();
    }
    
    @Override
    public void setStatus(MachineStatus status) {
        decoratedMachine.setStatus(status);
    }
    
    @Override
    public double getFuelLevel() {
        return decoratedMachine.getFuelLevel();
    }
    
    @Override
    public void setFuelLevel(double fuelLevel) {
        decoratedMachine.setFuelLevel(fuelLevel);
    }
    
    @Override
    public void performTask(Task task) {
        decoratedMachine.performTask(task);
    }
    
    @Override
    public void handleEnvironmentUpdate(EnvironmentUpdate update) {
        decoratedMachine.handleEnvironmentUpdate(update);
    }
    
    @Override
    public void moveTo(Position position) {
        decoratedMachine.moveTo(position);
    }
    
    @Override
    public void printStatus() {
        decoratedMachine.printStatus();
    }
} 