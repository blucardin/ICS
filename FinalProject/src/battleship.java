import java.util.Scanner;
import java.util.Random;
public class battleship {
    // program to play the game battleship
    static Scanner key = new Scanner(System.in); //create new scanner
    static final char BACKROUND = '~'; //set backround character
    static final int SIZE = 20; // Size of the board
    static char[][] board = new char[SIZE][SIZE]; //create game board
    static Random rand = new Random();
    //create ship locations
    static int[][][] ships = {
        {{rand.nextInt(SIZE), rand.nextInt(SIZE)}},
        {{rand.nextInt(SIZE), rand.nextInt(SIZE)}}
    };
    static char[][] answers = new char[SIZE][SIZE]; //create answer board
    static String msg = ""; //create message
 


    public static void printout(String[] args) { //print out the board
        System.out.print("\033[H\033[2J"); //flush screen
        System.out.flush();
        
        for (int j = 0; j < board[0].length; j++) {
            System.out.print(j);
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(i);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(msg);
    }

    public static void game(String[] args) throws InterruptedException {
        for (int i = 0; i < SIZE; i++) { //fill board with dots
          for (int j = 0; j < SIZE; j++) { 
            board[i][j] = BACKROUND;
            answers[i][j] = BACKROUND;
          }
        }
        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[i].length; j++) {
                answers[ships[i][j][0]][ships[i][j][1]] = 'S';
            }
        }
        boolean run = true;
        while (run) {
            printout(args);
            System.out.println("Enter a coordinate to fire at: ");
            String input = key.next();
            String[] coords = input.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            if (answers[y][x] == '~') {
                board[y][x] = 'X';
                msg = "You missed!";
            } else if (answers[y][x] == 'S') {
                answers[y][x] = 'H';
                msg = "You hit a ship!";
            } else if (answers[y][x] == 'H') {
                msg = "You already fired at this location!";
            }
        }
    }



    public static void menu(String[] args) throws InterruptedException {
        boolean run = true;
        while (run) {
        System.out.print("\033[H\033[2J");  //flush the screen
        System.out.flush(); 
        System.out.println("Welcome to Jeopardy!");//print welcome message

        Thread.sleep(1000);   //wait to build suspence
        System.out.println("Enter a number for your selection:"); //print out menu
        System.out.println("1. Battleship Game");
        System.out.println("2. Exit to Main Menu");
        System.out.println("3. How to play");
        System.out.println("4. Shop");

        String selection = key.next(); //get user input

        switch (selection){
            case "1":
            game(args); // if they entered 1, start the game
            break;

            case "2":
            System.out.println("Goodbye!"); // if they entered 2, print goodbye, wait, and go back to main menu
            Thread.sleep(1000);
            run = false;
            break;        
            case "3": // if they entered 3, print the rules, wait for input, then go to the biggining
            System.out.print("\033[H\033[2J");  //flush the screen
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
            System.out.print("\033[H\033[2J");  //flush the screen
            System.out.flush(); 
            System.out.println("Invalid input, please try again."); // if they did not enter something correct, tell them
            Thread.sleep(2000);
        }
    }
    }
}
