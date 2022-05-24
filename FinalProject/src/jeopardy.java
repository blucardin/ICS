import java.util.Scanner;
public class jeopardy {

  //creade 2d array
  public static int[][] matrix = { // define question value matrix
    {100, 200, 300},
    {100, 200, 300},
    {100, 200, 300},
    {100, 200, 300},
  };

  public static String[] sections = { // define section matrix
    "Section1", "Section2", "Section3",
  };

  static Scanner key = new Scanner(System.in); 

  public static void printout(String[] args){
    for (int w = 0; w < sections.length; w++){  //print out the sections
      System.out.print(w + "." + sections[w] + " | ");
    }
    System.out.println();
    for (int i = 0; i < matrix[0].length; i++) { // print out the points array
      for (int j = 0; j < matrix.length; j++) {
        System.out.print("   " + matrix[j][i] + "   |");
      }
      System.out.println();
    }


  }
  
  public static void game(String[] args){
    boolean run = true; 
    while (run) {
      printout(args);
      System.out.println("Pick a section number:");
      String section = key.next();
      System.out.println("Pick a question number:");
      String question = key.next();
      
      }
  }

    public static void menu(String[] args) throws InterruptedException {
    while (true) {
      System.out.print("\033[H\033[2J");  //flush the screen
      System.out.flush(); 
      System.out.println("Welcome to Jeopardy!");//print welcome message

      Thread.sleep(1000);   //wait to build suspence
      System.out.println("Enter a number for your selection:"); //print out menu
      System.out.println("1. Jeopardy Game");
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
          System.exit(0);
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