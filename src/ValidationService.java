package src;
import java.util.Scanner;

public class ValidationService implements IValidationService {

    @Override
    public int getValidNumericInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    @Override
    public double getValidDoubleInput(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Enter a valid temperature: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}