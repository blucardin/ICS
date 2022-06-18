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

  public static void generateRandomShips (String direction, int length) throws InterruptedException{
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
        if (board[i - 1][j] == BACKGROUND) {
          System.out.print(board[i - 1][j] + " ");
        }
        else {
          System.out.print(board[i - 1][j] + "  ");
        }
      }
      System.out.println();
    }
    System.out.println(msg);

    for (int i = 1; i <= answers.length; i++) {
      if (i < 10) {
        System.out.print(" " + i + " ");
      } else {
        System.out.print(i + " ");
      }

      for (int j = 0; j < answers[i - 1].length; j++) {
        if (answers[i - 1][j] == BACKGROUND) {
          System.out.print(answers[i - 1][j] + " ");
        }
        else {
          System.out.print(answers[i - 1][j] + "  ");
        }
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
        answers[ships.get(i).get(j).get(0)][ships.get(i).get(j).get(1)] = 'S';
      }
    }
    boolean run = true;
    printout();
    while (run) {
      while (true) {
        System.out.println("Enter an x-coordinate to fire at (1-20): ");
        int x = Integer.parseInt(key.next()) - 1;
        System.out.println("Enter a y-coordinate to fire at (1-20): ");
        int y = Integer.parseInt(key.next()) - 1;
        msg = "";
        if (x == -1 || y == -1) {
          run = false; // if user enters 0, end game
          break;
        } else if (x > SIZE - 1 || y > SIZE - 1 || x < 0 || y < 0) {
          msg = "Invalid coordinates";
        } else if (answers[y][x] == BACKGROUND) {
          board[y][x] = 'X';
          msg = "You missed!";
          break;
        } else if (answers[y][x] == 'S') {
          answers[y][x] = 'H';
          board[y][x] = 'H';
          msg = "You hit a ship!";
          break;
        } else if (answers[y][x] == 'H') {
          msg = "You already fired at this location!";
          break;
        }
      }
      System.out.print("\033[H\033[2J"); //flush screen
      System.out.flush();
      printout();
    }
  }

  public static void menu() throws InterruptedException {
    boolean run = true;
    for (int r = 0; r < random.length; r++) {
     generateRandomShips(random[r][1], Integer.parseInt(random[r][0])); 
    }
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
