package bigO;

//https://www.baeldung.com/java-algorithm-complexity
public class BigO {

    public static void main(String[] args) {

        /*
        Better to the worst Big O annotation.
        Big O -> must understand the rates at which things can grow
         */

        //O(1) -> Constant time
        int n = 100;
        System.out.println("Your input is: " + n);

        System.out.println("\n");
        /*****************************/

        //Logarithmic Time Algorithms
        //running time grows in proportion to the logarithm of the input
        // log to the base 2 (Example: BinarySearch)
        //O(log n)
        for (int i = 1; i < n; i = i * 2) {
            System.out.println("O(log n) : " + i);
        }
        System.out.println("\n");
        /*****************************/

        //Linear Time Algorithms
        // O(N)
        for(int i = 0; i < n; i++) {
            System.out.println("O(N) : " + i);
        }
        System.out.println("\n");
        /*****************************/

        //O(n log n)
        //The running time grows in proportion to n log n of the input
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j < n; j = j * 2) {
                System.out.println("O(n log n) : " + j);
            }
        }
        System.out.println("\n");
        /*****************************/

        //O(n²) -> quadratic (faster than n3)
        //O(n3) -> cubic (faster than n4 and so on....)
        //O(n4) -> quartic
        //Quadratic example:
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                System.out.println("O(n²) : " + j);
            }
        }
        System.out.println("\n");
        /*****************************/

        //Exponential Time Algorithms
        //O(k n) algo will het k times bigger with every additional input
        //O(3n) algo triple with every additional input
        for(int i = 1; i <= Math.pow(2, n); i++) {
            System.out.println("O(k n): " + i);
        }
        System.out.println("\n");
        /*****************************/

        //Factorial Time Algorithms (worst algorithm)
        //O(n!)
        /*
        for (int i = 1; i <= factorial(n); i++){
            System.out.println("Hey - I'm busy looking at: " + i);
        }
         */

    }


}
