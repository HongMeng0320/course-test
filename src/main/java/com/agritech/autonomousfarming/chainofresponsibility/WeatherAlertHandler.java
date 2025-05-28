package com.agritech.autonomousfarming.chainofresponsibility;

import com.agritech.autonomousfarming.model.AgriculturalMachine;
import com.agritech.autonomousfarming.model.MachineStatus;

/**
 * 天气警报处理器类
 * 责任链模式中的具体处理器
 */
public class WeatherAlertHandler extends AbstractSafetyHandler {
    private AgriculturalMachine machine;
    
    public WeatherAlertHandler(AgriculturalMachine machine) {
        this.machine = machine;
    }
    
    @Override
    protected boolean canHandle(SafetyEvent event) {
        return event.getType() == SafetyEvent.EventType.WEATHER_ALERT;
    }
    
    @Override
    protected boolean doHandle(SafetyEvent event) {
        System.out.println("[天气警报处理器] 处理天气警报事件: " + event.getDescription());
        
        String weatherDescription = event.getDescription().toLowerCase();
        
        // 根据不同天气状况采取不同措施
        if (weatherDescription.contains("雷暴") || 
            weatherDescription.contains("暴雨") || 
            weatherDescription.contains("冰雹")) {
            // 恶劣天气，紧急停止作业
            System.out.println("[天气警报处理器] 检测到恶劣天气，执行紧急停止作业");
            machine.setStatus(MachineStatus.IDLE);
            return true;
        } else if (weatherDescription.contains("大雨") || 
                   weatherDescription.contains("大风")) {
            // 不良天气，减速作业
            System.out.println("[天气警报处理器] 检测到不良天气，减速作业");
            // 模拟减速
            return true;
        } else if (weatherDescription.contains("小雨")) {
            // 轻微天气，继续作业但监控
            System.out.println("[天气警报处理器] 检测到轻微天气变化，继续作业但增加监控频率");
            return true;
        }
        
        System.out.println("[天气警报处理器] 天气状况可接受，继续正常作业");
        return true;
    }
} 