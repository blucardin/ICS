/*  This is the main file that is run at start. 
 * It prints the welcome message and calls methods for each game based on what the user wants to play.
 * 
 * All code formatting was done with Prittier: https://github.com/jhipster/prettier-java
 * Array syntax was taken from: https://www.w3schools.com/java/java_arrays_multi.asp
 * ArrayList syntax was taken from: https://www.w3schools.com/java/java_arraylist.asp 
 * Switch statement syntax was taken from: https://www.w3schools.com/java/java_switch.asp
 * Multi-dimensional arrayList syntax was taken from: https://stackoverflow.com/questions/4401850/how-to-create-a-multidimensional-arraylist-in-java
 */

import java.util.Scanner;

public class app { 

  public static int coins = 0; // create public variable for coins
  public static boolean devMode = true; // create public variable for devMode, this disabled the auto-error catching
  static Scanner key = new Scanner(System.in); // create a scanner for the keyboard

  public static void picker(String pick) throws InterruptedException{ // create a method called picker, this method will be used to pick a game when the user enters a number
    switch (pick) {
          case "1":
            snake.menu(); // if the user enters 1, call the snake menu
            break;
          case "2":
            jeopardy.menu(); // if the user enters 2, call the jeopardy menu
            break;
          case "3":
            battleship.menu(); // if the user enters 3, call the battleship menu
            break;
          case "4":
            tictactoe.menu(); // if the user enters 4, call the tictactoe menu
            break;
          case "5":
            System.out.println("Goodbye!"); // if the user enters 5, print goodbye and exit the program
            System.exit(0);
            key.close();
            break;
          default:
            System.out.println("Invalid input. Please try again."); // if the user enters an invalid input, print an error message and ask them to try again
            break;
        }
  }

  //throws Exception
  public static void main(String[] args) throws InterruptedException {
    while (true) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("Hello World!");
      System.out.println("And welcome to:"); // print out the welcome message and title
      System.out.println(
        "\033[0;101m ███    ██  ██████   █████  ██   ██ ███████      ██████   █████  ███    ███ ███████     ███████ ███    ███ ██████   ██████  ██████  ██ ██    ██ ███    ███	" +
        "\n" +
        "\033[0;103m ████   ██ ██    ██ ██   ██ ██   ██ ██          ██       ██   ██ ████  ████ ██          ██      ████  ████ ██   ██ ██    ██ ██   ██ ██ ██    ██ ████  ████	" +
        "\n" +
        "\033[0;102m ██ ██  ██ ██    ██ ███████ ███████ ███████     ██   ███ ███████ ██ ████ ██ █████       █████   ██ ████ ██ ██████  ██    ██ ██████  ██ ██    ██ ██ ████ ██	" +
        "\n" +
        "\033[0;104m ██  ██ ██ ██    ██ ██   ██ ██   ██      ██     ██    ██ ██   ██ ██  ██  ██ ██          ██      ██  ██  ██ ██      ██    ██ ██   ██ ██ ██    ██ ██  ██  ██	" +
        "\n" +
        "\033[0;105m ██   ████  ██████  ██   ██ ██   ██ ███████      ██████  ██   ██ ██      ██ ███████     ███████ ██      ██ ██       ██████  ██   ██ ██  ██████  ██      ██	" +
        "\n"
      );
      System.out.println("\033[0;0m");
      System.out.println("We have a variety of games for you to play!");

      Thread.sleep(2000);
      System.out.println("You have " + coins + " coins to spend!\n"); // print out the number of coins the user has
      System.out.println("Enter a number for a game:"); // ask the user to enter a number for a game
      System.out.println("1. Snake");
      System.out.println("2. Jeopardy");
      System.out.println("3. BattleShip");
      System.out.println("4. Tic Tac Toe");
      System.out.println("5. Exit");

      String selection = key.next(); // get user input for the game they want to play

      if (devMode == true){
        picker(selection); // if the devMode is true, call the picker method without catching any errors
      } 
      else if (devMode == false){
        try {
          picker(selection); // if the devMode is false, call the picker method and catch any errors
        } catch (Exception e) {  // if there is an error, tell the user, print the error message, and return back the the main menu.
          System.out.println(
            "It appears there was an error. Usually this is caused by an invalid input. Please try again.");
          System.out.println("The error was: ");
          System.out.println(e);
          Thread.sleep(1000);
          System.out.println("Enter any key to return back to the menu:");
          key.next();
        }
      }
    }
  }
}
