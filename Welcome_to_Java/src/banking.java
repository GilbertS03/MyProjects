import java.util.*;
import java.io.*;
class Account{
    private double amount;
    public Account(){
        amount = 0;
    }
    public Account(double a){
        amount = a;
    }
    public void deposit(double x){
        amount += x;
    }
    public void withdrawal(double x){
        amount -= x;
    }
    public double checkBalance(){
        return amount;
    }
}
class Login{
    private String firstName;
    private String lastName;
    private String ID;
    private String email;
    private String password;
    public Login(){
        firstName = "";
        lastName = "";
        ID = "";
        email = "";
        password = "";
    }
    public Login(String f, String l, String i, String e, String p){
        firstName = f;
        lastName = l;
        ID = i;
        email = e;
        password = p;
    }
    public String checkFirst(){
        return firstName;
    }
    public String checkLast(){
        return lastName;
    }
    public String checkID(){
        return ID;
    }
    public String checkPassword(){
        return password;
    }
    public String checkEmail(){
        return email;
    }
    public void changePassword(String x){
        password = x;
    }
}

public class banking{
    public static int displayInterface(){
        Scanner scan = new Scanner(System.in);

        return 0;
    }
    public static Login makeLogin(){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter your first name");
        String f,l,e,p;
        boolean upper = false, lower = false, special = false, number = false;
        System.out.println("What is your first name?");
        f = scan.next();
        System.out.println("What is your last name?");
        l = scan.next();
        System.out.println("What is your email?");
        e = scan.next();
        System.out.println("Enter a strong password containing a capital letter, lowercase letter, special character," +
                " one number, and at least 8 characters long:");
        p = scan.next();
        int i = 0;
        if(p.length() > 8) {
            while (!upper && !lower && !special && !number) {
                char letter = p.charAt(i);
                upper = Character.isUpperCase(letter);
                lower = Character.isLowerCase(letter);
                special = Character.isAlphabetic(letter);
                special = Character.isDigit(letter);
                number = Character.isDigit(letter);
                i++;
            }
        }

        int x = rand.nextInt(0,9);
        String y = "";

        for (int j = 0; j < 5; j++){
            if(y.isEmpty()){
                y = String.valueOf(x);
            }
            else{
                y = y + String.valueOf(x);
            }
            x = rand.nextInt(0, 9);
        }
        Login s = new Login(f, l, y, e, p);
        System.out.println(s.checkFirst());
        System.out.println(s.checkLast());
        System.out.println(s.checkEmail());
        System.out.println(s.checkPassword());
        System.out.println(s.checkID());
        System.out.println("Is information correct? (Y/N)");
        //if n, make a list of things that are wrong and change them depending on what they choose
        return s;
    }
    public static Account makeAccount(){
        Account a = new Account();
        return a;
    }
    public static void main(String[] args){
        HashMap<Login, Account> logAcc = new HashMap<>();
        ArrayList<HashMap<Login, Account>> list = new ArrayList<>();
        if(list.isEmpty()){
            logAcc.put(makeLogin(),makeAccount());

        }
        System.out.println("Hello World");
    }
}
