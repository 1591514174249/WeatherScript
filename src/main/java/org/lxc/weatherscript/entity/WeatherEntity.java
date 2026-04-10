package org.lxc.weatherscript.entity;

import lombok.Data;

@Data
public class WeatherEntity {
    private String province;
    private String city;
    private String adcode;
    private String weather;
    private Integer temperature;
    private String winddirection;
    private String windpower;
    private String humidity;
    private String reporttime;
    private String temperature_float;
    private String humidity_float;
    private String weatherIcon;
}
