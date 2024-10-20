import java.util.*;
public class Class {
    public static void main(String[] args){
        //Solution to number 1
/*        int male, female;
        System.out.println("How many males are in your class?");
        male = kbd.nextInt();
        System.out.println("How many females are in your class?");
        female = kbd.nextInt();
        System.out.println("% of males is " + (float)male / (male + female));
        System.out.println("% of females is " + (float)female / (male + female));
*/
        //Solution to number 2
/*        int x, y;
        System.out.println("Enter a number:");
        x = kbd.nextInt();
        System.out.println("Enter another number: ");
        y = kbd.nextInt();
        if (x > y){
            System.out.println(x + " is greater than " + y);
        }
        else if (x < y){
            System.out.println(y + " is greater than " + x);
        }
        else{
            System.out.println("The numbers are equal");
        }
*/
        //Solution to number 3
/*      int sum = 0, x, largest = Integer.MIN_VALUE;
        System.out.println("Enter a number ");
        for (int i = 0; i < 10; i++) {
            x = kbd.nextInt();
            sum += x;
            if (x > largest)
                largest = x;
            System.out.println("Enter a number ");
        }
        System.out.println("Sum: " + sum + " Largest: " + largest);
*/

        //Solution to number 4
/*        int y;
        System.out.println("Type in numbers or -1 to end: ");
        y = kbd.nextInt();
        while (y != -1){
            if (y % 2 == 1)
                System.out.println("The number is odd");
            else
                System.out.println("The number is even");
            System.out.println("Type in numbers or -1 to end: ");
            y = kbd.nextInt();
        }
*/
        //Solution to number 5
/*        float x, sum = 0, counter = 0;
        System.out.println("Enter your score in decimal form: (-1.0 to end)");
        x = kbd.nextFloat();
        while (x != -1.0){
            System.out.println("The number in percentage form is " + x * 100);
            sum += x;
            counter++;
            System.out.println("Enter your score in decimal form: (-1.0 to end)");
            x = kbd.nextFloat();
        System.out.println("The average is " + (sum/counter) + " or " + (sum/counter) * 100 + "%");
        }
*/
        Scanner kbd = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<String>();
        System.out.println("Enter some words (-1 to end)");
        String x = kbd.next();
        while (!x.equals("-1")){
            words.add(x);
            x = kbd.next();
        }
        System.out.println(words.size());
        System.out.println(words);



    }
}