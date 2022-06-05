import java.util.ArrayList; // Import libraries
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class snake {

  static Scanner key = new Scanner(System.in); //create new scanner
  // hashmap syntax from https://www.w3schools.com/java/java_hashmap.asp 

  //create  array for colors
  static String[] colors = {color.RESET, color.RESET, color.RED, color.GREEN};
  // colors 0 represents menu color, 1 represents background color, 2 represents target color, 3 represents snake color


  public static void shop() throws InterruptedException{ //shop menu
    while (true) {
      System.out.print("\033[H\033[2J"); //clear screen
      System.out.flush();

      System.out.println(colors[0]);
      System.out.println("Welcome to the shop!");
      System.out.println("What object would you like to buy a color for?");
      System.out.println("1. Menu");
      System.out.println("2. Background");
      System.out.println("3. Target");
      System.out.println("4. Snake");
      System.out.println("5. Exit back to main menu");
      System.out.println("Enter your choice: ");
      String choice = key.next(); //get user choice
      if (choice.equals("5")){
        break;
      }

      Thread.sleep(1000);
      System.out.print("\033[H\033[2J"); //clear screen
      System.out.flush();

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
      Thread.sleep(1000);
    }
  }

  public static void game() throws InterruptedException {
    boolean run = true;
    while (run) {
      final char BACKGROUND = '.'; //set background character
      final int SIZE = 20; // Size of the board
      final int[] STARTING_POS = { SIZE / 2, SIZE / 2 }; //Starting position for the snake
      ArrayList<ArrayList<Integer>> blocks = new ArrayList<ArrayList<Integer>>();
      blocks.add(
        new ArrayList<Integer>(
          Arrays.asList(STARTING_POS[0], STARTING_POS[1] - 1)
        )
      ); //create starting snake
      blocks.add(
        new ArrayList<Integer>(Arrays.asList(STARTING_POS[0], STARTING_POS[1]))
      ); //create starting snake

      Random rand = new Random();
      ArrayList<Integer> Target = new ArrayList<Integer>(
        Arrays.asList(rand.nextInt(SIZE), rand.nextInt(SIZE))
      ); //create random target

      char[][] board = new char[SIZE][SIZE * 2]; //create game board
      for (int i = 0; i < SIZE; i++) { //fill board with dots
        for (int j = 0; j < SIZE * 2; j++) {
          board[i][j] = BACKGROUND;      
        }
      }

      char direction = 'w'; //set default direction to up

      System.out.println("Welcome to the game! Press any key to begin.");

      int x = STARTING_POS[0];
      int y = STARTING_POS[1]; //set starting position
      boolean triggered = true;
      String character = new String();
      boolean firstTime = true;
      String reason = ""; //create reason for death

      while (true) {
        if (firstTime == true) { // if it is the users first time playing, just print the board, don't accept user input
          character = "d";
          firstTime = false;
        } else {
          character = key.nextLine();
        }
        do {
          if (character.equals("w") && direction != 's') { // check the inputted character against the current direction and move the snake accordingly
            direction = 'w';
            y--;
            triggered = true;
          } else if (character.equals("a") && direction != 'd') {
            direction = 'a';
            x--;
            triggered = true;
          } else if (character.equals("s") && direction != 'w') {
            direction = 's';
            y++;
            triggered = true;
          } else if (character.equals("d") && direction != 'a') {
            direction = 'd';
            x++;
            triggered = true;

          } else if (character.equals("q")) { // if the user inputs q, quit the game
            reason = "You quit the game.";
            break;
          } else if (character.equals("p")) { // if the user inputs p, pause the game
            System.out.println("Game Paused");
            System.out.println("Press any key to continue.");
            key.nextLine();
          } else {
            character = "" + direction;
            triggered = false;
          }
        } while (!triggered);

        if (triggered) {
          for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE * 2; j++) {
              board[i][j] = BACKGROUND;
            }
          }

          System.out.print("\033[H\033[2J");
          System.out.flush();

          ArrayList<Integer> coords = new ArrayList<Integer>();
          coords.add(y);
          coords.add(x);

          if (blocks.contains(coords)) {
            reason = "You hit yourself.";
            break;
          }

          //check if the snake has went out of bounds
          if (x < 0 || x >= board[0].length || y < 0 || y >= board.length) {
            reason = "You went out of bounds.";
            break;
          }

          blocks.add(coords);

          if (blocks.contains(Target)) {
            Target =
              new ArrayList<Integer>(
                Arrays.asList(rand.nextInt(SIZE), rand.nextInt(SIZE))
              );
          } else {
            blocks.remove(0);
          }
        }
        triggered = true;

        for (int i = 0; i < blocks.size(); i++) {
          board[blocks.get(i).get(0)][blocks.get(i).get(1)] = '*';
        }
        board[Target.get(0)][Target.get(1)] = '0';

        System.out.println(colors[1]);

        for (int i = 0; i < SIZE; i++) {
          for (int j = 0; j < SIZE * 2; j++) {
          if (board[i][j] == '*') {
            System.out.print(colors[3] + board[i][j] + colors[1]);
          } else if (board[i][j] == '0') {
            System.out.print(colors[2] + board[i][j] + colors[1]);
          } else {
            System.out.print(board[i][j]);
          }
        }
        System.out.println();
      }
    }

      System.out.print("\033[H\033[2J"); //clear the screen
      System.out.flush();
      System.out.println(color.RESET);
      System.out.println("Game Over");
      System.out.println(reason);
      System.out.println();
      Thread.sleep(1000);

      System.out.println("Your score was: " + blocks.size());
      int coinsEarned = (blocks.size() - 2) * 10;
      app.coins += coinsEarned;
      System.out.println("You earned " + coinsEarned  + " coins!");
      System.out.println("You now have " + app.coins + " coins!");
      System.out.println();

      Thread.sleep(1000);
      System.out.println("Would you like to play again? (y/n)");
      String playAgain = key.next();
      if (playAgain.equals("n")) {
        run = false;
      }
    }
  }

  public static void menu(String[] args) throws InterruptedException {
    boolean menu = true;
    while (menu) {
      //loop over array
      for (int i = 0; i < animation.length; i++) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Welcome to Snake!");

        System.out.println(
          "	........................................		" +
          "\n" +
          "	........................................		" +
          "\n" +
          animation[i] +
          "	........................................		" +
          "\n" +
          "	........................................		" +
          "\n" +
          "	........................................		" +
          "\n" +
          "	........................................		" +
          "\n" +
          "	........................................		" +
          "\n" +
          "	........................................		" +
          "\n" +
          "	........................................		" +
          "\n"
        );
        Thread.sleep(125);
      }
      System.out.print(colors[0]);
      System.out.println("Enter a number for your selection:");
      System.out.println("1. Snake Game");
      System.out.println("2. Exit to Main Menu");
      System.out.println("3. How to play");
      System.out.println("4. Shop");
      System.out.println(color.RESET);

      String selection = key.next();

      switch (selection) {
        case "1":
          game();
          break;
        case "2":
          System.out.print("\033[H\033[2J");
          System.out.flush();
          System.out.println("Goodbye!");
          menu = false;
          break;
        case "3":
          System.out.print("\033[H\033[2J");
          System.out.flush();
          System.out.println("How to play: ");
          System.out.println("Use w, a, s, and d keys to move the snake.");
          System.out.println("Eat the 0 to grow.");
          System.out.println("Avoid the walls and yourself.");
          System.out.println("Press 'p' to pause the game.");
          System.out.println("Press 'q' to quit the game.");
          Thread.sleep(1000);
          System.out.println("Press, any key to go back to the menu.");
          key.next();
          System.out.print("\033[H\033[2J");
          System.out.flush();
          break;
        case "4":
          shop();
          break;
        default:
          System.out.println("Invalid input, please try again.");
          System.out.print("\033[H\033[2J");
          System.out.flush();
      }
    }
  }

  public static String[] animation = {
    "	....***.................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	.....***................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	......***...............................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	.......***..............................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	........***.............................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	.........***............................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	..........***...........................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	...........***..........................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	............***.........................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	.............***........................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	..............***.......................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	...............**.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	........................................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................0.......................		" +
    "\n",
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	........................................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	................*.......................		" +
    "\n" +
    "	........................................		" +
    "\n",
  };
}
