package org.lxc.weatherscript.service;

import org.lxc.weatherscript.entity.WeatherEntity;
import org.lxc.weatherscript.vo.WeatherReq;

public interface WeatherService {
    WeatherEntity getWeatherInfo(WeatherReq weatherReq);
}
