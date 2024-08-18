package src;
//Pseudocode
//1. Display a menu where the user selects a numeric option for the day of the week, weekly overview, or ending the program.
//2. Use dependency injection to inject the temperature service and validation service.
//3. Validate numeric input for menu selection.
//4. If a day (1-7) is selected, check if the day has already been entered.
//5. If not entered, prompt the user to input a temperature in Fahrenheit and validate the input.
//6. Store the day and temperature using the temperature service.
//7. If "Weekly Overview" (8) is selected, display the temperatures for all days in both Fahrenheit and Celsius, along with the weekly average.
//8. Loop the menu until the user selects "End program" (9).
//9. Close the scanner and terminate the program when the user selects the end option.

import java.util.Scanner;

public class Main {

    private ITemperatureService temperatureService;
    private IValidationService validationService;

    public Main(ITemperatureService temperatureService, IValidationService validationService) {
        this.temperatureService = temperatureService;
        this.validationService = validationService;
    }

    public static void main(String[] args) {
        ITemperatureService temperatureService = new TemperatureService();
        IValidationService validationService = new ValidationService();
        Main main = new Main(temperatureService, validationService);

        main.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Sunday");
            System.out.println("2. Monday");
            System.out.println("3. Tuesday");
            System.out.println("4. Wednesday");
            System.out.println("5. Thursday");
            System.out.println("6. Friday");
            System.out.println("7. Saturday");
            System.out.println("8. Weekly Overview");
            System.out.println("9. End program");
            System.out.print("Your choice: ");

            int choice = validationService.getValidNumericInput(scanner);

            switch (choice) {
                case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                    String day = temperatureService.getDayFromChoice(choice);
                    if (temperatureService.getDays().contains(day)) {
                        System.out.println(day + " has already been entered.");
                    } else {
                        System.out.print("Enter temperature for " + day + " (°F): ");
                        double temperatureF = validationService.getValidDoubleInput(scanner);
                        double temperatureC = temperatureService.convertToCelsius(temperatureF);
                        temperatureService.addDayAndTemperature(day, temperatureF, temperatureC);
                    }
                    break;
                case 8:
                    displayWeekSummary();
                    break;
                case 9:
                    running = false;
                    System.out.println("Program ended.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    private void displayWeekSummary() {
        System.out.println("\nWeek Summary (Fahrenheit and Celsius):");
        for (int i = 0; i < temperatureService.getDays().size(); i++) {
            double tempF = temperatureService.getTemperaturesF().get(i);
            double tempC = temperatureService.getTemperaturesC().get(i);
            System.out.printf("%s: %.2f°F (%.2f°C)%n", temperatureService.getDays().get(i), tempF, tempC);
        }
        System.out.printf("Weekly Average: %.2f°F (%.2f°C)%n",
                          temperatureService.calculateWeeklyAverageF(),
                          temperatureService.convertToCelsius(temperatureService.calculateWeeklyAverageF()));
    }
}
