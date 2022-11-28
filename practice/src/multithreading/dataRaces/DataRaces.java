package multithreading.dataRaces;

public class DataRaces {

    public static void main(String[] args) {
        final var sharedClass = new SharedClass();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkForDataRaces();
            }
        });
        t1.start(); //Create and start a new Thread
        t2.start();
    }

    public static class SharedClass {
        private int x = 0;
        private int y = 0;

        public SharedClass() {
        }

        public void increment() {
            this.x++;
            this.y++;
        }

        public void checkForDataRaces() {
            if (this.y > this.x) {
                System.out.println("y > x - Data Race is detected");
            }
        }
    }

}
