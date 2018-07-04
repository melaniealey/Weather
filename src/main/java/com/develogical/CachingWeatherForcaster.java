package com.develogical;

import java.util.HashMap;
import java.util.Map;

public class CachingWeatherForcaster {
    MyForcasterInterface delegate;

    public CachingWeatherForcaster(MyForcasterInterface delegate) {
        this.delegate = delegate;
    }
    private Map<String, String> weatherCache= new HashMap<>();

    String forecastFor(final int region, int day){
        String key = String.valueOf(region) + String.valueOf(day);
        weatherCache.computeIfAbsent(key, k -> delegate.forecastFor(region, day));
//        if (!weatherCache.containsKey(key)) {
//            String fetchedValue = delegate.forecastFor(region, day);
//            weatherCache.put(key, fetchedValue);
//        }
        return weatherCache.get(key);
    }
}
