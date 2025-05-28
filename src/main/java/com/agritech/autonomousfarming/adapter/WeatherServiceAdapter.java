package com.agritech.autonomousfarming.adapter;

/**
 * 气象服务适配器类
 * 实现适配器模式，将第三方气象服务适配到系统接口
 */
public class WeatherServiceAdapter implements WeatherService {
    private ThirdPartyWeatherService thirdPartyService;
    
    public WeatherServiceAdapter() {
        this.thirdPartyService = new ThirdPartyWeatherService();
    }
    
    @Override
    public double getTemperature(double latitude, double longitude) {
        // 将第三方服务的华氏度转换为摄氏度
        float fahrenheit = thirdPartyService.fetchTemperatureData((float)latitude, (float)longitude);
        return (fahrenheit - 32) * 5 / 9;
    }
    
    @Override
    public double getHumidity(double latitude, double longitude) {
        // 将0-1的湿度值转换为百分比
        float humidity = thirdPartyService.fetchHumidityData((float)latitude, (float)longitude);
        return humidity * 100;
    }
    
    @Override
    public double getWindSpeed(double latitude, double longitude) {
        // 将英里/小时转换为米/秒
        float mphWindSpeed = thirdPartyService.fetchWindData((float)latitude, (float)longitude);
        return mphWindSpeed * 0.44704;
    }
    
    @Override
    public String getWeatherDescription(double latitude, double longitude) {
        int weatherCode = thirdPartyService.fetchWeatherCode((float)latitude, (float)longitude);
        
        // 将天气代码转换为描述
        switch (weatherCode) {
            case 1: return "晴天";
            case 2: return "多云";
            case 3: return "阴天";
            case 4: return "小雨";
            case 5: return "大雨";
            case 6: return "雷暴";
            default: return "未知天气";
        }
    }
} 