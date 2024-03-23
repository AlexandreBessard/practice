package designPatterns;

import java.util.ArrayList;
import java.util.List;

/*
The Observer Pattern is a behavioral design pattern
where an object, known as the subject, maintains a list of
its dependents, called observers, and notifies them of any state changes,
 usually by calling one of their methods.

 Let's say we have a weather station that provides
 updates on temperature to multiple displays.
 Each display acts as an observer and displays the updated temperature.
 */
// Subject: Weather Station
interface Subject {
    void registerObserver(Observer1 observer);
    void removeObserver(Observer1 observer);
    void notifyObservers();
}
// Concrete Subject: WeatherData
class WeatherData implements Subject {
    // Subscribers
    private List<Observer1> observers;
    private float temperature;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer1 observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer1 observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer1 observer : observers) {
            observer.update(temperature);
        }
    }
}
// Observer: Observer
interface Observer1 {
    void update(float temperature);
}

// Concrete Observer: Display
class Display implements Observer1 {
    // Subscribe to
    private Subject weatherData;

    public Display(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature) {
        System.out.println("Temperature updated: " + temperature);
    }
}

public class Observer {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        Display display1 = new Display(weatherData);
        Display display2 = new Display(weatherData);

        // Simulate temperature updates
        weatherData.setTemperature(25.5f);
        weatherData.setTemperature(30.0f);

        // Display 2 unsubscribes from updates
        weatherData.removeObserver(display2);

        // Simulate temperature updates again
        weatherData.setTemperature(28.0f);
    }

}
