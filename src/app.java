import java.util.Scanner;

public class app {

  public static int coins = 0;
  public static boolean devMode = true;
  static Scanner key = new Scanner(System.in);

  public static void picker(String pick) throws InterruptedException{
    switch (pick) {
          case "1":
            snake.menu();
            break;
          case "2":
            jeopardy.menu();
            break;
          case "3":
            battleship.menu();
            break;
          case "4":
            tictactoe.menu();
            break;
          case "5":
            System.out.println("Goodbye!");
            System.exit(0);
            key.close();
            break;
          default:
            System.out.println("Invalid input. Please try again.");
            break;
        }
  }

  //throws Exception
  public static void main(String[] args) throws InterruptedException {
    while (true) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("Hello World!");
      System.out.println("And welcome to:");
      System.out.println(
        "\033[0;101m ███    ██  ██████   █████  ██   ██ ███████      ██████   █████  ███    ███ ███████     ███████ ███    ███ ██████   ██████  ██████  ██ ██    ██ ███    ███	" +
        "\n" +
        "\033[0;103m ████   ██ ██    ██ ██   ██ ██   ██ ██          ██       ██   ██ ████  ████ ██          ██      ████  ████ ██   ██ ██    ██ ██   ██ ██ ██    ██ ████  ████	" +
        "\n" +
        "\033[0;102m ██ ██  ██ ██    ██ ███████ ███████ ███████     ██   ███ ███████ ██ ████ ██ █████       █████   ██ ████ ██ ██████  ██    ██ ██████  ██ ██    ██ ██ ████ ██	" +
        "\n" +
        "\033[0;104m ██  ██ ██ ██    ██ ██   ██ ██   ██      ██     ██    ██ ██   ██ ██  ██  ██ ██          ██      ██  ██  ██ ██      ██    ██ ██   ██ ██ ██    ██ ██  ██  ██	" +
        "\n" +
        "\033[0;105m ██   ████  ██████  ██   ██ ██   ██ ███████      ██████  ██   ██ ██      ██ ███████     ███████ ██      ██ ██       ██████  ██   ██ ██  ██████  ██      ██	" +
        "\n"
      );
      System.out.println("\033[0;0m");
      System.out.println("We have a variety of games for you to play!");

      Thread.sleep(2000);
      System.out.println("You have " + coins + " coins to spend!\n");
      System.out.println("Enter a number for a game:");
      System.out.println("1. Snake");
      System.out.println("2. Jeopardy");
      System.out.println("3. BattleShip");
      System.out.println("4. Tic Tac Toe");
      System.out.println("5. Exit");

      String selection = key.next();

      if (devMode == true){
        picker(selection);
      } 
      else if (devMode == false){
        try {
          picker(selection);
        } catch (Exception e) {
          System.out.println(
            "It appears there was an error. Usually this is caused by an invalid input. Please try again.");
          System.out.println("The error was: ");
          System.out.println(e);
          Thread.sleep(1000);
          System.out.println("Enter any key to return back to the menu:");
          key.next();
        }
      }
    }
  }
}
