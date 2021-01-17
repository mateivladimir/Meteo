package ro.mta.se.lab;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.VremeaController;
import ro.mta.se.lab.model.CityFromFile;

import java.io.IOException;

/**
 * Clasa Main va incepe rularea aplicatiei. Aceasta va creea mai intai obiectul de tip
 * ObservableList cu toate informatiile din fisier, dupa care va incepe rularea interfetei
 * grafice.
 *
 * @author MateiMunteanu
 */
public class Main extends Application {

    private ObservableList<CityFromFile> allCities = FXCollections.observableArrayList();
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Init x = new Init();
        allCities = x.getAllInfoFromFile();

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(this.getClass().getResource("/view/VremeaView.fxml"));
            loader.setController(new VremeaController(allCities));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}