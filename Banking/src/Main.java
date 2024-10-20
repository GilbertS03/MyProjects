import java.io.*;
import java.util.*;

class Account{
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String status;
    private double balance;
    public Account(String fn, String ln, String em, double bal, String stats){
        fullName = fn + " " + ln;
        email = em;
        balance = bal;
        status = stats;
    }
    public void setFirstName(String fn){firstName = fn;}
    public void setLastName(String ln){lastName = ln;}
    public void setFullName(String fn){fullName = fn;}
    public void setEmail(String em){email = em;}
    public void setBalance(double money){balance = money;}
    public void deposit(double mon){balance += mon;}
    public void withdrawal(double mon){balance -= mon;}

    public String getFullName(){return fullName;}
    public String getEmail(){return email;}
    public double getBalance(){return balance;}
    public String getStatus(){return status;}
}

class User{
    private String ID;
    private String password;
    public User(String id, String pw){
        ID = id;
        password = pw;
    }
    public void setPassword(String pw){password = pw;}
    public void setID(String id) {ID = id;}

    public String getID(){return ID;}
    public String getPassword(){return password;}
}

public class Main {
    //Pulling up info from previous sessions
    public static HashMap<User, Account> priorInfo(HashMap<User, Account>userAccountHM) throws IOException {
        String first, last, email, ID, password, status;
        int id;
        double balance;
        User person;
        Account personAccount;
        File f = new File("usernamePasswords.txt");
        if(f.exists()) {
            Scanner infile = new Scanner(f);
            while (infile.hasNext()) {
                first = infile.next();
                last = infile.next();
                id = infile.nextInt();
                ID = Integer.toString(id);
                email = infile.next();
                password = infile.next();
                balance = infile.nextDouble();
                status = infile.next();
                person = new User(ID, password);
                personAccount = new Account(first, last, email, balance, status);
                userAccountHM.put(person, personAccount);
            }
        }
        return userAccountHM;
    }

    //Check to make sure the id is unique from creating a new user
    public static boolean checkID(HashMap<User, Account> userAccountHM, String ID){
        boolean unique = true;
        for(User uniqueID : userAccountHM.keySet()){
            if(uniqueID.getID().equals(ID)) {
                unique = false;
                break;
            }
        }
        return unique;

    }

    //Making user
    public static User createUser(Scanner scan, HashMap<User, Account>userAccountHM){
        User newUser;
        int randomID;
        boolean goodPW = false, unique = false;
        String pw = "", ID;
        Random rand = new Random();

        //Creating random integer for id number
        randomID = rand.nextInt(0, 99999);
        ID = Integer.toString(randomID);

        //Making sure there are no repeated ID numbers
        while(!unique){
            randomID = rand.nextInt(0, 99999);
            ID = Integer.toString(randomID);

            //Check ID uniqueness
            unique = checkID(userAccountHM, ID);
        }

        //Creating password
        while(!goodPW){
            System.out.println("Password must contain at least 8 characters, 1 cap, 1 lower, and 1 special");
            System.out.println("Enter a password: ");
            pw = scan.next();
            //Checking all the password fields
            if(pw.length() >= 8){
                boolean upper = false, lower = false, containsNum = false, containsSpecial = false;
                for(int i = 0; i < pw.length(); i++){
                    if(Character.isUpperCase(pw.charAt(i)))
                        upper = true;
                    else if(Character.isLowerCase(pw.charAt(i)))
                        lower = true;
                    else if(Character.isDigit(pw.charAt(i)))
                        containsNum = true;
                    else
                        containsSpecial = true;
                }
                if(upper && lower && containsSpecial && containsNum){
                    goodPW = true;
                    System.out.println("Valid password");
                }
                else
                    System.out.println("Password not good");
            }
            else{
                System.out.println("Password not long enough, try again");
            }
        }

        System.out.println("\n ______________________WRITE THIS DOWN_______________________\n");
        System.out.println("ID: " + ID);
        System.out.println("Password: " + pw);
        newUser = new User(ID, pw);
        return newUser;
    }

    public static Account createAccount(Scanner scan){
        String fn, ln, email, status = "member";
        double amount;
        Account newPerson;

        System.out.println("Enter first name: ");
        fn = scan.next();
        System.out.println("Enter last name: ");
        ln = scan.next();
        System.out.println("Enter email: ");
        email = scan.next();
        System.out.println("Enter initial deposit amount: ");
        amount = scan.nextDouble();
        while(amount < 0){
            System.out.println("Number must be positive, try again");
            amount = scan.nextDouble();
        }

        newPerson = new Account(fn, ln, email, amount, status);
        return newPerson;
    }

