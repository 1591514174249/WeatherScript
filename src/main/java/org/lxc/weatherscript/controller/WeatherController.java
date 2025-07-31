package org.lxc.weatherscript.controller;
import org.lxc.weatherscript.entity.WeatherEntity;
import org.lxc.weatherscript.service.WeatherService;
import org.lxc.weatherscript.vo.WeatherReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/weather/info")
    public WeatherEntity getWeatherInfo(@RequestBody WeatherReq weatherReq) {
        return weatherService.getWeatherInfo(weatherReq);
    }
}
