package Console_Video_Game;

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;


class Main {
  
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    final char BACKROUND = '.';
    final int SIZE = 20;
    ArrayList<ArrayList<Integer>> blocks = new ArrayList<ArrayList<Integer>>();
    blocks.add(new ArrayList<Integer>(Arrays.asList(SIZE/2, SIZE/2)));
    blocks.add(new ArrayList<Integer>(Arrays.asList((SIZE/2), (SIZE/2)-1)));

    Random rand = new Random();
    ArrayList<Integer> newBlock = new ArrayList<Integer>(Arrays.asList(rand.nextInt(SIZE), rand.nextInt(SIZE)));

    char[][] string = new char[SIZE][SIZE * 2];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE * 2; j++) {
        string[i][j] = BACKROUND;
      }
    }

    char direction = 'w';

    System.out.println("Welcome to the game! Press any key to begin.");

    int x = 2;
    int y = 2;
    boolean triggered = true;
    String character = new String();
    
    while (true) { 
      character = input.next();
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
          character = input.next();
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
            string[i][j] = BACKROUND;
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

        if (blocks.contains(newBlock)) {
          newBlock = new ArrayList<Integer>(Arrays.asList(rand.nextInt(SIZE), rand.nextInt(SIZE)));
        } else {
          blocks.remove(0);
        }
      }
      triggered = true;

      for (int i = 0; i < blocks.size(); i++) {
        string [blocks.get(i).get(0)][blocks.get(i).get(1)] = '*';
      }
      string [newBlock.get(0)][newBlock.get(1)] = '0';
      
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE * 2; j++) {
          System.out.print(string[i][j]);
        }
        System.out.println();
      }
    }    
  }
}