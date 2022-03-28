import java.util.Scanner;

class Main {
    public static void main(String[] args) {
      // ask  the user for the price of an item, then outputs the amount of HST for the item, followed by the total cost of the item
      Scanner input = new Scanner(System.in);
      System.out.println("Enter the price of the item: ");
      double price = input.nextDouble();
      final double HST_PERCENT = 0.13;
      double HST = price * HST_PERCENT;
      double total = price + HST;
      System.out.println("The HST is: " + HST);
      System.out.println("The total cost is: " + total);
    }
  }