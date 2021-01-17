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

/**
 * Clasa care va parsa JSON-ul si va returna informatiile importante din aceasta
 *
 * @author MateiMunteanu
 */
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

    /**
     * Modalitate de a transforma temperatura in grade kelvin de tip double
     * in temperatura in grade celsius de tip int
     * @param kelvin - JSON-ul va trimite temperatura in grade kelvin
     * @return - temperatura in grade celsius
     */
    public int convertKtoC(double kelvin)
    {
        return (int) (kelvin - 273.15);
    }

    /**
     * Metoda prin care se calculeaza data curenta. In JSON este mentrionata data
     * in unix UTC, pe care noi dorim sa o fomatam astfel incat sa fie citibila.
     * @param value - string care va reprezenta ora in unix UTC
     * @return - string de forma "ora-ora : min-min".
     */
    public String calculateData (String value)
    {
        long time = Long.parseLong(value) * 1000L;
        Date finalData = new Date(time);
        return new SimpleDateFormat("HH:mm").format(finalData);
    }

    /**
     * Constructorul explicit care va parsa JSON-ul in toate elementele necesare aplicatiei.
     *
     * @param jsonObject - obiect de tip JSONObject, primit ca raspuns de la HttpReqest.
     */
    public CityFromJson(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        this.name = (String) jsonObject.get("name");

        JSONObject mainJSON = (JSONObject) jsonObject.get("main");
        this.temp = (double) mainJSON.get("temp");
        var x = mainJSON.get("humidity");
        this.humidity = x.toString();
        double min = (double) mainJSON.get("temp_min");
        min = convertKtoC(min);
        double max = (double) mainJSON.get("temp_max");
        max = convertKtoC(max);
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
        this.dateTime = calculateData(dateTime);

        JSONObject sys = (JSONObject) jsonObject.get("sys");
        var sr = sys.get("sunrise");
        var ss = sys.get("sunset");
        String dateTime2 = sr.toString();
        String dateTime3 = ss.toString();
        this.sunriseTime = calculateData(dateTime2);
        this.sunsetTime = calculateData(dateTime3);
    }

    /**
     *
     * @return - numele orasului
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return - temperatura in momentul de fata
     */
    public double getTemp() {
        return (int) (temp - 273.15);
    }

    /**
     *
     * @return - descrierea vremii in momentul de fata
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return - umiditatea (ex : 90 %)
     */
    public String getHumidity() {
        return "Humidity : " + this.humidity + " % ";
    }

    /**
     *
     * @return - timpul actual al calculatorului care ruleaza aplicatia.
     */
    public String getTime(){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int hour = gregorianCalendar.get(Calendar.HOUR) ;
        int min = gregorianCalendar.get(Calendar.MINUTE);
        String data = String.valueOf(hour) + " : " + String.valueOf(min);
        return data;
    }

    /**
     *
     * @return - viteza vantului
     */
    public String getWindSpeed() {
        return "Wind speed : " + windSpeed + " m/s";
    }

    /**
     *
     * @return - denumirea imaginii vremii ( ex: soare - 01c, nori - 01d, etc)
     */
    public String getImg() {
        return img;
    }

    /**
     *
     * @return
     */
    public String getDescLabel() {
        return descLabel;
    }

    /**
     *
     * @return - procentul de nori prezenti pe cer
     */
    public String getCloudsPI() {
        return "Clouds : " +cloudsPI + " %";
    }

    /**
     *
     * @return - data cand s-a calculat ultima data vremea in acea localitate
     */
    public String getDateTime() {
        return "Time of data calculation : " + dateTime;
    }

    /**
     *
     * @return - ora si minutul cand rasare soarele
     */
    public String getSunriseTime() {
        return "Sunrise : " + sunriseTime;
    }

    /**
     *
     * @return - ora si minutul cand apune soarele
     */
    public String getSunsetTime() {
        return "Sunset : " + sunsetTime;
    }

    /**
     *
     * @return - string care va afisa temperatura minima, precum si cea maxima
     * din acea zi.
     */
    public String getMinMax() {
        return minMax;
    }
}
