package Lib.StrategyPattern;

import Lib.DataModels.*;

public class NewBookDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(Order order) {
        return order.getTotalPrice() * 0.8;
    }
}
