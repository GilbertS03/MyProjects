import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class sort {
    public static void populate(int[] nums){
        int x;
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            x = rand.nextInt(0, 1000);
            nums[i] = x;
        }
    }

    public static void sort(int[] nums){
        int temp;
        for(int i = 0; i < nums.length - 1; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] > nums[j]){
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    public static void printArray(int[] nums){
        for(int i = 0; i < nums.length; i++){
            System.out.println(nums[i]);
        }
    }

    public static void main(String[] args){
        int[] nums = new int[10];
        populate(nums);
        System.out.println("Before: ");
        printArray(nums);
        sort(nums);
        System.out.println("After: ");
        printArray(nums);
    }
}
