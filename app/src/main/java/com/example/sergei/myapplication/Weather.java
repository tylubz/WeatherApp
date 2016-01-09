package com.example.sergei.myapplication;

/**
 * Created by Sergei on 08.01.2016.
 */
public class Weather {

    private String cityName;
    private String sunrise;
    private String sunset;
    private String condition;
    private String temperature;
    private String lowTemperature;
    private String highTemperature;
    private String humidity;
    private String windSpeed;

    public void setCityName(String cityName){
        this.cityName=cityName;
    }
    public String getCityName(){
        return cityName;
    }
    public void setSunrise(String sunrise){
        this.sunrise=sunrise;
    }
    public String getSunrise(){
        return sunrise;
    }
    public void setSunset(String sunset){
        this.sunset=sunset;
    }
    public String getSunset(){
        return sunset;
    }
    public void setCondition(String condition){
        this.condition=condition;
    }
    public String getCondition(){
        return condition;
    }
    public void setTemperature(String temperature){
        this.temperature=temperature;
    }
    public String getTemperature(){
        return temperature;
    }
    public void setHighTemperature(String highTemperature){
        this.highTemperature=highTemperature;
    }
    public String getHighTemperature(){
        return highTemperature;
    }
    public void setLowTemperature(String lowTemperature){
        this.lowTemperature=lowTemperature;
    }
    public String getLowTemperature(){
        return lowTemperature;
    }
    public void setWindSpeed(String windSpeed){
        this.windSpeed=windSpeed;
    }
    public String getWindSpeed(){
        return windSpeed;
    }
    public void setHumidity(String humidity){
        this.humidity=humidity;
    }
    public String getHumidity(){
        return humidity;
    }

}
