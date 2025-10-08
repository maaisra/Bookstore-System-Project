package Lib.StrategyPattern;
import Lib.OrderProcessor.*;

public class NewBookDiscount implements DiscountStrategy {
    private final double percentage;

    public NewBookDiscount(double percentage){
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(Order order) {
        double originPrice = order.getBook().price() * order.getQuantity();
        return originPrice * (1.0 - percentage / 100.0);
    }
}