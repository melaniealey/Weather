package com.develogical;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PutYourTestCodeInThisDirectoryTest {
    @Test
    public void weatherForecasterCachesForecaster() throws Exception {
        MyForcasterInterface delegate = mock(MyForcasterInterface.class); //mocking interface because we're not testing it

        CachingWeatherForcaster cacher = new CachingWeatherForcaster(delegate); //needs to do something to the forecaster

        when(delegate.forecastFor(1, 2)).thenReturn("some string"); //faking behaviour of the "delegate" interface
        String actual = cacher.forecastFor(1, 2); //calling a method (forecastFor) on the actual cacher...

        verify(delegate).forecastFor(1,2 ); //...to check the "forecastFor" method has been called on the delegate
        assertEquals("some string", actual); //check the cacher is returning the weather
    }

    @Test
    public void duplicateCallsHitCache() throws Exception {
        MyForcasterInterface delegate = mock(MyForcasterInterface.class); //mocking interface because we're not testing it

        CachingWeatherForcaster cacher = new CachingWeatherForcaster(delegate); //needs to do something to the forecaster

        when(delegate.forecastFor(1, 2)).thenReturn("some string"); //faking behaviour of the "delegate" interface
        String actual = cacher.forecastFor(1, 2); //calling a method (forecastFor) on the actual cacher...

        verify(delegate).forecastFor(1,2 ); //...to check the "forecastFor" method has been called on the delegate
        assertEquals("some string", actual); //check the cacher is returning the weather


        when(delegate.forecastFor(1, 2)).thenReturn("some other string"); //faking behaviour of the "delegate" interface
        actual = cacher.forecastFor(1, 2); //calling a method (forecastFor) on the actual cacher...
        verifyNoMoreInteractions(delegate); //...to check the "forecastFor" method has been called on the delegate

        assertEquals("some string", actual); //check the cacher is returning the weather


    }
}
