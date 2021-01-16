package ro.mta.se.lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.*;

import org.json.simple.parser.JSONParser;
import ro.mta.se.lab.Init;
import ro.mta.se.lab.model.CityFromFile;
import ro.mta.se.lab.model.CityFromJson;

import javax.xml.crypto.Data;

public class VremeaController implements Initializable {
    @FXML private ComboBox<String> countryComboBox;
    @FXML private Label cityLabel;
    @FXML private ComboBox<String> cityComboBox;
    @FXML private Label tempLabel;
    @FXML private Label timeLabel;
    @FXML private Label typeWeatherLabel;
    @FXML private Label humidityLabel;
    @FXML private Label windLabel;
    @FXML private ImageView imageImageView;
    @FXML private Label descLabel;
    @FXML private Label cloudLabel;

    private ObservableList<CityFromFile> allCities = FXCollections.observableArrayList();

    public VremeaController(ObservableList<CityFromFile> allCities) {
        this.allCities = allCities;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ArrayList<String> allCountry = new ArrayList<>();
        for (CityFromFile city : allCities)
            allCountry.add(city.getCountryCity());
        List <String> distinctCountry = allCountry.stream().distinct().collect(Collectors.toList());
        for (String cnt : distinctCountry)
            countryComboBox.getItems().add(cnt);
    }

    public void whenCountryComboBoxIsSelected(){
        cityComboBox.getItems().clear();
        String x = countryComboBox.getValue();
        ArrayList <String> allCityFromOneCountry = new ArrayList<>();
        for (CityFromFile city : allCities){
            if(city.getCountryCity().equals(x))
                allCityFromOneCountry.add(city.getNameCity());
        }
        for (String cty :allCityFromOneCountry)
            cityComboBox.getItems().add(cty);

    }



    public void whenCityComboBoxIsSelected() throws Exception {
        String jsonString;
        Init initHttpRequest = new Init();
        jsonString = initHttpRequest.doHttpRequest(cityComboBox.getValue());


        Object obj = new JSONParser().parse(jsonString);
        JSONObject jo = (JSONObject) obj;

        CityFromJson cityParse = new CityFromJson(jo);

        this.cityLabel.setText(cityParse.getName());
        this.tempLabel.setText(Double.toString(cityParse.getTemp())+ "°C");
        this.typeWeatherLabel.setText(cityParse.getDescription());
        this.humidityLabel.setText(cityParse.getHumidity());
        //this.timeLabel.setText(cityParse.getTime());
        this.windLabel.setText(cityParse.getWindSpeed());
        this.imageImageView.setImage(new Image("https://openweathermap.org/img/wn/"+ cityParse.getImg()+"@2x.png"));
        this.descLabel.setText(cityParse.getDescLabel());
        this.cloudLabel.setText(cityParse.getCloudsPI());


       /*DateFormat format = new SimpleDateFormat("HHmm");
        Date date = format.parse(cityParse.getDateTime());
        this.timeLabel.setText(date.toString());*/

        long data = Long.parseLong(cityParse.getDateTime()) * 1000L;
        Date finalData = new Date(data);
        String sunrise = new SimpleDateFormat("E,hh:mm").format(finalData);
        System.out.println(sunrise);


    }
}
