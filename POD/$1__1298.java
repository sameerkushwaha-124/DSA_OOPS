package POD;
import java.util.LinkedList;
import java.util.Queue;

public class $1__1298 {
    public static void main(String[] args) {
        int[] status = {1, 0, 1, 0};     // This is a status of the box which tells ith box is opened or not!
        int[] candies = {7, 5, 4, 100};  // This is no of candies that ith box contains inside.
        int[][] keys = {                 // This is a list of boxes that can be opened by the ith key.
                {},
                {},
                {1}, // means (we can open box 1 with key = 2)
                {}
        };
        int[][] containedBoxes = {       // This is list of boxes that ith box contains.
                {1, 2}, // mean (0th box contains boxes 1, 2)
                {3},
                {},
                {}
        };
        int[] initialBoxes = {0};        // This is list of boxes that initially have in your hand.
        System.out.println(bruteForce(status, candies, keys, containedBoxes, initialBoxes));
    }
    public static int bruteForce(int[] status, int[] candies, int[][] keys, int[][] containedBoxes,
                                 int[] initialBoxes) {
        int noOfBoxes = status.length;

        boolean[] visitedBoxes = new boolean[noOfBoxes];
        boolean[] hasBox = new boolean[noOfBoxes];
        int ans = 0;

        // abhi tak jitney bhi initial boxes given the aur open the unhe hamne q me add kiya aur kitne candies mile unhe bhi count kiya. if box close you can't open that box because to open that box you need key and key in always be found inside any of the boxes.
        Queue<Integer> q = new LinkedList<>();
        for (int box : initialBoxes) {
            hasBox[box] = true;
            if (status[box] == 1) {
                q.add(box);
                visitedBoxes[box] = true;
                ans += candies[box];
            }
        }

        // abb jo jo box open hain usme se candies toh count kar liye par us box me keys bhi ho sakte hain aur un boxes ke ander dusre box bhi ho sakte hain.
        // abb jo jo open boxes hain q me unme se jo jo important info hain use karo aur remove karo aur dusre boxes ko add karo.
        while (!q.isEmpty()) {
            int bigBox = q.poll();

            // abhi mujhe key bhi nikalna hai usi box se
            for (int key : keys[bigBox]) {
                status[key] = 1;
                if (hasBox[key] && !visitedBoxes[key]) {
                    q.add(key);
                    visitedBoxes[key] = true;
                    ans += candies[key];
                }
            }

            // yaha maine boxes ko nikala hain box se.
            for (int newBox : containedBoxes[bigBox]) {
                hasBox[newBox] = true;
                if (!visitedBoxes[newBox] && status[newBox] == 1) {
                    q.add(newBox);
                    visitedBoxes[newBox] = true;
                    ans += candies[newBox];
                }
            }
        }

        return ans;
    }
}
