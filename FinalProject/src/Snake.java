
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;


class snake {

  static Scanner key = new Scanner(System.in); 
  
  public static void game(String[] args) {
    final char BACKROUND = '.';
    final int SIZE = 20;
    final int[] STARTING_POS = {SIZE/2, SIZE/2};
    ArrayList<ArrayList<Integer>> blocks = new ArrayList<ArrayList<Integer>>();
    blocks.add(new ArrayList<Integer>(Arrays.asList(STARTING_POS[0], STARTING_POS[1] - 1))); //create starting snake
    blocks.add(new ArrayList<Integer>(Arrays.asList(STARTING_POS[0], STARTING_POS[1]))); //create starting snake 

    Random rand = new Random();
    ArrayList<Integer> Target = new ArrayList<Integer>(Arrays.asList(rand.nextInt(SIZE), rand.nextInt(SIZE))); //create random target

    char[][] board = new char[SIZE][SIZE * 2]; //create game board
    for (int i = 0; i < SIZE; i++) { //fill board with dots
      for (int j = 0; j < SIZE * 2; j++) { 
        board[i][j] = BACKROUND;
      }
    }

    char direction = 'w';
    
    System.out.println("Welcome to the game! Press any key to begin.");

    int x = STARTING_POS[0];
    int y = STARTING_POS[1];
    boolean triggered = true;
    String character = new String();
    boolean firstTime = true;
    
    while (true) { 
      if (firstTime == true) {
        character = "d";
        firstTime = false;
      } else {
        character = key.next();
      }
      System.out.println(direction);
      if (character.equals("w") && direction != 's') {
        direction = 'w';
        y--;
      } else if (character.equals("a") && direction != 'd') {
        direction = 'a';
        x--;
      } else if (character.equals("s") && direction != 'w') {
        direction = 's';
        y++;
      } else if (character.equals("d") && direction != 'a') {
        direction = 'd';
        x++;
      } else if (character.equals("q")) {
        System.out.println("Game Over");
        System.exit(0);
      }else if (character.equals("p")) {
        System.out.println("Game Paused");
        while (true) {
          character = key.next();
          if (character.equals("p")) {
            break;
          }
        }
      } else{
        System.out.println("Invalid input");
        triggered = false; 
      }

      System.out.print("\033[H\033[2J");  
      System.out.flush(); 

      if (triggered) {
        for (int i = 0; i < SIZE; i++) {
          for (int j = 0; j < SIZE * 2; j++) {
            board[i][j] = BACKROUND;
          }
        }
        
        ArrayList<Integer> coords = new ArrayList<Integer>();
        coords.add(y);
        coords.add(x);

        if (blocks.contains(coords)) {
          System.out.println("Game Over");
          System.exit(0);
        }

        blocks.add(coords);

        if (blocks.contains(Target)) {
          Target = new ArrayList<Integer>(Arrays.asList(rand.nextInt(SIZE), rand.nextInt(SIZE)));
        } else {
          blocks.remove(0);
        }
      }
      triggered = true;

      for (int i = 0; i < blocks.size(); i++) {
        board [blocks.get(i).get(0)][blocks.get(i).get(1)] = '*';
      }
      board [Target.get(0)][Target.get(1)] = '0';
      
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE * 2; j++) {
          if (board[i][j] == '*'){
            System.out.print( "\033[0;32m" + board[i][j] + "\033[0m");
          }
          else if (board[i][j] == '0'){
            System.out.print("\033[0;31m" + board[i][j] + "\033[0m");
          }
          else {
            System.out.print(board[i][j]);
          }
        }
        System.out.println();
      }
    }    
  }

  public static void menu(String[] args) throws InterruptedException {
    while (true) {
      System.out.print("\033[H\033[2J");  
      System.out.flush(); 
      System.out.println("Welcome to Snake!");

      Thread.sleep(1000);
      System.out.println("Enter a number for your selection:");
      System.out.println("1. Snake Game");
      System.out.println("2. Exit to Main Menu");
      System.out.println("3. How to play");
      System.out.println("4. Shop");

      String selection = key.next();

      switch (selection){
        case "1":
          game(args);
          break;

        case "2":
          System.out.println("Goodbye!");
          System.exit(0);
          break;        
        case "3":
          System.out.println("How to play: ");
          System.out.println("Use w, a, s, and d keys to move the snake.");
          System.out.println("Eat the 0 to grow.");
          System.out.println("Avoid the walls and yourself.");
          System.out.println("Press 'p' to pause the game.");
          System.out.println("Press 'q' to quit the game.");
          break;
        default:
          System.out.println("Invalid input, please try again.");
      }
  }
  }
}