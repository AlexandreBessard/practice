package leetcode.design;
//https://leetcode.com/problems/design-hit-counter/
public class DesignHitCounter {

    public static void main(String[] args) {
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1);       // hit at timestamp 1.
        hitCounter.hit(2);       // hit at timestamp 2.
        hitCounter.hit(3);       // hit at timestamp 3.
        System.out.println(hitCounter.getHits(4));   // get hits at timestamp 4, return 3.
        hitCounter.hit(300);     // hit at timestamp 300.
        System.out.println(hitCounter.getHits(300)); // get hits at timestamp 300, return 4.
        System.out.println(hitCounter.getHits(301)); // get hits at timestamp 301, return 3.
        System.out.println(hitCounter.getHits(599)); //If the value is changed to 600, return 0
    }

    static class HitCounter {
        private final int[] times;
        private final int[] hits;
        //HitCounter() Initializes the object of the hit counter system.
        public HitCounter() {
            times = new int[300]; //each index represent a second (300 seconds == 5 minutes) value by default is 0
            hits = new int[300]; //1 bucket for every second, value by default is set to 0
        }
        //void hit(int timestamp) Records a hit that happened at timestamp (in seconds).
        // Several hits may happen at the same timestamp.
        public void hit(int timestamp) {
            int index = timestamp % 300; //We want to track the most recent 5 minutes
            if(times[index] != timestamp) {
                times[index] = timestamp; //Store the timestamp
                hits[index] = 1; //Count when we call hit() method at this timestamp
            } else {
                hits[index]++;
            }
        }
        //int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).
        public int getHits(int timestamp) {
            int total = 0;
            for (int i = 0; i < 300; i++) { //Loop through the entire array to count our hits
                int secondRange = timestamp - times[i];
                if (secondRange < 300) { // Check if do not exceed 5 minutes, true if -> most recent 5 minutes from that timestamp
                    total += hits[i];
                }
            }
            return total;
        }
    }

}