    //Making an account for managers
    public static Account createManagerAccount(Scanner scan){
        String fn, ln, email, status = "manager";
        double amount = 0; //Manager account doesn't need money
        Account newPerson;
        //Getting information
        System.out.println("Enter first name: ");
        fn = scan.next();
        System.out.println("Enter last name: ");
        ln = scan.next();
        System.out.println("Enter email: ");
        email = scan.next();
        System.out.println("Enter initial deposit amount: ");
        //Creating account
        newPerson = new Account(fn, ln, email, amount, status);
        return newPerson;
    }

    //Account menu for members
    public static void printAccountMenu(){
        System.out.println("______________________________");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("3. Check Balance");
        System.out.println("4. Check Information");
        System.out.println("5. Quit");
        System.out.println("______________________________");
    }

    //Menu when first opening this up
    public static void printDefaultMenu(){
        System.out.println("______________________________");
        System.out.println("1. Create Account");
        System.out.println("2. Log In");
        System.out.println("3. Log In Manager Account");
        System.out.println("4. Quit");
        System.out.println("______________________________");
    }

    //Menu for managers
    public static void printManagerMenu(){
        System.out.println("______________________________");
        System.out.println("1. Check Member Information");
        System.out.println("2. Delete Member");
        System.out.println("3. Quit");
        System.out.println("______________________________");
    }

    //Menu for members when they select an option
    public static void accountMenu(Scanner scan, HashMap<User, Account> userAccountHM, User person){
        int choice;
        double amount;
        System.out.println("______________________________");
        System.out.println("Welcome, " + userAccountHM.get(person).getFullName());
        printAccountMenu();
        choice = scan.nextInt();
        while(choice > 5 || choice < 1){
            System.out.println("Invalid choice, try again");
            choice = scan.nextInt();
        }
        while(choice != 5) {
            if (choice == 1) {
                System.out.println("Enter the amount you would like to deposit: ");
                amount = scan.nextDouble();
                while (amount < 0) {
                    System.out.println("Number must be positive, try again");
                    amount = scan.nextDouble();
                }
                userAccountHM.get(person).deposit(amount);
                System.out.println("You deposited $" + amount);
                System.out.println("Balance: $" + userAccountHM.get(person).getBalance());
                System.out.println("______________________________");
            }
            else if (choice == 2) {
                System.out.println("Enter the amount you would like to deposit: ");
                amount = scan.nextDouble();
                while (amount < 0 || (userAccountHM.get(person).getBalance() - amount) < 0) {
                    if (amount < 0) {
                        System.out.println("Number must be positive, try again");
                        amount = scan.nextDouble();
                    }
                    else if (userAccountHM.get(person).getBalance() - amount < 0) {
                        System.out.println("This is an overdraft, must be above the amount you have or equal to it");
                        System.out.println("Try again: ");
                        amount = scan.nextDouble();
                    }
                }
                userAccountHM.get(person).withdrawal(amount);
                System.out.println("You withdrew $" + amount);
                System.out.println("Balance: $" + userAccountHM.get(person).getBalance());
                System.out.println("______________________________");
            }
            else if(choice == 3) {
                System.out.println("Balance: $" + userAccountHM.get(person).getBalance());
                System.out.println("______________________________");

            }
            else if(choice == 4){
                System.out.println("Name: " + userAccountHM.get(person).getFullName() + "\nID: " + person.getID() +
                        "\nPassword: " + person.getPassword() + "\nEmail: " + userAccountHM.get(person).getEmail());
                System.out.println("______________________________");

            }
            printAccountMenu();
            choice = scan.nextInt();
            while(choice > 5 || choice < 1){
                System.out.println("Invalid choice, try again");
                choice = scan.nextInt();
            }
        }
    }

    //Logging in & using choice in parameters due to type of log in, if member login, the choice is 2, manager = 3
    public static User login(Scanner scan, HashMap<User, Account> userAccount, int choice){
        String id, password;
        int attempts = 1;
        User guest = null;
        boolean found = false;
        if(userAccount.isEmpty())
            System.out.println("No accounts exist");
        else{
            while(!found) {
                System.out.println("Enter your ID to login: ");
                id = scan.next();
                for (User person : userAccount.keySet()) {
                    if (person.getID().equals(id)) {
                        found = true;
                        guest = person;
                    }
                }
                if(!found)
                    System.out.println("ID not found, try again. ");
            }
            if(userAccount.get(guest).getStatus().equals("manager") && choice == 2){
                System.out.println("This is a manager account, cannot be accessed through member menu.");
                guest = null;
            }
            else {
                System.out.println("ID found, enter password");
                password = scan.next();
                while (!guest.getPassword().equals(password) && attempts != 3) {
                    attempts++;
                    System.out.println("Wrong password try again. (Max: 3 attempts, if exceeded, account will be locked)");
                    password = scan.next();
                }
                if(attempts == 3)
                    guest = null;
            }
        }
        return guest;

    }

