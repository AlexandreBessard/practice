package designPatterns;

// Strategy: DiscountStrategy
interface DiscountStrategy {
    double applyDiscount(double amount);
}

// Concrete Strategy: RegularCustomerDiscount
class RegularCustomerDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.05; // 5% discount for regular customers
    }
}

// Concrete Strategy: LoyalCustomerDiscount
class LoyalCustomerDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.10; // 10% discount for loyal customers
    }
}

// Concrete Strategy: NewCustomerDiscount
class NewCustomerDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.20; // 20% discount for new customers
    }
}

// Context: PaymentProcessor
class PaymentProcessor {
    private DiscountStrategy discountStrategy;

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double processPayment(double amount) {
        // Calculate discounted amount based on the selected strategy
        double discountedAmount = amount - discountStrategy.applyDiscount(amount);
        System.out.println("Discounted Amount: " + discountedAmount);
        return discountedAmount;
    }
}

public class Strategy {

    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        // Regular customer payment
        paymentProcessor.setDiscountStrategy(new RegularCustomerDiscount());
        paymentProcessor.processPayment(100.0);

        // Loyal customer payment
        paymentProcessor.setDiscountStrategy(new LoyalCustomerDiscount());
        paymentProcessor.processPayment(100.0);

        // New customer payment
        paymentProcessor.setDiscountStrategy(new NewCustomerDiscount());
        paymentProcessor.processPayment(100.0);
    }

}
