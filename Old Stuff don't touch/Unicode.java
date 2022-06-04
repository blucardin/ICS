public class Unicode {

  //Write a do...while loop that displays a Unicode table for the characters from 1..212.  Notice that some characters are unprintable and a box will be displayed (that’s okay).

  public static void main(String[] args) {
    int i = 1;
    do {
      System.out.println(i + " " + (char) i);
      i++;
    } while (i <= 212);
  }
}