    //Writing out to a file to remember information
    public static void writeOut(HashMap<User, Account> userAccountHM) throws IOException {
        PrintWriter outfile = new PrintWriter("usernamePasswords.txt");
        for(User person : userAccountHM.keySet()){
            outfile.println(userAccountHM.get(person).getFullName() + " " + person.getID() + " "
            + userAccountHM.get(person).getEmail() + " " + person.getPassword() + " " +
                    userAccountHM.get(person).getBalance() + " " + userAccountHM.get(person).getStatus());
        }
        outfile.close();
    }

    //Menu for the manager POV
    public static void managerMenu(Scanner scan, HashMap<User, Account> userAccountHM, User person){
        int choice;
        String ID, answer;
        User member = null;
        System.out.println("Welcome, " + userAccountHM.get(person).getFullName());
        printManagerMenu();
        choice = scan.nextInt();
        while(choice != 3){
            if (choice == 1){
                System.out.println("Enter ID of member: ");
                ID = scan.next();
                for(User findID : userAccountHM.keySet()){
                    if(findID.getID().equals(ID)){
                        member = findID;
                        break;
                    }
                }
                if(member == null){
                    System.out.println("No one found");
                }
                else{
                    System.out.println("Name: " + userAccountHM.get(member).getFullName() + "\nID: " + member.getID() +
                            "\nPassword: " + member.getPassword() + "\nEmail: " + userAccountHM.get(member).getEmail());
                    System.out.println("Balance: $" + userAccountHM.get(member).getBalance());
                }
            }
            else if(choice == 2){
                System.out.println("Enter ID of member to be deleted: ");
                ID = scan.next();
                for(User findID : userAccountHM.keySet()){
                    if(findID.getID().equals(ID)){
                        member = findID;
                    }
                }
                if(member == null)
                    System.out.println("Member not found");
                else{
                    System.out.println("Delete " + userAccountHM.get(member).getFullName() + "? (Y/N)");
                    answer = scan.next();
                    while(!answer.equals("Y") && !answer.equals("N")){
                        System.out.println("Invalid");
                        System.out.println("Delete " + userAccountHM.get(member).getFullName() + "? (Y/N)");
                        answer = scan.next();
                    }
                    if(answer.equals("Y")){
                        userAccountHM.remove(member);
                    }
                    else
                        System.out.println("Deletion cancelled");
                }
            }
            printManagerMenu();
            choice = scan.nextInt();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean existingManager = false;
        HashMap<User, Account> userAccountHM = new HashMap<>();
        userAccountHM = priorInfo(userAccountHM);
        User person, manager;
        Account personAccount, managerAccount;
        int choice;
        System.out.println("Welcome!");
        printDefaultMenu();
        choice = scan.nextInt();
        while (choice > 4 || choice < 1) {
            System.out.println("Invalid choice, try again");
            choice = scan.nextInt();
        }
        while (choice != 4){
            if (choice == 1) {
                person = createUser(scan, userAccountHM);
                personAccount = createAccount(scan);
                userAccountHM.put(person, personAccount);
            }
            else if(choice == 2){
                person = login(scan, userAccountHM, choice);
                if(person == null)
                    System.out.println("No login found");
                else {
                    accountMenu(scan, userAccountHM, person);
                }
            }
            else if(choice == 3){
                for(User checkStatus : userAccountHM.keySet()){
                    if(userAccountHM.get(checkStatus).getStatus().equals("manager")) {
                        existingManager = true;
                        break;
                    }
                }
                if(existingManager) {
                    manager = login(scan, userAccountHM, choice);
                    if (manager == null || userAccountHM.get(manager).getStatus().equals("member"))
                        System.out.println("No manager exists");
                    else {
                        managerMenu(scan, userAccountHM, manager);
                    }
                }
                else {
                    manager = createUser(scan, userAccountHM);
                    managerAccount = createManagerAccount(scan);
                    userAccountHM.put(manager, managerAccount);
                }

            }
            printDefaultMenu();
            choice = scan.nextInt();
            while (choice > 4 || choice < 1) {
                System.out.println("Invalid choice, try again");
                choice = scan.nextInt();
            }
        }
        System.out.println("Goodbye!");
        writeOut(userAccountHM);
    }
}