import java.util.Scanner;

public class jeopardy {

  //create 2d array
  public static int[][] points = { // define question value matrix
    { 100, 200, 300 },
    { 100, 200, 300 },
    { 100, 200, 300 },
    { 100, 200, 300 },
  };

  public static String[][] questions = { // define question value matrix
    { "Question1,1", "Question1,2", "Question1,3" },
    { "Question2,1", "Question2,2", "Question2,3" },
    { "Question3,1", "Question3,2", "Question3,3" },
    { "Question4,1", "Question4,2", "Question4,3" },
  };

  public static String[][] answers = { // define answer value matrix
    { "answer1,1", "answer1,2", "answer1,3" },
    { "answer2,1", "answer2,2", "answer2,3" },
    { "answer3,1", "answer3,2", "answer3,3" },
    { "answer4,1", "answer4,2", "answer4,3" },
  };

  public static String[] sections = { // define section matrix
    "Section1",
    "Section2",
    "Section3",
    "Section4",
  };

  static Scanner key = new Scanner(System.in); //create new scanner

  public static void printout(String[] args) {
    System.out.print("\033[H\033[2J"); //flush the screen
    System.out.flush();
    for (int w = 0; w < sections.length; w++) { //print out the sections
      System.out.print((w + 1) + "." + sections[w] + " | ");
    }
    System.out.println();
    for (int i = 0; i < points[0].length; i++) { // print out the points array
      for (int j = 0; j < points.length; j++) {
        System.out.print("   " + points[j][i] + "   |");
      }
      System.out.println();
    }
  }

  public static void game(String[] args) throws InterruptedException {
    final String CLEAR = "    ";
    boolean run = true;
    while (run) {
      while (run) {
        //loop over questions
        for (int y = 0; y < questions.length; y++) {
          for (int x = 0; x < questions[y].length; x++) {
            if (questions[y][x].equals(CLEAR)) {
              System.out.println(
                "You have answered all the questions! Thank you for playing!"
              );
              break;
            }
          }
        }

        printout(args);

        System.out.println(
          "Pick a section number (1, 2, 3, or 4. Enter 0 to exit.):"
        );
        int section = key.nextInt() - 1;
        if (section == -1) {
          run = false;
          break;
        }
        System.out.println("Pick a question number (100, 200, or 300):");
        int question = key.nextInt();
        Thread.sleep(1000);

        System.out.println(
          "You chose " + sections[section] + " for " + question
        );
        Thread.sleep(1000);

        System.out.println(
          "Your question is" + questions[section][(question / 100) - 1]
        );
        System.out.println("Enter your answer:");
        questions[section][(question / 100) - 1] = CLEAR;
        boolean correct = false;

        for (int i = 1; i <= 3; i++) {
          String answer = key.next();
          if (answer.equals(answers[section][(question / 100) - 1])) {
            System.out.print("\033[H\033[2J"); //flush the screen
            System.out.flush();
            System.out.println("You are correct!");
            int coinIncrease = question - (i * 50);
            System.out.println("You earned " + coinIncrease + " coins.");
            System.out.println("Enter any key to continue.");
            key.next();
            app.coins += coinIncrease;

            correct = true;
            break;
          } else {
            System.out.println("Wrong!");
            System.out.println(
              "Try again, you have " + (3 - i) + " more chances"
            );
          }
        }

        if (correct == false) {
          System.out.println("You are out of chances, try another question.");
          System.out.println("Enter any key to continue.");
          key.next();
        }
      }

      if (run == true) {
        System.out.println("Play again? (y/n)");
        String play = key.next();
        if (!play.equals("y")) {
          run = false;
        }
      }
    }
  }

  public static void menu(String[] args) throws InterruptedException {
    boolean run = true;
    while (run) {
      System.out.print("\033[H\033[2J"); //flush the screen
      System.out.flush();
      System.out.println("Welcome to Jeopardy!"); //print welcome message

      Thread.sleep(1000); //wait to build suspense
      System.out.println("Enter a number for your selection:"); //print out menu
      System.out.println("1. Jeopardy Game");
      System.out.println("2. Exit to Main Menu");
      System.out.println("3. How to play");
      System.out.println("4. Shop");

      String selection = key.next(); //get user input

      switch (selection) {
        case "1":
          game(args); // if they entered 1, start the game
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
          System.out.println("Enter any key to return back to the menu:");
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
