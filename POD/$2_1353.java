package POD;
import java.util.*;
public class $2_1353 {
    public int maxEvents(int[][] events) {
        int lastDate = -1;
        int n = events.length;

        for(int event[] : events){
            lastDate = Math.max(lastDate, event[1]);
        }

        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int count = 0;
        int j = 0;
        for(int i = 1; i <= lastDate; i++){

            while(j < n && events[j][0] <= i){
                pq.offer(events[j][1]);
                j++;
            }
            while (!pq.isEmpty() && pq.peek() < i) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                pq.poll();
                count++;
            }
        }

        return count;
    }
}