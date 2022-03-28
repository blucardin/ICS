import java.util.Scanner;

class Main {
    public static void main(String[] args) {
      // Write a program that asks for an amount of United States money and then prints the Canadian equivalent.  Canadian $ = U.S. $ * (10/9)
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the amount of US dollars: ");
        double usDollars = input.nextDouble();
        final double CANADIAN_DOLLAR_PER_US_DOLLAR = 10.0/9.0;
        double canadianDollars = usDollars * CANADIAN_DOLLAR_PER_US_DOLLAR;
        System.out.println("The amount of Canadian dollars is: " + canadianDollars);

    }
  }