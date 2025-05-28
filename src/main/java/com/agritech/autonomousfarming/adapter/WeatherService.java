package com.agritech.autonomousfarming.adapter;

/**
 * 第三方气象服务接口
 * 模拟外部气象服务
 */
public interface WeatherService {
    /**
     * 获取当前温度
     * @param latitude 纬度
     * @param longitude 经度
     * @return 温度（摄氏度）
     */
    double getTemperature(double latitude, double longitude);
    
    /**
     * 获取当前湿度
     * @param latitude 纬度
     * @param longitude 经度
     * @return 湿度（百分比）
     */
    double getHumidity(double latitude, double longitude);
    
    /**
     * 获取当前风速
     * @param latitude 纬度
     * @param longitude 经度
     * @return 风速（米/秒）
     */
    double getWindSpeed(double latitude, double longitude);
    
    /**
     * 获取天气描述
     * @param latitude 纬度
     * @param longitude 经度
     * @return 天气描述
     */
    String getWeatherDescription(double latitude, double longitude);
} 