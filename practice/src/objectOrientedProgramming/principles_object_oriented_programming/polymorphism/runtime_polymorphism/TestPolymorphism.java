package objectOrientedProgramming.principles_object_oriented_programming.polymorphism.runtime_polymorphism;

public class TestPolymorphism {
    public static void main(String args[]) {
        Bank b;
        b = new SBI();
        System.out.println("SBI Rate of Interest: " + b.getRateOfInterest());
        b = new ICICI();
        System.out.println("ICICI Rate of Interest: " + b.getRateOfInterest());
        b = new AXIS();
        System.out.println("AXIS Rate of Interest: " + b.getRateOfInterest());
    }

    static class Bank {
        float getRateOfInterest() {
            return 0;
        }
    }

    static class SBI extends Bank {
        float getRateOfInterest() {
            return 8.4f;
        }
    }

    static class ICICI extends Bank {
        float getRateOfInterest() {
            return 7.3f;
        }
    }

    static class AXIS extends Bank {
        float getRateOfInterest() {
            return 9.7f;
        }
    }
}
