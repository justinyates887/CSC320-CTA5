package src;
import java.util.ArrayList;

public interface ITemperatureService {
    void addDayAndTemperature(String day, double temperatureF, double temperatureC);
    String getDayFromChoice(int choice);
    ArrayList<String> getDays();
    ArrayList<Double> getTemperaturesF();
    ArrayList<Double> getTemperaturesC();
    double calculateWeeklyAverageF();
    double convertToCelsius(double fahrenheit);
}