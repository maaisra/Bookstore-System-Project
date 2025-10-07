package pricing;

public interface DiscountStrategy {
    double applyDiscount(Order order);
}
