package com.agritech.autonomousfarming.adapter;

import java.util.Random;

/**
 * 第三方气象服务实现类
 * 模拟外部气象服务的实现
 */
public class ThirdPartyWeatherService {
    private Random random = new Random();
    
    /**
     * 获取温度数据
     * @param lat 纬度
     * @param lon 经度
     * @return 温度数据（华氏度）
     */
    public float fetchTemperatureData(float lat, float lon) {
        // 模拟获取温度数据（华氏度）
        return 60.0f + random.nextFloat() * 30.0f;
    }
    
    /**
     * 获取湿度数据
     * @param lat 纬度
     * @param lon 经度
     * @return 湿度数据（0-1）
     */
    public float fetchHumidityData(float lat, float lon) {
        // 模拟获取湿度数据
        return random.nextFloat();
    }
    
    /**
     * 获取风速数据
     * @param lat 纬度
     * @param lon 经度
     * @return 风速数据（英里/小时）
     */
    public float fetchWindData(float lat, float lon) {
        // 模拟获取风速数据（英里/小时）
        return random.nextFloat() * 20.0f;
    }
    
    /**
     * 获取天气代码
     * @param lat 纬度
     * @param lon 经度
     * @return 天气代码
     */
    public int fetchWeatherCode(float lat, float lon) {
        // 模拟获取天气代码
        // 1: 晴天, 2: 多云, 3: 阴天, 4: 小雨, 5: 大雨, 6: 雷暴
        return random.nextInt(6) + 1;
    }
} 