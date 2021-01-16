package ro.mta.se.lab.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CityFromJson {
    private JSONObject jsonObject;
    private String name;
    private double temp;
    private String description;
    private String humidity;
    private String windSpeed;

    public CityFromJson(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        this.name = (String) jsonObject.get("name");

        JSONObject mainJSON = (JSONObject) jsonObject.get("main");
        this.temp = (double) mainJSON.get("temp");
        var x = mainJSON.get("humidity");
        this.humidity = x.toString();


        JSONArray weather = (JSONArray) jsonObject.get("weather");
        JSONObject weatherobj = (JSONObject) weather.get(0);
        this.description = (String) weatherobj.get("main");

        JSONObject wind = (JSONObject) jsonObject.get("wind");
        var ws = wind.get("speed");
        this.windSpeed = ws.toString();


    }

    public String getName() {
        return name;
    }

    public double getTemp() {
        return temp;
    }

    public String getDescription() {
        return description;
    }

    public String getHumidity() {
        return "humidity : " + this.humidity + " % ";
    }
    public String getTime(){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int hour = gregorianCalendar.get(Calendar.HOUR) ;
        int min = gregorianCalendar.get(Calendar.MINUTE);
        String data = String.valueOf(hour) + " : " + String.valueOf(min);
        return data;
    }

    public String getWindSpeed() {
        return "Wind speed : " + windSpeed + " km/h";
    }
}
