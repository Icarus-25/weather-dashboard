package edu.example.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.example.weather.model.WeatherData;
import java.io.File;
import java.io.IOException;

public class WeatherService {
    private final ObjectMapper mapper = new ObjectMapper();

    public WeatherData loadWeather(String city) throws IOException {
        String filename = "sample-data/" + city.toLowerCase() + ".json";
        return mapper.readValue(new File(filename), WeatherData.class);
    }
}