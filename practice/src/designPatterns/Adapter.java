package designPatterns;

/*
Adapter Pattern using an example where we have two interfaces
representing different types of temperature measurement:
Celsius and Fahrenheit. We'll create an adapter
to convert Celsius temperatures to Fahrenheit
 */

interface CelsiusTemperature {
    double getCelsius();
}

class FahrenheitTemperature {
    private double fahrenheit;

    public FahrenheitTemperature(double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public double getFahrenheit() {
        return fahrenheit;
    }
}

// Adapter: Celsius to Fahrenheit Adapter
class CelsiusToFahrenheitAdapter implements CelsiusTemperature {
    private FahrenheitTemperature fahrenheitTemperature;

    public CelsiusToFahrenheitAdapter(FahrenheitTemperature fahrenheitTemperature) {
        this.fahrenheitTemperature = fahrenheitTemperature;
    }

    @Override
    public double getCelsius() {
        // Convert Fahrenheit to Celsius
        return (fahrenheitTemperature.getFahrenheit() - 32) * 5 / 9;
    }
}

public class Adapter {

    public static void main(String[] args) {
        // Creating a Fahrenheit temperature object
        var fahrenheitTemperature = new FahrenheitTemperature(68);

        // Creating a Celsius to Fahrenheit adapter
        var adapter = new CelsiusToFahrenheitAdapter(fahrenheitTemperature);

        // Getting temperature in Celsius using adapter
        double celsius = adapter.getCelsius();

        System.out.println("Temperature in Celsius: " + celsius);
    }

}
