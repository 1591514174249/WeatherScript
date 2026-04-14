package org.lxc.weatherscript.service.impl;

import com.alibaba.fastjson2.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.lxc.weatherscript.entity.WeatherEntity;
import org.lxc.weatherscript.service.WeatherService;
import org.lxc.weatherscript.util.WeatherIconUtil;
import org.lxc.weatherscript.vo.WeatherReq;
import org.lxc.weatherscript.vo.WeatherRsp;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Override
    public WeatherEntity getWeatherInfo(WeatherReq weatherReq) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = "https://restapi.amap.com/v3/weather/weatherInfo?Key=" + weatherReq.getKey() + "&city=" + weatherReq.getCity() + "&extensions=" + weatherReq.getExtensions();
            HttpGet httpGet = new HttpGet(url);


            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                System.out.println("Status Code: " + response.getStatusLine().getStatusCode());

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String string = EntityUtils.toString(entity);
                    WeatherRsp weatherRsp = JSON.parseObject(string, WeatherRsp.class);
                    if (weatherRsp == null || !"1".equals(weatherRsp.getStatus()) || weatherRsp.getLives() == null || weatherRsp.getLives().length == 0) {
                        throw new RuntimeException("天气数据为空或API返回错误: " + (weatherRsp != null ? weatherRsp.getInfo() : "null"));
                    }
                    WeatherEntity weather = weatherRsp.getLives()[0];
                    // 添加天气图标
                    weather.setWeatherIcon(WeatherIconUtil.getWeatherIcon(weather.getWeather()));
                    return weather;
                }
            }
            return null;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
