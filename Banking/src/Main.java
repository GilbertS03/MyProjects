import javax.swing.*;
import java.util.*;
import java.io.*;
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
    public Account(String full, String em, double bal, String stats){
        fullName = full;
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
    public static User createUser(HashMap<User, Account>userAccountHM){
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
            pw = JOptionPane.showInputDialog("Password must contain at least 8 characters, 1 cap, 1 lower, and 1 special\nEnter a password:");
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
                    JOptionPane.showMessageDialog(null,"Valid password");
                }
                else
                    JOptionPane.showMessageDialog(null,"Password not good, try again");
            }
            else{
                JOptionPane.showMessageDialog(null,"Password not long enough, try again");
            }
        }

        JOptionPane.showMessageDialog(null,"WRITE THIS DOWN\nID: " + ID + "\nPassword: " + pw);
        newUser = new User(ID, pw);
        return newUser;
    }

    public static Account createAccount(){
        String fn, ln, email, status = "member";
        double amount;
        Account newPerson;
        fn = JOptionPane.showInputDialog("Enter first name: ");
        ln = JOptionPane.showInputDialog("Enter last name: ");
        email = JOptionPane.showInputDialog("Enter email: ");
        amount = Double.parseDouble(JOptionPane.showInputDialog("Enter initial deposit amount: "));
        while(amount < 0){
            amount = Double.parseDouble(JOptionPane.showInputDialog("Number must be positive, try again: "));
        }

        newPerson = new Account(fn, ln, email, amount, status);
        return newPerson;
    }

    //Making an account for managers
    public static Account createManagerAccount(){
        String fn, ln, email, status = "manager";
        double amount = 0; //Manager account doesn't need money
        Account newPerson;

        //Getting information
        fn = JOptionPane.showInputDialog("Enter first name: ");
        ln = JOptionPane.showInputDialog("Enter last name: ");
        email = JOptionPane.showInputDialog("Enter email: ");

        //Creating account
        newPerson = new Account(fn, ln, email, amount, status);
        return newPerson;
    }

    //Account menu for members
    public static int printAccountMenu(){
        int choice;
        choice = Integer.parseInt(JOptionPane.showInputDialog("1. Deposit\n2. Withdrawal\n3. Check Balance\n4. " +
                "Check Information\n5. Quit"));
        return choice;
    }

    //Menu when first opening this up
    public static int printDefaultMenu(){
        int choice = Integer.parseInt(JOptionPane.showInputDialog("1. Create Account\n2. Log In\n3. Log In Manager Account\n4. Quit"));
        return choice;
    }

    //Menu for managers
    public static int printManagerMenu(){
        int choice = Integer.parseInt(JOptionPane.showInputDialog("1. Check Member Information\n2. Delete Member\n3. Quit"));
        return choice;
    }

    //Menu for members when they select an option
    public static void accountMenu(HashMap<User, Account> userAccountHM, User person){
        int choice;
        double amount;
        JOptionPane.showMessageDialog(null,"Welcome, " + userAccountHM.get(person).getFullName());
        choice = printAccountMenu();
        while(choice > 5 || choice < 1){
            choice = Integer.parseInt(JOptionPane.showInputDialog("Invalid choice, try again"));
        }
        while(choice != 5) {
            if (choice == 1) {
                amount = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount you would like to deposit: "));
                while (amount < 0) {
                    amount = Double.parseDouble(JOptionPane.showInputDialog("Number must be positive, try again"));
                }
                userAccountHM.get(person).deposit(amount);
                JOptionPane.showMessageDialog(null,"You deposited $" + amount + "\nBalance: $" + userAccountHM.get(person).getBalance());
            }
            else if (choice == 2) {
                amount = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount you would like to deposit: "));
                while (amount < 0 || (userAccountHM.get(person).getBalance() - amount) < 0) {
                    if (amount < 0) {
                        amount = Double.parseDouble(JOptionPane.showInputDialog("Number must be positive, try again"));
                    }
                    else if (userAccountHM.get(person).getBalance() - amount < 0) {
                        amount = Double.parseDouble(JOptionPane.showInputDialog("This is an overdraft, must be above the amount " +
                                "you have or equal to it\nTry again: "));
                    }
                }
                userAccountHM.get(person).withdrawal(amount);
                JOptionPane.showMessageDialog(null,"You withdrew $" + amount + "\nBalance: $" + userAccountHM.get(person).getBalance());
            }
            else if(choice == 3) {
                JOptionPane.showMessageDialog(null, "Balance: $" + userAccountHM.get(person).getBalance());
            }
            else if(choice == 4){
                JOptionPane.showMessageDialog(null,"Name: " + userAccountHM.get(person).getFullName() + "\nID: " + person.getID() +
                        "\nPassword: " + person.getPassword() + "\nEmail: " + userAccountHM.get(person).getEmail());
            }
            choice = printAccountMenu();
            while(choice > 5 || choice < 1){
                choice = Integer.parseInt(JOptionPane.showInputDialog("Invalid choice, try again: "));
            }
        }
    }

    //Logging in & using choice in parameters due to type of log in, if member login, the choice is 2, manager = 3
    public static User login(HashMap<User, Account> userAccount, int choice){
        String id, password;
        int attempts = 1;
        User guest = null;
        boolean found = false, quit = false;
        if(userAccount.isEmpty())
            JOptionPane.showMessageDialog(null,"No accounts exist");
        else{
            while(!found && !quit) {
                id = JOptionPane.showInputDialog("Enter your ID to login (-1 to quit): ");
                if(id.equals("-1"))
                    quit = true;
                else{
                    for (User person : userAccount.keySet()) {
                        if (person.getID().equals(id)) {
                            found = true;
                            guest = person;
                        }
                    }
                    if(!found)
                        JOptionPane.showMessageDialog(null, "ID not found, try again");
                }
            }
            if(quit){
                guest = null;
            }
            else {
                if(userAccount.get(guest).getStatus().equals("manager") && choice == 2){
                    JOptionPane.showMessageDialog(null,"This is a manager account, cannot be accessed through member menu.");
                    guest = null;
                }
                else {
                    password = JOptionPane.showInputDialog("ID found, enter password");
                    while (!guest.getPassword().equals(password) && attempts != 3) {
                        attempts++;
                        password = JOptionPane.showInputDialog("Wrong password try again. (Max: 3 attempts, if exceeded, account will be locked)");
                    }
                    if(attempts == 3)
                        guest = null;
                }
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
    public static void managerMenu(HashMap<User, Account> userAccountHM, User person){
        int choice;
        String ID, answer;
        User member = null;
        JOptionPane.showMessageDialog(null,"Welcome, " + userAccountHM.get(person).getFullName());
        choice = printManagerMenu();
        while(choice != 3){
            if (choice == 1){
                ID = JOptionPane.showInputDialog(null, "Enter ID of member: ");
                for(User findID : userAccountHM.keySet()){
                    if(findID.getID().equals(ID)){
                        member = findID;
                        break;
                    }
                }
                if(member == null){
                    JOptionPane.showMessageDialog(null, "No one found");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Name: " + userAccountHM.get(member).getFullName() + "\nID: " + member.getID() +
                            "\nPassword: " + member.getPassword() + "\nEmail: " + userAccountHM.get(member).getEmail());
                }
            }
            else if(choice == 2){
                ID = JOptionPane.showInputDialog("Enter ID of member to be deleted");
                for(User findID : userAccountHM.keySet()){
                    if(findID.getID().equals(ID)){
                        member = findID;
                    }
                }
                if(member == null)
                    JOptionPane.showInputDialog("Member not found");
                else{
                    answer = JOptionPane.showInputDialog("Delete " + userAccountHM.get(member).getFullName() + "? (Y/N)");
                    while(!answer.equals("Y") && !answer.equals("N")){
                        answer = JOptionPane.showInputDialog("Invalid\nDelete " + userAccountHM.get(member).getFullName() + "? (Y/N)");
                        answer = answer.toUpperCase();
                    }
                    if(answer.equals("Y")){
                        userAccountHM.remove(member);
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Deletion cancelled");
                }
            }
            choice = printManagerMenu();
        }
    }

    public static void main(String[] args) throws IOException {
        boolean existingManager = false;
        HashMap<User, Account> userAccountHM = new HashMap<>();
        userAccountHM = priorInfo(userAccountHM);
        User person, manager;
        Account personAccount, managerAccount;
        int choice;
        JOptionPane.showMessageDialog(null, "Welcome");
        choice = printDefaultMenu();
        while (choice > 4 || choice < 1) {
            choice = Integer.parseInt(JOptionPane.showInputDialog("Invalid choice, try again"));
        }
        while (choice != 4){
            if (choice == 1) {
                person = createUser(userAccountHM);
                personAccount = createAccount();
                userAccountHM.put(person, personAccount);
            }
            else if(choice == 2){
                person = login(userAccountHM, choice);
                if(person == null)
                    JOptionPane.showMessageDialog(null,"No login found");
                else {
                    accountMenu(userAccountHM, person);
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
                    manager = login(userAccountHM, choice);
                    if (manager == null || userAccountHM.get(manager).getStatus().equals("member"))
                        JOptionPane.showMessageDialog(null,"No manager exists");
                    else {
                        managerMenu(userAccountHM, manager);
                    }
                }
                else {
                    manager = createUser(userAccountHM);
                    managerAccount = createManagerAccount();
                    userAccountHM.put(manager, managerAccount);
                }

            }
            choice = printDefaultMenu();
            while (choice > 4 || choice < 1) {
                choice = Integer.parseInt(JOptionPane.showInputDialog("Invalid choice, try again"));
            }
        }
        JOptionPane.showMessageDialog(null, "Goodbye!");
        writeOut(userAccountHM);
    }
}