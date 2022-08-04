package academy.learnprogramming.heaps.priorityQueues;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() { //Min heap, lower number higher priority
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        pq.add(25);
        pq.add(-22);
        pq.add(1343);
        pq.add(54);
        pq.add(0);
        pq.add(-3492);
        pq.add(429);

        //System.out.println(pq.peek());
        pq.remove(); //remove element with the higher priority (lower element based on the comparator)
        //System.out.println(pq.peek());
        pq.poll();
        System.out.println(pq.peek());

        System.out.println("--------------------------");

//        System.out.println(pq.peek());
//        System.out.println(pq.remove());
//        System.out.println(pq.peek());
//        System.out.println(pq.poll());
//        System.out.println(pq.peek());
        System.out.println(pq.remove(54));

        Object[] ints = pq.toArray();
        for (Object num: ints) {
            System.out.println(num);
        }

        //System.out.println(pq.peek());
        pq.add(-1);
        //System.out.println(pq.peek());
    }
}
