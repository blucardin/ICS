import java.util.Random;
import java.util.Scanner;

public class tictactoe {
   //create  array for colors
  static String[] colors = {color.RESET, color.RESET, color.RED, color.GREEN};
  // colors 0 represents menu color, 1 represents background color, 2 represents X color, 3 represents O color


  public static void shop() throws InterruptedException{ //shop menu
    while (true) {
      System.out.print("\033[H\033[2J"); //clear screen
      System.out.flush();

      System.out.println(colors[0]);
      System.out.println("Welcome to the shop!");
      System.out.println("You have " + app.coins + " coins.");
      System.out.println("What object would you like to buy a color for?");
      System.out.println("1. Menu");
      System.out.println("2. Background");
      System.out.println("3. O");
      System.out.println("4. X");
      System.out.println("5. Exit back to main menu");
      System.out.println("Enter your choice: ");
      String choice = key.next(); //get user choice
      if (choice.equals("5")){
        break;
      }

      Thread.sleep(1000);
      System.out.print("\033[H\033[2J"); //clear screen
      System.out.flush();
      
      System.out.println(colors[0]);
      System.out.println("What color would you like to buy?");
      System.out.println("1. Green (10 coins)");
      System.out.println("2. Red (20 coins)");
      System.out.println("3. Blue (30 coins)");
      System.out.println("4. Yellow (40 coins)");
      System.out.println("5. Exit back to main menu");
      System.out.println("Enter your choice: ");
      String colorChoice = key.next();
      if (colorChoice.equals("5")){
        break;
      }

      Thread.sleep(1000);
      System.out.print("\033[H\033[2J"); //clear screen
      System.out.flush();

      int object = Integer.parseInt(choice) - 1; //create selector for the object the user wants to change

      boolean approved = false; //create boolean to check if the user has enough coins

      switch(colorChoice) { //set color
        case "1":
          if (app.coins >= 10) {
            app.coins -= 10; //subtract coins
            colors[object] = color.GREEN;
            approved = true;
          }
          break;
        case "2":
          if (app.coins >= 20) {
            app.coins -= 20;
            colors[object] = color.RED;
            approved = true;
          }
          break;
        case "3":
          if (app.coins >= 30) {
            app.coins -= 30;
            colors[object] = color.BLUE;
            approved = true;
          }
          break;
        case "4":
          if (app.coins >= 40) {
            app.coins -= 40;
            colors[object] = color.YELLOW;
            approved = true;
          }
        default:
          System.out.println("Invalid choice!");
          approved = true;
          break;
      }
      if (approved == true) {
        System.out.println("Your purchase was approved! Your color is now equipped!");
        System.out.println("You have " + app.coins + " coins.");
      } else {
        System.out.println("You don't have enough coins!");
      }
      System.out.println("Press enter to continue...");
      key.nextLine();
      key.nextLine();
    }
  }


  //program to play tic tac toe
  static Scanner key = new Scanner(System.in); //create new scanner
  static Random rand = new Random();

  static char[][] board = new char[3][3]; // create the board
  static final char BACKGROUND = ' '; //set background character
  static final int SIZE = 3; // Size of the board
  static final char X = 'X'; //set X character
  static final char O = 'O'; //set O character

