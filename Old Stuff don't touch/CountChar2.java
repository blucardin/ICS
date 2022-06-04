import java.util.Scanner;

public class CountChar2 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int num = 0;
    int count = 0;
    do {
      System.out.println("Enter an integer");
      num = input.nextInt();
      count++;
    } while (num > 0);
    System.out.println("You entered " + (count - 1) + " integers");
  }
}
