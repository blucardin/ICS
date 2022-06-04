import java.util.Scanner;

public class YesOrNo {

  //Write a program that forces a user to supply either y or n in response to the question “Continue?  Respond with y or n” use a do while loop.
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String answer;
    do {
      System.out.println("Continue? Respond with y or n");
      answer = input.next();
    } while (!answer.equals("y") && !answer.equals("n"));
    System.out.println("Thank you for your response");
  }
}
