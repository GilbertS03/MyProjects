import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main{
    public static void populate(ArrayList<String> names, ArrayList<Double> salary, ArrayList<Double> hours){
        try{
            double x = 0;
            Scanner infile = new Scanner(new File("employee_data.csv"));
            while(infile.hasNext()){
                names.add(infile.next() + " " + infile.next());
                salary.add(infile.nextDouble());
                while(infile.hasNextDouble()){
                    x += infile.nextDouble();
                }
                hours.add(x);
            }
            System.out.println(names);
            System.out.println(salary);
            System.out.println(hours);
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("An error has occurred");
        }
    }

    public static int menu(Scanner scan){
        try{
            int choice;
            System.out.println("1. Show all");
            System.out.println("2. Search Employee");
            System.out.println("3. Highest Salary");
            System.out.println("4. Highest Hours");
            System.out.println("5. Quit");
            choice = scan.nextInt();
            while(choice > 5 || choice < 1 && choice != -1){
                System.out.println("Choice not valid, try again");
                System.out.println("1. Show all");
                System.out.println("2. Search Employee");
                System.out.println("3. Highest Salary");
                System.out.println("4. Highest Hours");
                System.out.println("5. Quit");
                choice = scan.nextInt();
            }
            return choice;
        }
        catch(InputMismatchException e){
            scan.next();
        }
        return -1;
    }

    public static void main(String[] args){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Double> salary = new ArrayList<>();
        ArrayList<Double> hours = new ArrayList<>();
        populate(names, salary, hours);
        Scanner scan = new Scanner(System.in);
        int choice = menu(scan);

        while(choice != 5){
            if (choice == 1){

            }
            else if(choice == 2){

            }
            else if(choice == 3){

            }
            else if(choice == 4){

            }
            else if(choice == -1){
                System.out.println("There was an error");
                System.out.println("Try again\n");
            }
            choice = menu(scan);
        }

    }
}