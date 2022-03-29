package Console_Video_Game;

import java.util.Scanner;
import java.util.Random;


class Main {
  
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    final char BACKROUND = '.';
    final int SIZE = 20;

    char[][] string = new char[SIZE][SIZE * 2];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE * 2; j++) {
        string[i][j] = BACKROUND;
      }
    }

    System.out.println("Welcome to the game! Press any key to begin.");

    int x = 2;
    int y = 2;
    boolean triggered = false;
    String character = new String();
    
    while (true) { 

      character = input.next();
      if (character.equals("d")){
        x += 1;
        triggered = true;
      } else if (character.equals("a")) {
        x -= 1;
        triggered = true;
      } else if (character.equals("w")) {
        y -= 1;
        triggered = true;
      } else if (character.equals("s")) {
        y += 1;
        triggered = true;
      } else if (character.equals("q")) {
        break;
      }

      if (triggered) {
        for (int i = 0; i < SIZE; i++) {
          for (int j = 0; j < SIZE * 2; j++) {
            string[i][j] = BACKROUND;
          }
        }
        triggered = false;
      }

      string[y][x] = '*';

      System.out.print("\033[H\033[2J");  
      System.out.flush(); 
      
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE * 2; j++) {
          System.out.print(string[i][j]);
        }
        System.out.println();
      }
    }    
  }
}