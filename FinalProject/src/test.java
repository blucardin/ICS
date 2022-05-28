public class test {
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        //loop 100 times
        int i = 0;
        while (i < 150) {
            //print out the i
            System.out.println(i + "\033[0;" + i + "m" + "Hello World!" + "\033[0m");
            //sleep for 1 second
            i++;
        }
        

    }
}
