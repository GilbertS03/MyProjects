import java.util.*;
import java.io.*;
public class array_practice {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String names[] = new String[10];
        int sums[] = new int[10];
        int ctr[] = new int[10];
        int num_counter = 2;
        names[0] = "John";
        names[1] = "Mary";
        sums[0] = 169;
        sums[1] = 179;
        ctr[0] = 2;
        ctr[1] = 2;
        System.out.println("Type in a name and score (done and -1 to end)");
        String user_name = scan.next();
        int score = scan.nextInt();
        while (!user_name.equals("done") && score != -1){
            num_counter = populate(user_name, score, sums, ctr, names,num_counter);
            System.out.println("Type in a name and score (done and -1 to end)");
            user_name = scan.next();
            score = scan.nextInt();
        }

        calcScore(sums, ctr, names, 2);
    }
    public static void calcScore(int sums[], int count[], String names[], int num_times){
        for (int i = 0; i < num_times; i++){
            System.out.println(names[i]);
            System.out.println(sums[i]/count[i]);
        }
    }
    public static int populate(String name, int score, int sums[], int count[], String names[], int num_times){
        boolean found = false;
        int index = 0;
        while (!found && index < num_times){
            if (name.equals(names[index])){
                found = true;
            }
            else{
                index++;
            }
        }
        names[index] = name;
        sums[index] = score;
        count[index]++;
        if (found){
            return num_times;
        }
        else{
            return num_times + 1;
        }
    }
}
