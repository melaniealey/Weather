package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;
import com.weather.Forecaster;

public interface MyForcasterInterface {

    Forecast forecastFor(Region region, Day day);
}