  public static void printout() { //print out the board
    System.out.println(colors[1]);
    for (int i = 0; i < board.length; i++) {
      System.out.println("-------------"); // print the horizontal lines
      System.out.print("| "); // print the first vertical line of each row
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == X) {
          System.out.print(colors[2] + board[i][j] + colors[1] + " | "); //print the board row by row
        } 
        else if (board[i][j] == O) {
          System.out.print(colors[3] + board[i][j] + colors[1] + " | ");
        } else {
          System.out.print("  | ");
        }
      }
      System.out.println();
    }
    System.out.println("-------------"); // print the last horizontal line
  }

  public static boolean checkWin(char test) { // check if there is a win
    for (int i = 0; i < board.length; i++) { // check horizontal rows
      if (board[i][0] == test && board[i][1] == test && board[i][2] == test) {
        return true;
      }
    }

    for (int i = 0; i < board.length; i++) { //check vertical columns
      if (board[0][i] == test && board[1][i] == test && board[2][i] == test) {
        return true;
      }
    }

    if (board[0][0] == test && board[1][1] == test && board[2][2] == test) { //check 1st diagonal
      return true;
    } else if (
      board[0][2] == test && board[1][1] == test && board[2][0] == test
    ) { //check 2nd diagonal
      return true;
    }
    return false;
  }

  public static void game() throws InterruptedException {
    boolean run = true;
    while (run) { //if the user wants to play again the game will continue
      for (int i = 0; i < SIZE; i++) { //fill board with dots
        for (int j = 0; j < SIZE; j++) {
          board[i][j] = BACKGROUND;
        }
      }

      char turn = X;
      while (true) { // loop until someone wins or until the board is full
        System.out.print("\033[H\033[2J"); //flush screen
        System.out.flush();
        System.out.println(colors[0]);

        if (turn == X) { // if it is X's turn
          System.out.println("Player's turn");
          printout(); // print the board
          System.out.println("Enter a row to place your X (1-3):");
          int row = key.nextInt() - 1;
          System.out.println("Enter a column to place your X (1-3):"); // get the row and column from the user
          int col = key.nextInt() - 1;
          if (board[row][col] == BACKGROUND) { // if the space is empty
            board[row][col] = X; // place the X
            turn = O; // change the turn to O
          } else { // if the space is taken
            System.out.println("That spot is taken, try again"); // tell the user that the spot is taken
            Thread.sleep(1000); // wait 1 second, then go back to the start of the loop
          }

          if (checkWin(X)) { // if X wins
            System.out.print("\033[H\033[2J"); //flush screen
            System.out.flush();
            System.out.println(colors[0]);
            System.out.println("You win!"); // tell the user that X wins
            Thread.sleep(1000); // wait 1 second
            turn = X; // change the turn to X
            break; // break out of the loop
          }
        } else if (turn == O) { // if it is O's turn
          System.out.println("Computer's turn"); // tell the user that it is O's turn
          printout(); // print the board
          System.out.println("The computer is thinking...");
          Thread.sleep(1000);
          while (true) {
            int x = rand.nextInt(3); // get a random x between 0 and 2
            int y = rand.nextInt(3); // get a random y between 0 and 2
            if (board[x][y] == ' ') { // if the space is empty
              board[x][y] = O; // place the O
              turn = X;
              break;
            } // if the space is taken, go back to the start of the loop and try with another random number
          }

          if (checkWin(O)) { // if O wins
            System.out.println("The computer wins! Try again next time!"); // tell the user that O wins
            turn = O; // change the turn to O
            break; // break out of the loop
          }
        }

        boolean full = true; // check if the board is full
        for (int i = 0; i < SIZE; i++) {
          for (int j = 0; j < SIZE; j++) {
            if (board[i][j] == BACKGROUND) { // if at least one of the spots are the background, the board is not full
              full = false;
            }
          }
        }
        if (full) { // if the board is full, it is a tie
          System.out.println("It's a tie!"); // tell the user that it is a tie
          turn = 'T'; // change the turn to T, for tie
          break; // break out of the loop
        }
      }

      if (turn == 'T') { // if it is a tie
        System.out.println("You did not earn any coins!");
      } else if (turn == X) { // if X won
        System.out.println("You earned 50 coins!");
        app.coins += 50; // add 50 coins to the user's total
      } else if (turn == O) { // if O won
        System.out.println("You lost 50 coins!");
        app.coins -= 50; // subtract 50 coins from the user's total
      }

      System.out.println("You now have " + app.coins + " coins.");

      Thread.sleep(1000); // wait 1 second

      System.out.println("Play again? (y/n)");
      String answer = key.next();
      if (answer.equals("y")) {
        run = true;
      } else {
        run = false;
      }
    }
  }

  public static void menu() throws InterruptedException {
    boolean run = true;
    while (run) {
      System.out.print("\033[H\033[2J"); //flush the screen
      System.out.flush();
       System.out.println(colors[0]);
      System.out.println("Welcome to Tic Tac Toe!"); //print welcome message

      Thread.sleep(1000); //wait to build suspense
      System.out.println("Enter a number for your selection:"); //print out menu
      System.out.println("1. Tic Tac Toe Game");
      System.out.println("2. Exit to Main Menu");
      System.out.println("3. How to play");
      System.out.println("4. Shop");

      String selection = key.next(); //get user input

      switch (selection) {
        case "1":
          game(); // if they entered 1, start the game
          break;
        case "2":
          System.out.println("Goodbye!"); // if they entered 2, print goodbye, wait, and go back to main menu
          Thread.sleep(1000);
          run = false;
          break;
        case "3": // if they entered 3, print the rules, wait for input, then go to the beginning
          System.out.print("\033[H\033[2J"); //flush the screen
          System.out.flush();
           System.out.println(colors[0]); 
          System.out.println("How to play: ");
          System.out.println(
            "The goal of the game is to get three in a row. The first player to achieve this wins."
          );
          System.out.println(
            "The board is made up of 3 rows and 3 columns. The top left corner is 1,1 and the bottom right corner is 3,3."
          );
          System.out.println(
            "To place your X, enter the row and column you want your X to go. For example, if you want to place your X in the middle, enter row 2 and column 2."
          );
          System.out.println("The computer will place O. ");
          System.out.println(
            "If you have won, you will win 50 points. If you have lost, you will lose 50 points. If you have tied, your amount of points will not change."
          );
          System.out.println(
            "If you want to play again, enter 1. If you want to exit, enter 2."
          );

          Thread.sleep(1000);
          System.out.println("Press any key to return back to the menu:");
          key.next();
          break;
        case "4":
          shop();
          break;
        default:
          System.out.print("\033[H\033[2J"); //flush the screen
          System.out.flush();
          System.out.println(colors[0]);
          System.out.println("Invalid input, please try again."); // if they did not enter something correct, tell them
          Thread.sleep(2000);
      }
    }
  }
}
