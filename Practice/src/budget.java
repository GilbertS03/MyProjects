import java.util.*;
class Budget{
    private double total;
    public Budget(){
        total = 0;
    }
    public Budget (double x){
        total = x;
    }
    public double getTotal(){
        return total;
    }
    public void minusTotal(double y){
        total -= y;
    }
    public void addTotal(double z){
        total += z;
    }

}
public class budget{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double expense, more_money;
        System.out.println("How much money are you starting with?");
        double money = scan.nextDouble();
        Budget person = new Budget(money);
        int i = 0;
        System.out.println("What is expense number " + i + " (-1 to end)");
        expense = scan.nextDouble();
        while (expense != 1){
            person.minusTotal(expense);
            i++;
            System.out.println("What is expense number " + i + " (-1 to end)");
            expense = scan.nextDouble();
        }
        System.out.println(person.getTotal());
    }
}