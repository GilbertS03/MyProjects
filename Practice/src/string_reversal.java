import java.util.*;
import java.io.*;

public class string_reversal {
    public static void main(String[] args){
        String forward, backwards = "";
        char temp;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a word to be reversed:");
        forward = scan.next();
        for (int i = forward.length() - 1; i > -1; i--){
            temp = forward.charAt(i);
            backwards += temp;
        }
        System.out.println(backwards);

    }
}
