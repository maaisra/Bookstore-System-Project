package Lib.StrategyPattern;

import Lib.*;

public class BulkDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(Order order) {
        if (order.getBooks().size() >= 3) {
            return order.getTotalPrice() * 0.9;
        }
        return order.getTotalPrice();
    }
}