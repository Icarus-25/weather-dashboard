package edu.example.weather.ui;

import edu.example.weather.model.WeatherData;
import edu.example.weather.service.WeatherService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;

public class WeatherDashboardView extends BorderPane {
    private final WeatherService service = new WeatherService();
    private final Label tempLabel = new Label();
    private final Label conditionLabel = new Label();
    private final Label humidityLabel = new Label();

    public WeatherDashboardView() {
        setPadding(new Insets(20));

        Label title = new Label("Weather Dashboard - Test");
        title.setStyle("-fx-font-size: 28;");

        ComboBox<String> cityBox = new ComboBox<>();
        cityBox.getItems().addAll("Seattle", "Redmond", "Bellevue");
        cityBox.setValue("Seattle");

        cityBox.setOnAction(e -> updateWeather(cityBox.getValue()));

        VBox center = new VBox(10, tempLabel, conditionLabel, humidityLabel);
        center.setAlignment(Pos.CENTER);

        setTop(new VBox(10, title, cityBox));
        setCenter(center);

        // Load initial city
        updateWeather("Seattle");
    }

    private void updateWeather(String city) {
        try {
            WeatherData data = service.loadWeather(city);
            tempLabel.setText(data.temperature + "°F");
            conditionLabel.setText(StringUtils.capitalize(data.condition.toLowerCase()));
            humidityLabel.setText("Humidity: " + data.humidity + "%");
        } catch (Exception ex) {
            tempLabel.setText("Error loading data");
            conditionLabel.setText(ex.getMessage());
            humidityLabel.setText("");
        }
    }
}