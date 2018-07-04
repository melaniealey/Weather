package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

import java.util.HashMap;
import java.util.Map;

public class CachingWeatherForcaster implements MyForcasterInterface{
    MyForcasterInterface delegate;

    public CachingWeatherForcaster(MyForcasterInterface delegate) {
        this.delegate = delegate;
    }
    private Map<String, Forecast> weatherCache= new HashMap<>();

    @Override
    public Forecast forecastFor(Region region, Day day){
        String key = String.valueOf(region) + String.valueOf(day);
        weatherCache.computeIfAbsent(key, k -> delegate.forecastFor(region, day));
//        if (!weatherCache.containsKey(key)) {
//            String fetchedValue = delegate.forecastFor(region, day);
//            weatherCache.put(key, fetchedValue);
//        }
        return weatherCache.get(key);
    }
}
