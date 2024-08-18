package src;
import java.util.ArrayList;

public class TemperatureService implements ITemperatureService {

    private ArrayList<String> days;
    private ArrayList<Double> temperaturesF;
    private ArrayList<Double> temperaturesC;

    public TemperatureService() {
        days = new ArrayList<>();
        temperaturesF = new ArrayList<>();
        temperaturesC = new ArrayList<>();
    }

    @Override
    public void addDayAndTemperature(String day, double temperatureF, double temperatureC) {
        days.add(day);
        temperaturesF.add(temperatureF);
        temperaturesC.add(temperatureC);
    }

    @Override
    public String getDayFromChoice(int choice) {
        switch (choice) {
            case 1: return "Sunday";
            case 2: return "Monday";
            case 3: return "Tuesday";
            case 4: return "Wednesday";
            case 5: return "Thursday";
            case 6: return "Friday";
            case 7: return "Saturday";
            default: return null;
        }
    }

    @Override
    public ArrayList<String> getDays() {
        return days;
    }

    @Override
    public ArrayList<Double> getTemperaturesF() {
        return temperaturesF;
    }

    @Override
    public ArrayList<Double> getTemperaturesC() {
        return temperaturesC;
    }

    @Override
    public double calculateWeeklyAverageF() {
        double sum = 0.0;
        for (Double temp : temperaturesF) {
            sum += temp;
        }
        return temperaturesF.size() > 0 ? sum / temperaturesF.size() : 0;
    }

    @Override
    public double convertToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }
}