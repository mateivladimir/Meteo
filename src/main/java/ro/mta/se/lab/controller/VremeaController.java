package ro.mta.se.lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.json.simple.*;

import org.json.simple.parser.JSONParser;
import ro.mta.se.lab.Init;
import ro.mta.se.lab.model.CityFromFile;
import ro.mta.se.lab.model.CityFromJson;

public class VremeaController implements Initializable {
    @FXML private ComboBox<String> countryComboBox;
    @FXML private Label cityLabel;
    @FXML private ComboBox<String> cityComboBox;
    @FXML private Label tempLabel;
    @FXML private Label timeLabel;
    @FXML private Label typeWeatherLabel;
    @FXML private Label humidityLabel;
    @FXML private Label windLabel;

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
        this.tempLabel.setText(Double.toString(cityParse.getTemp()));
        this.typeWeatherLabel.setText(cityParse.getDescription());
        this.humidityLabel.setText(cityParse.getHumidity());
        this.timeLabel.setText(cityParse.getTime());
        this.windLabel.setText(cityParse.getWindSpeed());
    }
}
