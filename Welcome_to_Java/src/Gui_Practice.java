import javax.swing.JOptionPane;

public class Gui_Practice {

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

    public static void main(String[] args) {

        String name = JOptionPane.showInputDialog("Enter your name");
        JOptionPane.showMessageDialog(null, "Hello " + name);


        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your age"));
        JOptionPane.showMessageDialog(null, "You are " + age + " years old");

        double height = Double.parseDouble(JOptionPane.showInputDialog("Enter your height"));
        JOptionPane.showMessageDialog(null, "You are " + height + " cm tall");


        int option = Integer.parseInt(JOptionPane.showInputDialog("Choose a function: \n1. Add \n2. Subtract" +
                "\n3. Multiply \n4.Divide \n5. Exponentiate \n6. Quit"));
        while(option != 6){
            double num1, num2;
            if (option == 1){
                num1 = Double.parseDouble(JOptionPane.showInputDialog("Enter a number: "));
                num2 = Double.parseDouble(JOptionPane.showInputDialog("Enter another number to add: "));
                JOptionPane.showMessageDialog(null, "Your result is: " + add(num1, num2));
            }
            else if(option == 2){
                num1 = Double.parseDouble(JOptionPane.showInputDialog("Enter a number: "));
                num2 = Double.parseDouble(JOptionPane.showInputDialog("Enter another number to subtract: "));
                JOptionPane.showMessageDialog(null, "Your result is: " + subtract(num1, num2));
            }
            else if(option == 3){
                num1 = Double.parseDouble(JOptionPane.showInputDialog("Enter a number: "));
                num2 = Double.parseDouble(JOptionPane.showInputDialog("Enter another number to multiply: "));
                JOptionPane.showMessageDialog(null, "Your result is: " + multiply(num1, num2));
            }
            else if(option == 4){
                num1 = Double.parseDouble(JOptionPane.showInputDialog("Enter a number: "));
                num2 = Double.parseDouble(JOptionPane.showInputDialog("Enter another number to divide: "));
                JOptionPane.showMessageDialog(null, "Your result is: " + divide(num1, num2));
            }
            else{
                num1 = Double.parseDouble(JOptionPane.showInputDialog("Enter a number: "));
                num2 = Double.parseDouble(JOptionPane.showInputDialog("Enter another number to exponentiate: "));
                JOptionPane.showMessageDialog(null, "Your result is: " + exponential(num1, num2));
            }
            option = Integer.parseInt(JOptionPane.showInputDialog("Choose a function: \n1. Add \n2. Subtract" +
                    "\n3. Multiply \n4.Divide \n5. Exponentiate \n6. Quit"));
        }
        JOptionPane.showMessageDialog(null, "Goodbye!");
    }
}
