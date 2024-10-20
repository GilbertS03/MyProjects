import org.w3c.dom.ls.LSOutput;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
public class Sorting {
    public static long simpleSort(int[] n) {
        Instant start, stop;
        start = Instant.now();
        for (int i = 0; i < n.length - 1; i++) {
            for (int j = i + 1; j < n.length; j++) {
                if (n[i] > n[j]) {
                    int temp = n[i];
                    n[i] = n[j];
                    n[j] = temp;
                }
            }
        }
        stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    public static long selectionSort(int[] n) {
        Instant start, stop;
        start = Instant.now();
        int minIdx, minVal;
        for (int i = 0; i < n.length - 1; i++) {
            minIdx = i;
            minVal = n[i];
            for (int j = i + 1; j < j++; ) {
                if (n[j] < minVal) {
                    minVal = n[j];
                    minIdx = j;
                }
            }
            n[minIdx] = n[i];
            n[i] = minVal;
        }
        stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int n[] = new int[100000];
        int m[] = new int[100000];
        int x = rand.nextInt(0, 200);

        for (int i = 0; i < n.length; i++) {
            n[i] = x;
            m[i] = x;
            x = rand.nextInt(0, 2000);
        }
        System.out.println(simpleSort(m));
        System.out.println(selectionSort(n));
    }
}