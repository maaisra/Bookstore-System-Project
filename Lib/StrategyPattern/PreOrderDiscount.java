package Lib.StrategyPattern;

import Lib.DataModels.*;

public class PreOrderDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(Order order) {
        return order.getTotalPrice() * 0.7;
    }
}
