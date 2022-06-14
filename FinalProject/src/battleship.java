import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class battleship {

  // program to play the game battleship
  static Scanner key = new Scanner(System.in); //create new scanner
  static final char BACKGROUND = '〜'; //set background character
  static final int SIZE = 20; // Size of the board
  static char[][] board = new char[SIZE][SIZE]; //create game board
  static Random rand = new Random();

  static String[] directions = {"up", "right"};

  //generate a list of ships
  static String[][] random = {
    {"5", directions[rand.nextInt(2)], "Aircraft Carrier"},
    {"4", directions[rand.nextInt(2)], "Battleship"},
    {"3", directions[rand.nextInt(2)], "Submarine"},
    {"3", directions[rand.nextInt(2)], "Patrol Boat"},
    {"2", directions[rand.nextInt(2)], "Destroyer"}
  };

  //new 3dimensional arraylist
  static ArrayList<ArrayList<ArrayList<Integer>>> ships = new ArrayList<ArrayList<ArrayList<Integer>>>();

  public static void generateRandomShips(String direction, int length) {
    //generate random starting point for ship placement on board away from edges
    int x = rand.nextInt(SIZE - (2 * length)) + length;
    int y = rand.nextInt(SIZE - (2 * length)) + length;

    ships.add(new ArrayList<ArrayList<Integer>>());
    for (int i = 0; i <= length; i++) {
      if (direction == "up") {
        ships.get(ships.size() - 1).add(new ArrayList<Integer>(Arrays.asList(x, y + i)));
      } else {
        ships.get(ships.size() - 1).add(new ArrayList<Integer>(Arrays.asList(x + i, y)));
      }
    }
    for (int q = 0; q < ships.size(); q++) {
      System.out.println(ships.get(q));
    }
  }
  

  static char[][] answers = new char[SIZE][SIZE]; //create answer board
  static String msg = ""; //create message

  public static void printout() { //print out the board
    System.out.print("  ");

    for (int j = 1; j <= board[0].length; j++) {
      if (j < 10) {
        System.out.print(" " + j + " ");
      } else {
        System.out.print(" " + j);
      }
    }
    System.out.println();
    for (int i = 1; i <= board.length; i++) {
      if (i < 10) {
        System.out.print(" " + i + " ");
      } else {
        System.out.print(i + " ");
      }

      for (int j = 0; j < board[i - 1].length; j++) {
        System.out.print(board[i - 1][j] + " ");
      }
      System.out.println();
    }
  }

  public static void game() throws InterruptedException {
    for (int i = 0; i < SIZE; i++) { //fill board with dots
      for (int j = 0; j < SIZE; j++) {
        board[i][j] = BACKGROUND;
        answers[i][j] = BACKGROUND;
      }
    }
    for (int i = 0; i < ships.size(); i++) {
      for (int j = 0; j < ships.get(i).size(); j++) {
        answers[ships.get(i).get(j).get(0)][ships.get(i).get(j).get(0)] = 'S';
      }
    }
    boolean run = true;
    printout();
    while (run) {
      System.out.println("Enter an x-coordinate to fire at (1-20): ");
      String input = key.next();
      int x = Integer.parseInt(input);
      System.out.println("Enter a y-coordinate to fire at (1-20): ");
      input = key.next();
      int y = Integer.parseInt(input);
      if (answers[y][x] == BACKGROUND) {
        board[y][x] = 'X';
        msg = "You missed!";
      } else if (answers[y][x] == 'S') {
        answers[y][x] = 'H';
        msg = "You hit a ship!";
      } else if (answers[y][x] == 'H') {
        msg = "You already fired at this location!";
      }
      Thread.sleep(1000);
      System.out.print("\033[H\033[2J"); //flush screen
      System.out.flush();
      printout();
    }
  }

  public static void menu() throws InterruptedException {
    boolean run = true;
    generateRandomShips("up", 5);
    while (run) {
      System.out.print("\033[H\033[2J"); //flush the screen
      System.out.flush();
      System.out.println("Welcome to Battleship!"); //print welcome message

      Thread.sleep(1000); //wait to build suspense
      System.out.println("Enter a number for your selection:"); //print out menu
      System.out.println("1. Battleship Game");
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
          System.out.println("How to play: ");
          System.out.println("Use w, a, s, and d keys to move the snake.");
          System.out.println("Eat the 0 to grow.");
          System.out.println("Avoid the walls and yourself.");
          System.out.println("Press 'p' to pause the game.");
          System.out.println("Press 'q' to quit the game.");
          Thread.sleep(1000);
          System.out.println("Press any key to return back to the menu:");
          key.next();
          break;
        default:
          System.out.print("\033[H\033[2J"); //flush the screen
          System.out.flush();
          System.out.println("Invalid input, please try again."); // if they did not enter something correct, tell them
          Thread.sleep(2000);
      }
    }
  }
}
