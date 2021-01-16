package ro.mta.se.lab.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
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
    private String img;
    private String descLabel;
    private String cloudsPI;
    private String dateTime;
    private String sunriseTime;
    private String sunsetTime;
    private String minMax;

    public CityFromJson(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        this.name = (String) jsonObject.get("name");

        JSONObject mainJSON = (JSONObject) jsonObject.get("main");
        this.temp = (double) mainJSON.get("temp");
        var x = mainJSON.get("humidity");
        this.humidity = x.toString();
        double min = (double) mainJSON.get("temp_min");
        min = (int) (min-273.15);
        double max = (double) mainJSON.get("temp_max");
        max = (int) (max-273.15);
        this.minMax = min + " °C/ " + max + " °C";


        JSONArray weather = (JSONArray) jsonObject.get("weather");
        JSONObject weatherobj = (JSONObject) weather.get(0);
        this.description = (String) weatherobj.get("main");
        this.descLabel = (String) weatherobj.get("description");
        this.img = (String) weatherobj.get("icon");

        JSONObject wind = (JSONObject) jsonObject.get("wind");
        var ws = wind.get("speed");
        this.windSpeed = ws.toString();

        JSONObject cld = (JSONObject) jsonObject.get("clouds");
        var clouds =  cld.get("all");
        this.cloudsPI = clouds.toString();

        var data= jsonObject.get("dt");
        String dateTime = data.toString();

        long time = Long.parseLong(dateTime) * 1000L;
        Date finalData = new Date(time);
        this.dateTime = new SimpleDateFormat("E,HH:mm").format(finalData);

        JSONObject sys = (JSONObject) jsonObject.get("sys");
        var sr = sys.get("sunrise");
        var ss = sys.get("sunset");

        String dateTime2 = sr.toString();
        String dateTime3 = ss.toString();
        long time2 = Long.parseLong(dateTime2) * 1000L;
        long time3 = Long.parseLong(dateTime3) * 1000L;
        Date finalData2 = new Date(time2);
        Date finalData3 = new Date(time3);

        this.sunriseTime = new SimpleDateFormat("HH:mm").format(finalData2);
        this.sunsetTime = new SimpleDateFormat("HH:mm").format(finalData3);



    }

    public String getName() {
        return name;
    }

    public double getTemp() {
        return (int) (temp - 273.15);
    }

    public String getDescription() {
        return description;
    }

    public String getHumidity() {
        return "Humidity : " + this.humidity + " % ";
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

    public String getImg() {
        return img;
    }

    public String getDescLabel() {
        return descLabel;
    }

    public String getCloudsPI() {
        return "Clouds : " +cloudsPI + " %";
    }

    public String getDateTime() {
        return "Time of data calculation : " + dateTime;
    }

    public String getSunriseTime() {
        return "Sunrise : " + sunriseTime;
    }

    public String getSunsetTime() {
        return "Sunset : " + sunsetTime;
    }

    public String getMinMax() {
        return minMax;
    }
}
