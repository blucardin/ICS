import java.util.Scanner;

public class jeopardy {

    //create  array for colors
  static String[] colors = {color.RESET, color.RESET, color.RED, color.GREEN};
  // colors 0 represents menu color, 1 represents background color, 2 represents question color, 3 represents answer color

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
      System.out.println("3. Questions");
      System.out.println("4. Answers");
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

  //create 2d array
  public static String[][] points = { // define question value matrix
    { "100", "200", "300" },
    { "100", "200", "300" },
    { "100", "200", "300" },
    { "100", "200", "300" },
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

  public static void printout() {
    System.out.print("\033[H\033[2J"); //flush the screen
    System.out.flush();
    for (int w = 0; w < sections.length; w++) { //print out the sections
      System.out.print(colors[1] + (w + 1) + "." + sections[w] + " | ");
    }
    System.out.println();
    for (int i = 0; i < points[0].length; i++) { // print out the points array
      for (int j = 0; j < points.length; j++) {
        System.out.print(colors[1] + "|    " + colors[2] + points[j][i] + colors[1] + "    ");
      }
      System.out.println("|");
    }
  }

  public static void game() throws InterruptedException {
    System.out.println(colors[0]);
    final String CLEAR = "   ";
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

        printout();
        System.out.println(colors[0]);
        
        System.out.println(
          "Pick a section number (1, 2, 3, or 4. Enter 0 to exit.):"
        );
        int section = key.nextInt() - 1;
        if (section <= -1) {
          run = false;
          break;
        }
        int question = 0;
        while (true) {
          System.out.println("Pick a question number (100, 200, or 300):");
          question = key.nextInt();
          Thread.sleep(1000);
          if (question < 100 || question > 300){
            System.out.println("Invalid input, try again\n");
          }
          else if (points[section][(question / 100) - 1].equals(CLEAR)){
            System.out.println("Question already used, try again\n");
          }
          else{
            break; 
          }
          
        }

        System.out.println(
          "\n You chose " + sections[section] + " for " + question
        );
        Thread.sleep(1000);

        System.out.println( colors[2] + 
          "Your question is" + questions[section][(question / 100) - 1]
        );
        System.out.println("Enter your answer:");
        questions[section][(question / 100) - 1] = CLEAR;
        points[section][(question / 100) - 1] = CLEAR;
        boolean correct = false;

        for (int i = 1; i <= 3; i++) {
          String answer = key.next();
          if (answer.equals(answers[section][(question / 100) - 1])) {
            System.out.print("\033[H\033[2J"); //flush the screen
            System.out.flush();
            System.out.print(colors[3]);
            System.out.println("You are correct!");
            int coinIncrease = question - (i * 50);
            System.out.println("You earned " + coinIncrease + " coins.");
            System.out.println("Enter any key to continue.");
            key.next();
            app.coins += coinIncrease;

            correct = true;
            break;
          } else {
            System.out.print(colors[3]);
            System.out.println("Wrong!");
            System.out.println(
              "Try again, you have " + (3 - i) + " more chances"
            );
            System.out.print(colors[2]);
          }
        }

        System.out.print(colors[0]);

        if (correct == false) {
          System.out.println("You are out of chances, the answer was "+ colors[3] + answers[section][(question / 100) - 1] + colors[0]);
          System.out.println("try another question"); 
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

  public static void menu() throws InterruptedException {
    boolean run = true;
    while (run) {
      System.out.print("\033[H\033[2J"); //flush the screen
      System.out.flush();
      System.out.println(colors[0]);
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
          System.out.println("Select a section and a question.");
          System.out.println("Answer the question correctly to gain points.");
          System.out.println("Answer the question incorrectly and you will be given 3 more changes.");
          System.out.println("Enter section '0' to quit the game.");
          Thread.sleep(1000);
          System.out.println("Enter any key to return back to the menu:");
          key.next();
          break;
        case "4":
          shop();
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
