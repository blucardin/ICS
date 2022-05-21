import java.util.Scanner;

public class main {
    //throws Exception
    public static void main(String[] args) throws InterruptedException{
        Scanner key = new Scanner(System.in);
        System.out.println("Hello World!");
        System.out.println("And welcome to Noah's Games Emporium!");
        System.out.println("We have a variety of games for you to play!");

        Thread.sleep(2000);
        System.out.println("Enter a number for a game:");
        System.out.println("1. Snake");
      
        int selection = key.nextInt();
      
        if (selection == 1){
          snake.snake(args);
        }
      



    }
}