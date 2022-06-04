import java.util.Scanner;

class Main {

  public static void main(String[] args) {
    // There are 2.54 cm in one inch.  Write a program to input the user’s height in inches and output their height in centimeters.  Use a constant for the conversion factor.
    Scanner input = new Scanner(System.in);
    System.out.println("Enter your height in inches: ");
    double height = input.nextDouble();
    final double CM_PER_INCH = 2.54;
    double heightInCm = height * CM_PER_INCH;
    System.out.println("Your height in centimeters is: " + heightInCm);
  }
}
