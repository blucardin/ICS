import java.util.Scanner;

public class CountChar1 {
    //Using a do… while loop, write a program that allows the user to continuously enter integers.  The user will enter ‘-1’ to indicate the end of input.  The program will then output the number of integers that were entered.

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = 0;
        int count = 0;
        do {
            System.out.println("Enter an integer");
            num = input.nextInt();
            count++;
        } while (num != -1);
        System.out.println("You entered " + (count - 1) + " integers");
    }
    
}
