package Lib.StrategyPattern;

import Lib.DataModels.*;

public class BulkDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(Order order) {
        if (order.getBookCount() >= 3) {
            return order.getTotalPrice() * 0.9;
        }
        return order.getTotalPrice();
    }
}
