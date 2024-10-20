import java.util.*;
import java.io.*;
public class Random {
    public static void main(String[] args) throws IOException {
        //Scanner keyboard = new Scanner(System.in);
/*        int height;
        System.out.println("What do you want the height of the triangle to be?");
        height = keyboard.nextInt();
        String symbol, symbol1;
        System.out.println("What symbol would you like?");
        symbol = keyboard.next();
        symbol1 = symbol;
        for (int i = 0; i < height; i++){
            System.out.println(symbol);
            symbol += symbol1;
        }
 */
        Scanner infile = new Scanner(new File(".idea/words.txt"));
        int num_words = infile.nextInt();
        System.out.println(num_words);
        String words[] = new String[num_words];
        String file_words = infile.next();
        //System.out.println(file_words);
        int i = 0;
        while (i < num_words) {
            words[i] = file_words;
            file_words = infile.next();
           // System.out.println(file_words);
            System.out.println(words[i]);
            i++;
        }
    }
}