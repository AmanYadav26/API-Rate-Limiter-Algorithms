import java.util.*;
import java.time.LocalTime;
import java.time.Duration;
public class TokenBucketAlgo {
    int maxCountPerMinute = 3;
    LocalTime time = null;
    public static void main(String[] args) {
        TokenBucketAlgo tokenBucket = new TokenBucketAlgo();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Press 1 to make a request, or 0 to exit:");
            int choice = sc.nextInt();
            if (choice == 1) {
                tokenBucket.request();
            } else {
                break;
            }
        }
        sc.close();
    }
    public void request() {
        LocalTime currTime = LocalTime.now();
        if (time == null) {
            time = currTime;
        }
        Duration duration = Duration.between(time, currTime);
        if (duration.toMinutes() >= 1) {
            maxCountPerMinute = 3;
            time = currTime;
        }
        if (maxCountPerMinute > 0) {
            maxCountPerMinute--;
            System.out.println("Call the API. Tokens left: " + maxCountPerMinute);
        } else {
            System.out.println("You are making requests too fast. Please wait.");
        }
    }
}
