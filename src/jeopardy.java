/* This is the file that runs the jeopardy game.
 * It displays a menu, and gets user input.
 * Based on what the user wants to do, it will either start the jeopardy game, go to the shop, print the rules, or go back the main menu.
 */

import java.util.Scanner;

public class jeopardy {

  //create  array for colors
  static String[] colors = { color.RESET, color.RESET, color.RED, color.GREEN };

  // colors 0 represents menu color, 1 represents background color, 2 represents question color, 3 represents answer color

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
      System.out.println("3. Questions");
      System.out.println("4. Answers");
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
      System.out.println("What color would you like to buy?");
      System.out.println("1. Green (10 coins)");
      System.out.println("2. Red (20 coins)");
      System.out.println("3. Blue (30 coins)");
      System.out.println("4. Yellow (40 coins)");
      System.out.println("5. Exit back to menu");
      System.out.println("\nYou have " + app.coins + " coins.\n");
      System.out.println("Enter your choice: ");
      String colorChoice = key.next();
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

  //create 2d array
  public static String[][] points = { // define point value matrix
    { "100", "200", "300" },
    { "100", "200", "300" },
    { "100", "200", "300" },
    { "100", "200", "300" },
  };

  public static String[][] questions = { // define question value matrix
    { "What is a baby cow called?", "What do you call a cow with no legs?", "How long did the longest chicken live? (in years)" },
    { "How many elements are in the periodic table?", "How many dots are on a pair of dice?", "What was the lastname of the first black president of America?" },
    { "Is an apple food?", "I have a pen, I have an apple, UH ________", "Do you put the milk or the cereal first?" },
    { "How many continents are there?", "What is the biggest country?", "What is the best country?" },
  };

  public static String[][] answers = { // define answer value matrix
    { "calf", "ground beef", "16" },
    { "118", "42", "obama" },
    { "yes", "applepen", "Trick Question: You put the bowl first" },
    { "7", "russia", "canada" },
  };

  public static String[] sections = { // define section matrix
    "Animals",
    "Misc.",
    "Food",
    "World",
  };

  static Scanner key = new Scanner(System.in); //create new scanner

  public static void printout() {
    System.out.print("\033[H\033[2J"); //flush the screen
    System.out.flush();
    System.out.print("| 1.animals |  2.Misc.  |   3.Food  |  4.World  |"); //print out the sections

    System.out.println();
    for (int i = 0; i < points[0].length; i++) { // print out the points array
      for (int j = 0; j < points.length; j++) {
        System.out.print(
          colors[1] + "|    " + colors[2] + points[j][i] + colors[1] + "    "
        );
      }
      System.out.println("|");
    }
  }

  public static void game() throws InterruptedException {
    System.out.println(colors[0]);
    final String CLEAR = "   ";
    boolean run = true;
    while (run) { //if the user wants to play again, this loop will run the game again
      while (run) {
        for (int y = 0; y < questions.length; y++) { // check if the user answered all the questions
          for (int x = 0; x < questions[y].length; x++) { //loop over questions
            if (questions[y][x].equals(CLEAR)) {
              System.out.println(
                "You have answered all the questions! Thank you for playing! Enter 0 to exit."
              );
              break;
            }
          }
        }

        printout(); //print out the points array
        System.out.println(colors[0]);

        System.out.println(
          "Pick a section number (1, 2, 3, or 4. Enter 0 to exit.):"
        ); //prompt user to pick a section
        int section = key.nextInt() - 1; //get user input for section
        if (section <= -1) { //if user enters 0, exit the game
          run = false;
          break;
        }
        int question = 0; //create question variable
        while (true) {
          System.out.println("Pick a question number (100, 200, or 300):"); //prompt user to pick a question number
          question = key.nextInt(); //get user input for question number
          Thread.sleep(1000);
          if (question < 100 || question > 300) { //if user enters an invalid question number, print error message
            System.out.println("Invalid input, try again\n");
          } else if (points[section][(question / 100) - 1].equals(CLEAR)) { //if user enters a question that has already been answered, print error message
            System.out.println("Question already used, try again\n");
          } else {
            break; //if user enters a valid question number, break the loop
          }
        }

        System.out.println(
          "\n You chose " + sections[section] + " for " + question
        ); //print out the section and question number
        Thread.sleep(1000);

        System.out.println(
          colors[2] +
          "Your question is : \n" +
          questions[section][(question / 100) - 1]
        ); //print out the question
        System.out.println("Enter your answer:");
        questions[section][(question / 100) - 1] = CLEAR; //set the question to CLEAR
        points[section][(question / 100) - 1] = CLEAR; //set the points display for that question to CLEAR
        boolean correct = false; //create boolean variable for if the user is correct

        for (int i = 1; i <= 3; i++) { //loop over the answers
          key.nextLine();
          String answer = key.nextLine();
          if (answer.equalsIgnoreCase(answers[section][(question / 100) - 1])) { //if the user enters the correct answer, print success message
            System.out.print("\033[H\033[2J"); //flush the screen
            System.out.flush();
            System.out.print(colors[3]);
            System.out.println("You are correct!");
            int coinIncrease = question - (i * 50); //calculate the amount of coins the user will get
            System.out.println("You earned " + coinIncrease + " coins."); //print out the amount of coins the user will get
            System.out.println("Enter any key to continue."); //prompt user to continue
            key.next();
            app.coins += coinIncrease;

            correct = true;
            break;
          } else {
            System.out.print(colors[3]);
            System.out.println("Wrong!"); //if the user enters the wrong answer, print error message
            System.out.println(
              "Try again, you have " + (3 - i) + " more chances" //print out the amount of chances the user has
            );
            System.out.print(colors[2]);
          }
        }

        System.out.print(colors[0]);

        if (correct == false) { //if the user is wrong more than 3 times, give them the answer
          System.out.println(
            "You are out of chances, the answer was " + 
            colors[3] +
            answers[section][(question / 100) - 1] +
            colors[0]
          );
          System.out.println("try another question");
          System.out.println("Enter any key to continue.");   //prompt user to continue
          key.next();
        }
      }

      if (run == true) {
        System.out.println("Play again? (y/n)"); //prompt user to play again
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
          System.out.println(
            "Answer the question incorrectly and you will be given 3 more changes."
          );
          System.out.println("Enter section '0' to quit the game.");
          Thread.sleep(1000);
          System.out.println("Enter any key to return back to the menu:");
          key.next();
          break;
        case "4": // if they entered 4, run the shop
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
