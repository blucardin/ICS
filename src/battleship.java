/* This is the file that runs the battleship game.
 * It displays a menu, and gets user input.
 * Based on what the user wants to do, it will either start the battleship game, go to the shop, print the rules, or go back the main menu.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class battleship { 

  static String[] colors = { color.BLUE, color.RESET, color.RED, color.GREEN };
  // colors 0 represents menu color, 1 represents background color, 2 represents miss color, 3 represents hit color


  public static void shop() throws InterruptedException { //shop menu
    while (true) {
      System.out.print("\033[H\033[2J"); //clear screen
      System.out.flush();

      System.out.println(colors[0]);
      System.out.println("Welcome to the shop!");
      System.out.println("You have " + app.coins + " coins.");
      System.out.println("What object would you like to buy a color for?"); //print item menu and prompt user to choose an option
      System.out.println("1. Menu");
      System.out.println("2. Background");
      System.out.println("3. Miss");
      System.out.println("4. Hit");
      System.out.println("5. Exit back to menu");
      System.out.println("Enter your choice: ");
      String choice = key.next(); //get user choice
      if (choice.equals("5")) { //if user chooses 5, return to menu
        break;
      }

      Thread.sleep(1000);
      System.out.print("\033[H\033[2J"); //clear screen
      System.out.flush();

      System.out.println(colors[0]);
      System.out.println("What color would you like to buy?"); //print item menu and prompt user to choose an option
      System.out.println("1. Green (10 coins)");
      System.out.println("2. Red (20 coins)");
      System.out.println("3. Blue (30 coins)");
      System.out.println("4. Yellow (40 coins)");
      System.out.println("5. Exit back to menu");
      System.out.println("\nYou have " + app.coins + " coins.\n");
      System.out.println("Enter your choice: ");
      String colorChoice = key.next(); //get user choice
      if (colorChoice.equals("5")) { //if user chooses 5, return to menu
        break;
      }

      Thread.sleep(1000);
      System.out.print("\033[H\033[2J"); //clear screen
      System.out.flush();

      int object = Integer.parseInt(choice) - 1; //create selector for the object the user wants to change

      boolean approved = false; //create boolean to check if the user has enough coins

      switch (colorChoice) { //set color based on user choice
        case "1":
          if (app.coins >= 10) { //if user has enough coins, set color and subtract coins
            app.coins -= 10; //subtract coins
            colors[object] = color.GREEN; //set color
            approved = true; //set approved to true
          }
          break;
        case "2":
          if (app.coins >= 20) { //see above
            app.coins -= 20;
            colors[object] = color.RED;
            approved = true;
          }
          break;
        case "3":
          if (app.coins >= 30) { //see above
            app.coins -= 30;
            colors[object] = color.BLUE;
            approved = true;
          }
          break;
        case "4":
          if (app.coins >= 40) { //see above
            app.coins -= 40;
            colors[object] = color.YELLOW;
            approved = true;
          }
        default:
          System.out.println("Invalid choice, try again."); //if user chooses an invalid choice, print error message
          approved = false; //set approved to false
          break;
      }
      if (approved == true) {
        System.out.println(
          "Your purchase was approved! Your color is now equipped!"
        ); //if user has enough coins, print success message
        System.out.println("You have " + app.coins + " coins.");
      } else {
        System.out.println("You don't have enough coins!"); //if user doesn't have enough coins, print error message
      }
      System.out.println("Press enter to continue..."); //prompt user to continue
      key.nextLine();
      key.nextLine();
    }
  }

  // program to play the game battleship
  static Scanner key = new Scanner(System.in); //create new scanner
  static final char BACKGROUND = '〜'; //set background character
  static final int SIZE = 11; // Size of the board
  static char[][] CPUboard = new char[SIZE][SIZE]; //create game board
  static char[][] Playerboard = new char[SIZE][SIZE]; //create game board
  static Random rand = new Random();

  static String[] directions = { "up", "right" }; //create array of directions

  //generate a list of ships
  static String[][] random = { 
    { "5", directions[rand.nextInt(2)], "Aircraft Carrier" },  //create array of ships
    { "4", directions[rand.nextInt(2)], "Battleship" },
    { "3", directions[rand.nextInt(2)], "Submarine" },
    { "3", directions[rand.nextInt(2)], "Patrol Boat" },
    { "2", directions[rand.nextInt(2)], "Destroyer" },
  };

  //new 3dimensional arraylist
  static ArrayList<ArrayList<ArrayList<Integer>>> CPUships = new ArrayList<ArrayList<ArrayList<Integer>>>(); //create an ArrayLists for the CPU's ships
  static ArrayList<ArrayList<ArrayList<Integer>>> PlayerShips = new ArrayList<ArrayList<ArrayList<Integer>>>(); //create an ArrayLists for the player's ships

  public static void generateRandomShips(String direction, int length) throws InterruptedException { //generate random ships
    
    int x1 = rand.nextInt(SIZE - (2 * length)) + length; //generate random starting point for ship placement on board away from edges
    int y1 = rand.nextInt(SIZE - (2 * length)) + length;

    int x2 = rand.nextInt(SIZE - (2 * length)) + length; //see above
    int y2 = rand.nextInt(SIZE - (2 * length)) + length;

    PlayerShips.add(new ArrayList<ArrayList<Integer>>()); //add new coordinate array to both ArrayLists
    CPUships.add(new ArrayList<ArrayList<Integer>>());

    for (int i = 0; i <= length; i++) {
      if (direction == "up") {
        PlayerShips
          .get(PlayerShips.size() - 1)
          .add(new ArrayList<Integer>(Arrays.asList(x2, y2 + i))); //add new x and y to the coordinate array in the ArrayLists
        CPUships
          .get(CPUships.size() - 1)
          .add(new ArrayList<Integer>(Arrays.asList(x1, y1 + i))); //see above
      } else {
        PlayerShips
          .get(PlayerShips.size() - 1)
          .add(new ArrayList<Integer>(Arrays.asList(x2 + i, y2))); //see above

        CPUships
          .get(CPUships.size() - 1)
          .add(new ArrayList<Integer>(Arrays.asList(x1 + i, y1))); //see above
      }
    }
  }

  static char[][] CPUanswers = new char[SIZE][SIZE]; //create answer board
  static char[][] PlayerAnswers = new char[SIZE][SIZE]; //create answer board

  public static void printout(char[][] array) { //print out the board
    System.out.print("  " +  colors[1]);

    for (int j = 1; j <= array[0].length; j++) { //print out the column numbers
      if (j < 10) {
        System.out.print(" " + j + " ");
      } else {
        System.out.print(" " + j);
      }
    }
    System.out.println();
    for (int i = 1; i <= array.length; i++) {
      if (i < 10) {
        System.out.print(" " + colors[1] + i + " "); //print out the row numbers
      } else {
        System.out.print(colors[1] + i + " ");
      }

      for (int j = 0; j < array[i - 1].length; j++) { //print out the board
        if (array[i - 1][j] == BACKGROUND) {
          System.out.print(colors[1] + array[i - 1][j] + " "); // if the character is a background character, print it with the background color
        } else if (array[i - 1][j] == 'X') {
          System.out.print(colors[2] + array[i - 1][j] + "  "); // if the character is an X, print it with the miss color
        } else if (array[i - 1][j] == 'H' || array[i - 1][j] == 'S') { 
          System.out.print(colors[3] + array[i - 1][j] + "  "); // if the character is a H or a S, print it with the hit color
        }
      }
      System.out.println();
    }
    System.out.print(colors[0]);
  }

  public static void game() throws InterruptedException {
    for (int r = 0; r < random.length; r++) {
      generateRandomShips(random[r][1], Integer.parseInt(random[r][0]));
    }

    System.out.print("\033[H\033[2J"); //clear screen
    System.out.flush();

    for (int i = 0; i < SIZE; i++) { //fill boards with the backgnound character
      for (int j = 0; j < SIZE; j++) {
        CPUboard[i][j] = BACKGROUND;
        CPUanswers[i][j] = BACKGROUND;
        Playerboard[i][j] = BACKGROUND;
        PlayerAnswers[i][j] = BACKGROUND;
      }
    }
    
    for (int i = 0; i < PlayerShips.size(); i++) {
      for (int j = 0; j < PlayerShips.get(i).size(); j++) {
        PlayerAnswers[PlayerShips.get(i).get(j).get(0)][PlayerShips.get(i).get(j).get(1)] = 'S'; // fill in the answer board with PlayerShips
      }
    }

    for (int i = 0; i < CPUships.size(); i++) {
      for (int j = 0; j < CPUships.get(i).size(); j++) {
        CPUanswers[CPUships.get(i).get(j).get(0)][CPUships.get(i).get(j).get(1)] = 'S'; // fill in the answer board with CPUships
      }
    }

    System.out.println("Your ships have been randomly placed on the board in the following layout: \n"); 
    printout(PlayerAnswers); //print out the PlayerAnswer board
    System.out.println("Get ready for war! Enter any key to continue.");
    key.next();

    String msg = "";

    char turn = 'P'; //create a variable representing whose turn it is
    boolean run = true; 
    while (run) {
      if (turn == 'P') { //if it is the player's turn
        while (true) {
          System.out.print("\033[H\033[2J"); //clear screen
          System.out.flush();
          System.out.println("Players's turn");

          printout(CPUboard);  //print out the CPU board

          System.out.println("Enter an x-coordinate to fire at (1-20) (enter 0 to end game): ");
          int x = Integer.parseInt(key.next()) - 1; //get the x-coordinate
          System.out.println("Enter a y-coordinate to fire at (1-20) (enter 0 to end game): ");
          int y = Integer.parseInt(key.next()) - 1; //get the y-coordinate
          if (x == -1 || y == -1) { //if the user enters 0, end the game
            run = false; // if user enters 0, end game
            break;
          } else if (x > SIZE - 1 || y > SIZE - 1 || x < 0 || y < 0) { //if the user enters a coordinate outside of the board, print an error message
            System.out.println( "Invalid coordinates");
          } else if (CPUanswers[y][x] == BACKGROUND) { //if the user enters a coordinate that is a miss, tell them and update the board
            CPUboard[y][x] = 'X';
            CPUanswers[y][x] = 'X';
            msg = "You missed!";
            break;
          } else if (CPUanswers[y][x] == 'S') { //if the user enters a coordinate that is a hit, tell them and update the board
            CPUanswers[y][x] = 'H';
            CPUboard[y][x] = 'H';
            msg = "You hit a ship!";
            break;
          } else if (CPUanswers[y][x] == 'H' || CPUanswers[y][x] == 'X') { //if the user enters a coordinate that is already a hit or a miss, print an error message
            System.out.println( "You already fired at this location!");
            System.out.println( "Enter any key to continue.");
            key.next();
          }
        }
        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();
        System.out.println("Players's turn");
        printout(CPUboard); //print out the updated CPU board
        System.out.println(msg); //print out the message
        System.out.println( "Enter any key to continue.");
        key.next();

        boolean allSunk = true; //check if all boats were sunk
        for (int i = 0; i < CPUanswers.length; i++) {
          for (int j = 0; j < CPUanswers[i].length; j++) { 
            if (CPUanswers[i][j] == 'S') { //if any of the CPU's ships are not sunk, set allSunk to false
              allSunk = false;
            }
          }
        }
        if (allSunk) {
          System.out.println("You won!"); //if all the CPU's ships are sunk, print out the message
          run = false;
          break;
        }
        turn = 'C'; //change the turn to CPU
        
      } else if (turn == 'C') {
        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();
        System.out.println("CPU's turn");

        printout(Playerboard); //print out the Player board
        System.out.println("The CPU is gussing...");
        Thread.sleep(1000);

        while (true) {  //while the CPU is gussing, keep looping
          int x = (int) (Math.random() * SIZE);  //randomly generate an x and y coordinate to fire at
          int y = (int) (Math.random() * SIZE); 
          if (PlayerAnswers[y][x] == BACKGROUND) { //if the CPU fires and misses, update the board
            Playerboard[y][x] = 'X'; //update the display board
            msg = "The CPU Missed!";
            break;
          } else if (PlayerAnswers[y][x] == 'S') { //if the CPU fires snd hits, update the board
            PlayerAnswers[y][x] = 'H'; //update the answer board
            Playerboard[y][x] = 'H'; //update the display board
            msg = "The CPU Hit!"; //print out the message
            break;
          } 
        }
        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();
        System.out.println("CPU's turn");
        printout(Playerboard);
        System.out.println(msg);

        boolean allSunk = true; //check if all boats were sunk
        for (int i = 0; i < PlayerAnswers.length; i++) {
          for (int j = 0; j < PlayerAnswers[i].length; j++) {  
            if (PlayerAnswers[i][j] == 'S') { //if any of the Player's ships are not sunk, set allSunk to false
              allSunk = false;
            }
          }
        }
        if (allSunk) {
          System.out.println("The CPU won!"); //if all the Players's ships are sunk, print out the message
          run = false;
          break;
        }
        System.out.println( "Enter any key to continue."); //print out the message
        key.next();
        turn = 'P';
        
      }
    }
   
    int hits = 0;
    for (int i = 0; i < CPUanswers.length; i++) {
      for (int j = 0; j < CPUanswers[i].length; j++) {  // determine the amount of hits
        if (CPUanswers[i][j] == 'H') {
          hits++;
        }
      }
    }
    int CoinsIncrease = hits * 20;
    app.coins += CoinsIncrease;
    //print out the amount of hits
    System.out.println("You got " + hits + " hits!");
    System.out.println("You earned " + CoinsIncrease + " coins!");
    System.out.println("You now have " + (app.coins) + " coins!");

    //play again
    System.out.println("Would you like to play again? (y/n)"); //ask the user if they want to play again
    String answer = key.next();
    if (answer.equals("y")) {
      game();
    }

  }

  public static void menu() throws InterruptedException {
    boolean run = true;
    while (run) {
      System.out.print("\033[H\033[2J"); //flush the screen
      System.out.flush();
      System.out.print(colors[0]);
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
        case "4":
          shop();
        default:
          System.out.print("\033[H\033[2J"); //flush the screen
          System.out.flush();
          System.out.println("Invalid input, please try again."); // if they did not enter something correct, tell them
          Thread.sleep(2000);
      }
    }
  }
}
