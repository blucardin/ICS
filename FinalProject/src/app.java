import java.util.Scanner;

public class app {
    public static int coins = 0;
    //throws Exception
    public static void main(String[] args) throws InterruptedException{
        Scanner key = new Scanner(System.in);
        while (true) {
        System.out.print("\033[H\033[2J") ;  
        System.out.flush(); 
        System.out.println("Hello World!");
        System.out.println("And welcome to:");       
        System.out.println(
          "\033[0;101m ███    ██  ██████   █████  ██   ██ ███████      ██████   █████  ███    ███ ███████     ███████ ███    ███ ██████   ██████  ██████  ██ ██    ██ ███    ███	"+"\n" +
          "\033[0;103m ████   ██ ██    ██ ██   ██ ██   ██ ██          ██       ██   ██ ████  ████ ██          ██      ████  ████ ██   ██ ██    ██ ██   ██ ██ ██    ██ ████  ████	"+"\n" +
          "\033[0;102m ██ ██  ██ ██    ██ ███████ ███████ ███████     ██   ███ ███████ ██ ████ ██ █████       █████   ██ ████ ██ ██████  ██    ██ ██████  ██ ██    ██ ██ ████ ██	"+"\n" +
          "\033[0;104m ██  ██ ██ ██    ██ ██   ██ ██   ██      ██     ██    ██ ██   ██ ██  ██  ██ ██          ██      ██  ██  ██ ██      ██    ██ ██   ██ ██ ██    ██ ██  ██  ██	"+"\n" +
          "\033[0;105m ██   ████  ██████  ██   ██ ██   ██ ███████      ██████  ██   ██ ██      ██ ███████     ███████ ██      ██ ██       ██████  ██   ██ ██  ██████  ██      ██	"+"\n");
          System.out.println("\033[0;0m");
          System.out.println("We have a variety of games for you to play!");


        
          Thread.sleep(2000);
          System.out.println("Enter a number for a game:");
          System.out.println("1. Snake");
          System.out.println("2. Jeopardy");
          System.out.println("3. Exit");
          System.out.println("You have " + coins + " coins to spend!");
          
          
        
          String selection = key.next();
        
          switch (selection){
            case "1":
              snake.menu(args);
              break;
            case "2":
              jeopardy.menu(args);
              break;
            case "3":
              System.out.println("Goodbye!");
              System.exit(0);
              key.close();
              break;
            default:
              System.out.println("Invalid input. Please try again.");
              break;
          }
          
        }
    }
}