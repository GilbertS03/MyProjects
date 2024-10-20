import java.lang.reflect.Array;
import java.util.*;

public class calculator {
    public static int welcome(){
        int choice;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("""
                1. Add\s
                2. Subtract\s
                3. Multiply\s
                4. Divide\s
                5. Exponentiate\s
                6. Square Root\s
                7. Quit""");
        choice = scan.nextInt();
        while (choice < 1 || choice > 7){
            System.out.println("""
                    1. Add\s
                    2. Subtract\s
                    3. Multiply\s
                    4. Divide\s
                    5. Exponentiate\s
                    6. Square Root\s
                    7. Quit""");
            choice = scan.nextInt();
        }
        return choice;
    }

    public static double add(double a, double b){
        double z;
        z = a + b;
        return z;
    }

    public static double subtract(double a, double b){
        double z;
        z = a - b;
        return z;
    }

    public static double multiply(double a, double b){
        double z;
        z = a * b;
        return z;
    }

    public static double divide(double a, double b){
        double z;
        z = a / b;
        return z;
    }
    public static double exponential(double a, double b){
        double z, temp = a;
        for (int i = 1; i < b; i++){
            a *=temp;
        }
        z = a;
        return z;
    }

    public static double square_root(double a){
        double z;
        double sqrt = a / 2;
        do {
            z = sqrt;
            sqrt = (z + (a / z)) / 2;
        }
        while((z - sqrt) != 0);
        return z;
    }

    public static void main(String[] args){
        double x, y;
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        ArrayList <Integer> numbers= new ArrayList<Integer>();

        while (choice != 7){
            choice = welcome();
            if (choice == 1){
                System.out.println("Enter the numbers you would like to add: ");
                x = scan.nextDouble();
                y = scan.nextDouble();
                System.out.println(add(x, y));
            }
            else if(choice == 2){
                System.out.println("Enter the numbers you would like to subtract: ");
                x = scan.nextDouble();
                y = scan.nextDouble();
                System.out.println(subtract(x, y));
            }
            else if(choice == 3){
                System.out.println("Enter the numbers you would like to multiply: ");
                x = scan.nextDouble();
                y = scan.nextDouble();
                System.out.println(multiply(x, y));
            }
            else if(choice == 4){
                System.out.println("Enter the numbers you would like to divide: ");
                x = scan.nextDouble();
                y = scan.nextDouble();
                System.out.println(divide(x, y));
            }
            else if(choice == 5){
                System.out.println("Enter the numbers you would like to exponentiate: ");
                x = scan.nextDouble();
                y = scan.nextDouble();
                System.out.println(exponential(x, y));
            }
            else if(choice == 6){
                System.out.println("Enter the number you would like to square root: ");
                x = scan.nextDouble();
                System.out.println(square_root(x));
            }
        }
        System.out.println("Goodbye!");
    }
}
