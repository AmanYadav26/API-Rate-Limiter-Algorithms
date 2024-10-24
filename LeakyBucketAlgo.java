import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakyBucketAlgo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the bucket (queue): ");
        int n = sc.nextInt();
        Queue queue = new Queue(n);

        // ** if you want to process requests automatically after 5 seconds you can use the below code

//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(() -> {
//            queue.remove();
//        }, 0, 5, TimeUnit.SECONDS);



        while (true) {
            System.out.println("Enter 1 to add request, 2 to process request, 0 to exit:");
            int choice = sc.nextInt();
            if (choice == 1) {
                queue.request();
            } else if (choice == 2) {
                queue.remove();
            } else if (choice == 0) {
                break;
            }
        }
        sc.close();
    }
}

class Queue {
    int[] arr;
    int maxSize;
    int currentIndex = 0;
    int processedIndex = 0;
    int currentRequests = 0;

    Queue(int size) {
        arr = new int[size];
        maxSize = size;
    }
    void request() {
        if (currentRequests < maxSize) {
            int index = currentIndex % maxSize;
            arr[index] = 1;
            currentIndex++;
            currentRequests++;
            System.out.println("Request added to the queue. Current requests:" + currentRequests);
        } else {
            System.out.println("Bucket is full! Cannot add more requests.");
        }
    }


    void remove() {
        if (currentRequests > 0) {
            int index = processedIndex % maxSize;
            arr[index] = 0;
            processedIndex++;
            currentRequests--;
            System.out.println("Processed a request. Current requests:" + currentRequests);
        } else {
            System.out.println("No requests to process.");
        }
    }
}
