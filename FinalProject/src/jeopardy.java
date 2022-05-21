public class jeopardy {

  //creade 2d array
  public static int[][] matrix = {
    {100, 200, 300},
    {100, 200, 300},
    {100, 200, 300},
    {100, 200, 300},
  };

  public static void printout(String[] args){
    //print out the matrix
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + "  |  ");
      }
      System.out.println();
    }
  }
  
  public static void game(String[] args){
        System.out.println("Hello, World!");
        printout(args);
  }

}