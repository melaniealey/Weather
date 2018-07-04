package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PutYourTestCodeInThisDirectoryTest {
    @Test
    public void weatherForecasterCachesForecaster() throws Exception {
        MyForcasterInterface delegate = mock(MyForcasterInterface.class); //mocking interface because we're not testing it

        CachingWeatherForcaster cacher = new CachingWeatherForcaster(delegate); //needs to do something to the forecaster

        Forecast forcast = new Forecast("some summary", 0);
        when(delegate.forecastFor(Region.LONDON, Day.MONDAY)).thenReturn(forcast); //faking behaviour of the "delegate" interface
        Forecast actual = cacher.forecastFor(Region.LONDON, Day.MONDAY); //calling a method (forecastFor) on the actual cacher...

        verify(delegate).forecastFor(Region.LONDON, Day.MONDAY); //...to check the "forecastFor" method has been called on the delegate
        assertEquals(forcast, actual); //check the cacher is returning the weather
    }

    @Test
    public void duplicateCallsHitCache() throws Exception {
        MyForcasterInterface delegate = mock(MyForcasterInterface.class); //mocking interface because we're not testing it

        CachingWeatherForcaster cacher = new CachingWeatherForcaster(delegate); //needs to do something to the forecaster

        Forecast forcast = new Forecast("some summary", 0);
        when(delegate.forecastFor(Region.LONDON, Day.MONDAY)).thenReturn(forcast); //faking behaviour of the "delegate" interface
        Forecast actual = cacher.forecastFor(Region.LONDON, Day.MONDAY); //calling a method (forecastFor) on the actual cacher...

        verify(delegate).forecastFor(Region.LONDON, Day.MONDAY); //...to check the "forecastFor" method has been called on the delegate
        assertEquals(forcast, actual); //check the cacher is returning the weather


        Forecast otherForcast = new Forecast("some other forecaset", 0);
        when(delegate.forecastFor(Region.LONDON, Day.MONDAY)).thenReturn(otherForcast); //faking behaviour of the "delegate" interface
        actual = cacher.forecastFor(Region.LONDON, Day.MONDAY); //calling a method (forecastFor) on the actual cacher...
        verifyNoMoreInteractions(delegate); //...to check the "forecastFor" method has been called on the delegate

        assertEquals(forcast, actual); //check the cacher is returning the weather


    }
}
