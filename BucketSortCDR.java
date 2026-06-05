import java.util.*;

public class BucketSortCDR {

    public static void bucketSort(int[] arr) {

        int bucketCount = 10;

        ArrayList<Integer>[] buckets = new ArrayList[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int num : arr) {
            int bucketIndex = num / 10;
            buckets[bucketIndex].add(num);
        }

        System.out.println("Bucket Contents:");

        for (int i = 0; i < bucketCount; i++) {
            Collections.sort(buckets[i]);
            System.out.println("Bucket " + i + ": " + buckets[i]);
        }

        int index = 0;

        for (int i = 0; i < bucketCount; i++) {
            for (int num : buckets[i]) {
                arr[index++] = num;
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = {37, 12, 78, 25, 91, 4, 56, 8, 33, 67, 19, 45};

        System.out.println("Input Array:");
        System.out.println(Arrays.toString(arr));

        bucketSort(arr);

        System.out.println("\nSorted Array:");
        System.out.println(Arrays.toString(arr));
    }
}