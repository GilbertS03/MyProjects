import java.io.*;
import java.util.*;

public class number_guess {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int x = rand.nextInt(0, 1000);
        System.out.println("Guess the number");
        int guess = scan.nextInt(), count = 0;
        while (guess != x){
            if (guess > x){
                System.out.println("Lower!");
            }
            else if (guess < x){
                System.out.println("Higher!");
            }
            count++;
            guess = scan.nextInt();
        }
        if (guess == x){
            System.out.println("Winner!");
            System.out.println("It took " + count + " guesses!");
        }
    }
}
